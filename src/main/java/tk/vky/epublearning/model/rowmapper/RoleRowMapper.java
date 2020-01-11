/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tk.vky.epublearning.model.Role;

/**
 *
 * @author Akil Mahimwala
 */
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getString("ROLE_ID"));
        role.setRoleName(rs.getString("ROLE_NAME"));
        return role;
    }

}
