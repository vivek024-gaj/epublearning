/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vivek Gajbhiye - Cropdata
 */
public class User implements Serializable {

    private int userId;
    private String username;
    private String password;
    private int failedAttempts;
    private Date expiresOn;
    private boolean expired;
    private Date lastLoginDate;
    private Date createdDate;
    private UserName createdBy;
    private Date lastModifiedDate;
    private UserName lastModifiedBy;
    private String name;
    private Role role;
    private boolean active;
    private boolean outsideAccess;
    private List<String> businessFunctionList;

    public User() {
    }

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public User(int userId, String username, String password, int failedAttempts, Date expiresOn, boolean expired, Date lastLoginDate, Date createdDate, UserName createdBy, Date lastModifiedDate, UserName lastModifiedBy, String name, Role role, boolean active, boolean outsideAccess, List<String> businessFunctionList) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.failedAttempts = failedAttempts;
        this.expiresOn = expiresOn;
        this.expired = expired;
        this.lastLoginDate = lastLoginDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.name = name;
        this.role = role;
        this.active = active;
        this.outsideAccess = outsideAccess;
        this.businessFunctionList = businessFunctionList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isOutsideAccess() {
        return outsideAccess;
    }

    public void setOutsideAccess(boolean outsideAccess) {
        this.outsideAccess = outsideAccess;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserName getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserName createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserName getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(UserName lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBusinessFunctionList() {
        return businessFunctionList;
    }

    public void setBusinessFunctionList(List<String> businessFunctionList) {
        this.businessFunctionList = businessFunctionList;
    }

    public boolean getHasBusinessFunction(String businessFunctionId) {
        return this.businessFunctionList.contains(businessFunctionId);
    }

    public String getActiveString() {
        if (this.active == false) {
            return "Disabled";
        } else if (this.active && this.failedAttempts < 3) {
            return "Active";
        } else {
            return "Locked";
        }
    }

    public String getHasOutsideAccess() {
        if (this.outsideAccess) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", name=" + name + ", role=" + role + ", active=" + active + '}';
    }

    public String fullString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", failedAttempts=" + failedAttempts + ", expiresOn=" + expiresOn + ", expired=" + expired + ", lastLoginDate=" + lastLoginDate + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", name=" + name + ", role=" + role + ", active=" + active + ", outsideAccess=" + outsideAccess + ", businessFunctionList=" + businessFunctionList + '}';
    }
}
