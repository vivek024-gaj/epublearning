package tk.vky.epublearning.dao;

import java.util.List;

import tk.vky.epublearning.model.BusinessFunction;
import tk.vky.epublearning.model.KeyValuePair;
import tk.vky.epublearning.model.Password;
import tk.vky.epublearning.model.Role;
import tk.vky.epublearning.model.User;

public interface UserDao {

	public List<User> getUserList(String userType);

	public List<KeyValuePair> getUserNameList(boolean active);

	public List<Role> getRoleList();

	public int addUser(User user);

	public void updateUser(User user);

	public User getUserByUserId(int userId);

	public boolean confirmPassword(Password password);

	public void updatePassword(Password password, int offset);

	public List<String> getBusinessFunctionsForUserId(int userId);

	public List<String> getBusinessFunctionsForRoled(String roleId);

	public int incrementFailedCountForUsername(String username);

	public void loginSuccessUpdateForUserId(int userId);

	public void resetFailedAttempts(int userId);

	public String resetPassword(int userId);

	public List<Role> getCanCreateRoleList(String roleId);

	public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId);

	public void updateCanCreateRoles(String roleId, String[] canCreateRoleIds);

	public String getUserVendorMappingListByUserId(int userId);
	
	 public List<BusinessFunction> getBusinessFunctionList();
}