package tk.vky.epublearning.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.vky.epublearning.dao.UserDao;
import tk.vky.epublearning.model.BusinessFunction;
import tk.vky.epublearning.model.CustomUserDetails;
import tk.vky.epublearning.model.KeyValuePair;
import tk.vky.epublearning.model.Password;
import tk.vky.epublearning.model.Role;
import tk.vky.epublearning.model.User;
import tk.vky.epublearning.model.rowmapper.BusinessFunctionRowMapper;
import tk.vky.epublearning.model.rowmapper.KeyValuePairRowMapper;
import tk.vky.epublearning.model.rowmapper.RoleRowMapper;
import tk.vky.epublearning.model.rowmapper.UserRowMapper;
import tk.vky.epublearning.utils.DateUtils;
import tk.vky.epublearning.utils.PassPhrase;

@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<BusinessFunction> getBusinessFunctionList() {
		return this.jdbcTemplate.query("SELECT * FROM business_function", new BusinessFunctionRowMapper());
	}

	/**
	 * Method to get the list of Users with their roles
	 *
	 * @return Returns a list of users with their roles
	 */
	@Override
	public List<User> getUserList(String userType) {
		StringBuilder sb = new StringBuilder(
				"SELECT e.`FAILED_ATTEMPTS`, e.`EXPIRES_ON`, e.`LAST_LOGIN_DATE`, e.`NAME`, e.`USERNAME`, e.`ACTIVE`, e.`OUTSIDE_ACCESS`, e.USER_ID, er.ROLE_ID, r.ROLE_NAME FROM user e LEFT JOIN user_role er ON e.USER_ID=er.USER_ID LEFT JOIN role r ON er.ROLE_ID=r.ROLE_ID WHERE 1 ");
		Map<String, Object> params = new HashMap<>();
		if (userType.equals("1")) { // only active users
			sb.append(" AND e.`ACTIVE`");
		} else if (userType.equals("-1")) { // only deactive users
			sb.append(" AND e.`ACTIVE`=false");
		}

		return this.namedParameterJdbcTemplate.query(sb.toString(), params, new UserRowMapper());
	}

	/**
	 * Method to get the list of Users with only UserId and Name
	 *
	 * @return Returns the list of users by calling a method from userDao
	 */
	@Override
	public List<KeyValuePair> getUserNameList(boolean active) {
		if (active) { // only active cabs
			return this.jdbcTemplate.query(
					"SELECT user.USER_ID `KEY`, CONCAT(user.NAME,' - ',user.USER_ID) `VALUE` FROM user WHERE user.ACTIVE ORDER BY NAME",
					new KeyValuePairRowMapper());
		} else {
			return this.jdbcTemplate.query(
					"SELECT user.USER_ID `KEY`, CONCAT(user.NAME,' - ',user.USER_ID) `VALUE` FROM user ORDER BY NAME",
					new KeyValuePairRowMapper());
		}
	}

	/**
	 * Method to get the list of Roles that a userId has access to
	 *
	 * @return Returns a list of roles that the userId has access to
	 */
	@Override
	public List<Role> getRoleList() {
		return this.jdbcTemplate.query("SELECT * FROM role", new RoleRowMapper());
	}

	/**
	 * Method to create a new user
	 *
	 * @param user User that you want to create
	 * @return Returns the userId, null if no object could be found
	 */
	@Override
	@Transactional
	public int addUser(User user) {
		// Add record to user table
		SimpleJdbcInsert empInsert = new SimpleJdbcInsert(this.dataSource).withTableName("user")
				.usingGeneratedKeyColumns("USER_ID");
		Map<String, Object> params = new HashMap<>();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashPass = encoder.encode(user.getPassword());
		params.put("NAME", WordUtils.capitalizeFully(user.getName()));
		params.put("USERNAME", user.getUsername().toLowerCase());
		params.put("PASSWORD", hashPass);
		params.put("ACTIVE", true);
		params.put("EXPIRED", false);
		params.put("EXPIRES_ON", DateUtils.getOffsetFromCurrentDateObject(DateUtils.IST, 60));
		params.put("FAILED_ATTEMPTS", 0);
		CustomUserDetails curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal());
		if (curUser.hasBusinessFunction("ROLE_BF_OUTSIDE_ACCESS")) {
			params.put("OUTSIDE_ACCESS", user.isOutsideAccess());
		} else {
			params.put("OUTSIDE_ACCESS", false);
		}
		params.put("CREATED_BY", curUser.getUserId());
		params.put("LAST_MODIFIED_BY", curUser.getUserId());
		Date dt = DateUtils.getCurrentDateObject(DateUtils.IST);
		params.put("CREATED_DATE", dt);
		params.put("LAST_MODIFIED_DATE", dt);

		int userId = 0;
		try {
			userId = empInsert.executeAndReturnKey(params).intValue();
		} catch (Exception m) {
			return userId;
		}
		params.clear();
		// add record to user_role table
		String sqlString = "INSERT INTO user_role (USER_ID, ROLE_ID) VALUES(:userId, :roleId)";
		params.put("userId", userId);
		params.put("roleId", user.getRole().getRoleId());
		this.namedParameterJdbcTemplate.update(sqlString, params);
		return userId;
	}

	/**
	 * Method to update/edit the user
	 *
	 * @param user user that you want to update
	 */
	@Override
	@Transactional
	public void updateUser(User user) {
		Date date = DateUtils.getCurrentDateObject(DateUtils.IST);
		Map<String, Object> params = new HashMap<>();
		CustomUserDetails curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal());
		StringBuilder sb = new StringBuilder("UPDATE user SET ").append("LAST_MODIFIED_DATE=:dt, ")
				.append("LAST_MODIFIED_BY=:curEmpId, ").append("USERNAME=:username, ").append("ACTIVE=:active, ")
				.append("NAME=:name ");
		params.put("userId", user.getUserId());
		params.put("dt", date);
		params.put("curEmpId", curUser.getUserId());
		params.put("username", user.getUsername().toLowerCase());
		params.put("active", user.isActive());
		params.put("name", WordUtils.capitalizeFully(user.getName()));
		if (curUser.hasBusinessFunction("ROLE_BF_OUTSIDE_ACCESS")) {
			sb.append(", OUTSIDE_ACCESS=:outsideAccess");
			params.put("outsideAccess", user.isOutsideAccess());
		}
		sb.append(" WHERE user.USER_ID=:userId");
		this.namedParameterJdbcTemplate.update(sb.toString(), params);

		String sqlString = "UPDATE user_role SET ROLE_ID=:roleId WHERE USER_ID=:userId";
		params.clear();
		params.put("userId", user.getUserId());
		params.put("roleId", user.getRole().getRoleId());
		this.namedParameterJdbcTemplate.update(sqlString, params);
		params.clear();
		String query = " DELETE FROM user_vendor_mapping WHERE USER_ID=:userId; ";
		params.put("userId", user.getUserId());
		this.namedParameterJdbcTemplate.update(query, params);

	}

	/**
	 * Method to get the user object from userId
	 *
	 * @param userId UserId used to get the user by their name
	 * @return Returns the user object, null if no object could be found
	 */
	@Override
	public User getUserByUserId(int userId) {
		String sqlString = "SELECT e.`LAST_MODIFIED_BY`, lastModifiedBy.`USERNAME` LAST_MODIFIED_BY_NAME, createdBy.`USERNAME` CREATED_BY_NAME, e.`LAST_MODIFIED_DATE`, e.`FAILED_ATTEMPTS`, e.`EXPIRED`, e.`PASSWORD`, e.`EXPIRES_ON`, e.`LAST_LOGIN_DATE`, e.`NAME`, e.`USERNAME`, e.`ACTIVE`, e.`OUTSIDE_ACCESS`, e.USER_ID, er.ROLE_ID, r.ROLE_NAME, e.`CREATED_BY`, e.`CREATED_DATE` FROM `user` e LEFT JOIN user_role er ON e.USER_ID=er.USER_ID LEFT JOIN role r ON er.ROLE_ID=r.ROLE_ID LEFT JOIN `user` createdBy ON createdBy.`USER_ID`=e.`CREATED_BY` LEFT JOIN `user` lastModifiedBy ON lastModifiedBy.`USER_ID`=e.`LAST_MODIFIED_BY` WHERE e.USER_ID=?";
		Object params[] = new Object[] { userId };
		try {
//            LogUtils.debugLogger.info(LogUtils.buildStringForSystemLog(sqlString, params));
			return this.jdbcTemplate.queryForObject(sqlString, new UserRowMapper(), userId);
		} catch (EmptyResultDataAccessException erda) {
			return null;
		}
	}

	/**
	 * Method to confirm the password
	 *
	 * @param password
	 * @return Returns true when the password has been confirmed Returns false when
	 *         the password does not matched
	 */
	@Override
	public boolean confirmPassword(Password password) {
		String sqlString = "SELECT user.PASSWORD FROM user WHERE user.USER_ID=:userId";
		Map<String, Object> params = new HashMap<>();
		params.put("userId", password.getUserId());
//        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(sqlString, params));
		String hash = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, String.class);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(password.getOldPassword(), hash)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to update the password for existing user
	 *
	 * @param password Password to get the password
	 * @param offset   Offset to calculate the offset date
	 */
	@Override
	public void updatePassword(Password password, int offset) {
		Date offsetDate = DateUtils.getOffsetFromCurrentDateObject(DateUtils.IST, offset);
		String sqlString = "UPDATE user SET PASSWORD=:hash, EXPIRES_ON=:expiresOn WHERE user.USER_ID=:userId";
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hash = encoder.encode(password.getNewPassword());
		Map<String, Object> params = new HashMap<>();
		params.put("userId", password.getUserId());
		params.put("hash", hash);
		params.put("expiresOn", offsetDate);
		this.namedParameterJdbcTemplate.update(sqlString, params);
	}

	/**
	 * Method to get the list of Business functions that a userId has access to
	 *
	 * @param userId userId that you want to get the Business functions for
	 * @return Returns a list of Business functions that the userId has access to
	 */
	@Override
	public List<String> getBusinessFunctionsForUserId(int userId) {
		String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM user_role LEFT JOIN role_business_function ON user_role.ROLE_ID=role_business_function.ROLE_ID WHERE user_role.USER_ID=?";
		return this.jdbcTemplate.queryForList(sqlString, new Object[] { userId }, String.class);
	}

	/**
	 * Method to get the list of Business functions that a userId has access to
	 *
	 * @param roleId roleId that you want to get the Business functions for
	 * @return Returns a list of Business functions that the roleId has access to
	 */
	@Override
	public List<String> getBusinessFunctionsForRoled(String roleId) {
		String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM role_business_function r WHERE r.`ROLE_ID`=?";
		return this.jdbcTemplate.queryForList(sqlString, new Object[] { roleId }, String.class);
	}

	/**
	 * Method to update the login failure count from userId
	 *
	 * @param userId userId is used to get the user of whom you want to update the
	 *               failedCount
	 * @return Returns the updated login failedCount of a user
	 */
	@Override
	public int incrementFailedCountForUsername(String username) {
		String sqlString = "UPDATE `user` SET user.`FAILED_ATTEMPTS`=user.`FAILED_ATTEMPTS`+1 WHERE user.`USERNAME`=?";
		return this.jdbcTemplate.update(sqlString, username);
	}

	/**
	 * Method to update the user's login details
	 *
	 * @param userId UserId is used to get the user of which you want to update the
	 *               login details
	 */
	@Override
	public void loginSuccessUpdateForUserId(int userId) {
		Date curDt = DateUtils.getCurrentDateObject(DateUtils.IST);
		String sqlString = "UPDATE `user` SET user.`FAILED_ATTEMPTS`=0, user.`LAST_LOGIN_DATE`=:lastLoginDate WHERE user.`USER_ID`=:userId";
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("lastLoginDate", curDt);
		this.namedParameterJdbcTemplate.update(sqlString, params);
	}

	/**
	 * Method to set the number of failed attempts from userId
	 *
	 * @param userId userId is used to get the user of which you want to reset the
	 *               failed attempts
	 */
	@Override
	public void resetFailedAttempts(int userId) {
		String sqlString = "UPDATE `user` SET user.`FAILED_ATTEMPTS`=0 WHERE user.`USER_ID`=?";
		this.jdbcTemplate.update(sqlString, userId);
	}

	/**
	 * Method to reset the password for userId
	 *
	 * @param userId userId is used to get the user of which you want to reset the
	 *               password
	 */
	@Override
	public String resetPassword(int userId) {
		String pass = PassPhrase.getSmallCasePassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashPass = encoder.encode(pass);
		String sqlString = "UPDATE `user` SET user.`PASSWORD`=?, user.`EXPIRES_ON`=? WHERE user.`USER_ID`=?";
		if (this.jdbcTemplate.update(sqlString, hashPass, "2001-01-01", userId) == 1) {
			return pass;
		} else {
			return "Could not reset password";
		}

	}

	/**
	 * Method to get the list of who can create roles
	 *
	 * @param roleId roleId is used to get the role of user to access the
	 *               canCreateRole list
	 * @return Returns the roleId object and the list of who can create roles
	 */
	@Override
	public List<Role> getCanCreateRoleList(String roleId) {
		String sqlString = "SELECT role.* from can_create_roles LEFT JOIN role on can_create_roles.CAN_CREATE_ROLE=role.ROLE_ID where can_create_roles.ROLE_ID=?";
		return this.jdbcTemplate.query(sqlString, new RoleRowMapper(), roleId);
	}

	/**
	 * Method to get who can create roles by the roleId
	 *
	 * @param roleId
	 * @param canCreateRoleId
	 * @return Returns true if user has access to create a role Returns false if
	 *         user does not have access to create a role
	 */
	@Override
	public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId) {
		String sqlString = "SELECT count(*) from can_create_roles where can_create_roles.ROLE_ID=:roleId and can_create_roles.CAN_CREATE_ROLE=:canCreateRoleId";
		Map<String, Object> params = new HashMap<>();
		params.put("roleId", roleId);
		params.put("canCreateRoleId", canCreateRoleId);
		int i = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, Integer.class).intValue();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to update the canCraeteRoles table
	 *
	 * @param roleId           RoleId used to get the id of role which you want to
	 *                         create
	 * @param canCreateRoleIds canCreateRoleIds is used to get who can create the
	 *                         role
	 */
	@Override
	public void updateCanCreateRoles(final String roleId, final String[] canCreateRoleIds) {
		String sqlString = "DELETE FROM can_create_roles where can_create_roles.ROLE_ID=?";
		this.jdbcTemplate.update(sqlString, roleId);
		sqlString = "INSERT INTO can_create_roles (ROLE_ID, CAN_CREATE_ROLE) VALUES(?, ?)";
		this.jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, roleId);
				ps.setString(2, canCreateRoleIds[i]);
			}

			@Override
			public int getBatchSize() {
				return canCreateRoleIds.length;
			}
		});
	}

	@Override
	public String getUserVendorMappingListByUserId(int userId) {
		return this.jdbcTemplate.queryForObject(
				"SELECT GROUP_CONCAT(uvm.`VENDOR_ID`) FROM user_vendor_mapping uvm WHERE uvm.`USER_ID`=?;",
				String.class, userId);
	}
}