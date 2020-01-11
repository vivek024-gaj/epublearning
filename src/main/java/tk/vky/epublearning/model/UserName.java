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
public class UserName implements Serializable {

    private int userId;
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getNameLimit() {
        if (this.name.length() > 30) {
            return this.name.substring(0, 26) + " ...,";
        } else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserName(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UserName() {
    }
}
