/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.framework;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vky.epublearning.dao.UserDao;
import tk.vky.epublearning.model.BusinessFunction;
import tk.vky.epublearning.model.Role;

/**
 *
 * @author umesh
 */
@Service
public class ApplicationLoadService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void fetchData() {
		ApplicationSession applicationSession = ApplicationSession.getCurrent();
	}

	/**
	 * Method to get the role list
	 *
	 * @return Returns the role list
	 */
	public List<Role> getRoleList() {
		return this.userDao.getRoleList();
	}

	/**
	 * Method to get the canCreateRole list from roleId
	 *
	 * @param roleId RoleId used to get who can create the roles
	 * @return Returns the canCreateRole list from roleId
	 */
	public List<Role> getCanCreateRoleList(String roleId) {
		return this.userDao.getCanCreateRoleList(roleId);
	}

	public List<String> getBusinessFunctionForRoleId(String roleId) {
		return this.userDao.getBusinessFunctionsForRoled(roleId);
	}

	public List<BusinessFunction> getBusinessFunctionList() {
		return this.userDao.getBusinessFunctionList();
	}

}
