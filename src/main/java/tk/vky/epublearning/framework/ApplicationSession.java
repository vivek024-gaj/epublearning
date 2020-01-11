/**
 * 
 */
package tk.vky.epublearning.framework;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tk.vky.epublearning.model.BusinessFunction;
import tk.vky.epublearning.model.Role;
import tk.vky.epublearning.utils.StringUtils;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         09-Jan-2020
 */
@Component
@Scope("application")
public class ApplicationSession {

	@Autowired
	private ApplicationLoadService applicationLoadService;
	private final Map<String, List<String>> businessFunctionRoleMap = new HashMap<>();
	private List<BusinessFunction> businessFunctionList = new LinkedList<>();
	private List<Role> roleList = new LinkedList<>();

	/**
	 * Method to get the current application session
	 *
	 * @return Returns the session of applicationContext from the class
	 */
	public static ApplicationSession getCurrent() {
		return ApplicationContextProvider.getApplicationContext().getBean(ApplicationSession.class);
	}

	public void reloadRoleList() {
		this.roleList = null;
		this.businessFunctionRoleMap.clear();
		this.roleList = applicationLoadService.getRoleList();
		for (Role r : this.roleList) {
			this.businessFunctionRoleMap.put(r.getRoleId(),
					applicationLoadService.getBusinessFunctionForRoleId(r.getRoleId()));
		}
	}

	/**
	 * Method to get the role list
	 *
	 * @return Returns the role list
	 */
	public List<Role> getRoleList() {
		if (this.roleList != null && !this.roleList.isEmpty()) {
			return this.roleList;
		} else {
			this.roleList = applicationLoadService.getRoleList();
			return this.roleList;
		}
	}

	/**
	 * Method to get the role from roleId
	 *
	 * @param roleId
	 * @return Returns role object
	 */
	public Role getRoleById(String roleId) {
		if (this.roleList == null || this.roleList.isEmpty()) {
			reloadRoleList();
		}
		Role role;
		if (StringUtils.isBlank(roleId)) {
			return null;
		}
		role = new Role();
		role.setRoleId(roleId);
		int idx = this.roleList.indexOf(role);
		if (idx >= 0) {
			return this.roleList.get(idx);
		} else {
			return null;
		}
	}

	public List<Role> getCanCreateRoleList(String roleId) {
		return applicationLoadService.getCanCreateRoleList(roleId);
	}

	public List<String> getBusinessFunctionsForRoleId(String roleId) {
		if (this.businessFunctionRoleMap.isEmpty()) {
			reloadRoleList();
		}
		return this.businessFunctionRoleMap.get(roleId);
	}

	public void reloadBusinessFunctionList() {
		this.businessFunctionList = null;
		this.businessFunctionList = applicationLoadService.getBusinessFunctionList();
	}

	public List<BusinessFunction> getBusinessFunctionList() {
		if (this.businessFunctionList == null || this.businessFunctionList.isEmpty()) {
			reloadBusinessFunctionList();
		}
		return this.businessFunctionList;
	}
}
