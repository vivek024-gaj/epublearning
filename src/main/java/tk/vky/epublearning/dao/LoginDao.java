/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.dao;

import java.util.List;

import tk.vky.epublearning.model.CustomUserDetails;

/**
 *
 * @author akil
 */
public interface LoginDao {

    public CustomUserDetails getCustomUserByUsername(String username);
    
    public List<String> getBusinessFunctionsForUserId(int userId);
    
}
