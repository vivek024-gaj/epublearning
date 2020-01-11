/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tk.vky.epublearning.dao.LoginDao;
import tk.vky.epublearning.model.CustomUserDetails;
import tk.vky.epublearning.model.rowmapper.CustomUserDetailsRowMapper;

/**
 *
 * @author akil
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Method to get Customer object from username
     * @param username
     * Username used to login 
     * @return Returns the Customer object, null if no object could be found
     */
    @Override
    public CustomUserDetails getCustomUserByUsername(String username) {
        String sqlString = "SELECT user.*, role.`ROLE_ID`, role.`ROLE_NAME` FROM `user` LEFT JOIN user_role ON user.USER_ID=user_role.USER_ID LEFT JOIN role ON role.ROLE_ID=user_role.ROLE_ID WHERE user.`USERNAME`=?";
        return this.jdbcTemplate.queryForObject(sqlString, new CustomUserDetailsRowMapper(), username);
    }

    /**
     * Method to get the list of Business functions that a userId has access to
     * @param userId
     * userId that you want to get the Business functions for
     * @return Returns a list of Business functions that the userId has access to
     */
    @Override
    public List<String> getBusinessFunctionsForUserId(int userId) {
        String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM user_role LEFT JOIN role_business_function ON user_role.ROLE_ID=role_business_function.ROLE_ID WHERE user_role.USER_ID=?";
        return this.jdbcTemplate.queryForList(sqlString, String.class, userId);
    }
    
}
