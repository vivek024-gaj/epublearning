/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.config.security;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tk.vky.epublearning.dao.LoginDao;
import tk.vky.epublearning.model.CustomUserDetails;

/**
 *
 * @author Vivek - Gajbhiye
 * 
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private LoginDao loginDao;
	private Set<String> allowedIpRange;

	public CustomUserDetailsService() {
		this.allowedIpRange = new HashSet<String>();
	}

	@Override
	@Transactional
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String ipAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRemoteAddr();
		try {
			CustomUserDetails user = this.loginDao.getCustomUserByUsername(username);
			if (!user.isActive()) {
				log.info("Account disabled");
			} else if (!user.isAccountNonLocked()) {
				log.info("Account locked");
			}
//            else if (!(user.isOutsideAccess() || checkIfIpIsFromAllowedRange(ipAddress))) {
//                user.setActive(false);
//                this.logDao.accessLog(ipAddress, username, null, false, "Outside access");
//            } 
			else {
				if (user.isPasswordExpired()) {
					// only insert the ROLE_BF_PASSWORD_EXPIRED
					log.info("Credentials are Expired so only put in ROLE_BF_PASSWORD_EXPIRED into Authoirites");
					List<String> businessFunctions = new LinkedList<String>();
					businessFunctions.add("ROLE_BF_PASSWORD_EXPIRED");
					user.setBusinessFunctionString(businessFunctions);
				} else {
					user.setBusinessFunctionString(this.loginDao.getBusinessFunctionsForUserId(user.getUserId()));
				}
			}
			return user;
		} catch (EmptyResultDataAccessException erda) {
			throw new UsernameNotFoundException("Username not found");
		}
	}
}
