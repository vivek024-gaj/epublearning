/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;

import java.io.Serializable;

/**
 *
 * @author Vivek Gajbhiye - Cropdata
 */
public class Password implements Serializable {

    private int userId;
    private String name;
    private String oldPassword;
    private String newPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
