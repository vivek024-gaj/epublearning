/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tk.vky.epublearning.framework.ApplicationSession;
import tk.vky.epublearning.model.Role;
import tk.vky.epublearning.model.User;
import tk.vky.epublearning.model.UserName;

/**
 *
 * @author Akil Mahimwala
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int i) throws SQLException {
		ApplicationSession as = ApplicationSession.getCurrent();
		User user = new User();
		user.setUserId(rs.getInt("USER_ID"));
		user.setName(rs.getString("NAME"));
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setActive(rs.getBoolean("ACTIVE"));
		user.setExpired(rs.getBoolean("EXPIRED"));
		user.setFailedAttempts(rs.getInt("FAILED_ATTEMPTS"));
		user.setExpiresOn(rs.getTimestamp("EXPIRES_ON"));
		Role role = new Role();
		role.setRoleId(rs.getString("ROLE_ID"));
		role.setRoleName(rs.getString("ROLE_NAME"));
		user.setRole(role);
		user.setOutsideAccess(rs.getBoolean("OUTSIDE_ACCESS"));
		user.setLastLoginDate(rs.getTimestamp("LAST_LOGIN_DATE"));
		user.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
		user.setLastModifiedDate(rs.getTimestamp("LAST_MODIFIED_DATE"));
		user.setCreatedBy(new UserName(rs.getInt("CREATED_BY"), rs.getString("CREATED_BY_NAME")));
		user.setLastModifiedBy(new UserName(rs.getInt("LAST_MODIFIED_BY"), rs.getString("LAST_MODIFIED_BY_NAME")));
		user.setBusinessFunctionList(as.getBusinessFunctionsForRoleId(user.getRole().getRoleId()));
		return user;
	}
}
