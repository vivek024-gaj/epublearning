/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akil
 */
public class CanCreateRole implements Serializable{
    private Role role;
    private List<Role> canCreateRoles;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getCanCreateRoles() {
        return canCreateRoles;
    }

    public void setCanCreateRoles(List<Role> canCreateRoles) {
        this.canCreateRoles = canCreateRoles;
    }
    
}
