/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;


import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tk.vky.epublearning.utils.DateUtils;

/**
 *
 *@author Vivek Gajbhiye - Cropdata
 */
public class CustomUserDetails implements UserDetails {

    private int userId;
    private String username;
    private String name;
    private String password;
    private boolean active;
    private boolean expired;
    private Date expiresOn;
    private int failedAttempts;
    private boolean outsideAccess;
    private List<SimpleGrantedAuthority> businessFunction;
    private Role role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SimpleGrantedAuthority> getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(List<SimpleGrantedAuthority> businessFunction) {
        this.businessFunction = businessFunction;
    }

    public void setBusinessFunctionString(List<String> businessFunction) {
        this.businessFunction = new LinkedList<>();
        for (String b : businessFunction) {
            this.businessFunction.add(new SimpleGrantedAuthority(b));
        }
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.businessFunction;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.failedAttempts >= 3) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isPasswordExpired() {
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        if (DateUtils.compareDates(DateUtils.formatDate(this.expiresOn, DateUtils.YMD), curDate) > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isOutsideAccess() {
        return outsideAccess;
    }

    public void setOutsideAccess(boolean outsideAccess) {
        this.outsideAccess = outsideAccess;
    }

    public boolean hasBusinessFunction(String bf) {
        if (this.businessFunction == null || this.businessFunction.isEmpty()) {
            return false;
        } else {
            SimpleGrantedAuthority b = new SimpleGrantedAuthority(bf);
            return this.businessFunction.contains(b);
        }
    }

    @Override
    public String toString() {
        return this.name + " (" + this.username + ')' + " Role:"+this.role.getRoleId();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
