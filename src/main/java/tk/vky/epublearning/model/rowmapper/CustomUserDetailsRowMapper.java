/**
 * 
 */
package tk.vky.epublearning.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tk.vky.epublearning.model.CustomUserDetails;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 * 02-Jan-2020
 */
public class CustomUserDetailsRowMapper implements RowMapper<CustomUserDetails> {

    @Override
    public CustomUserDetails mapRow(ResultSet rs, int i) throws SQLException {
        CustomUserDetails user = new CustomUserDetails();
        user.setUserId(rs.getInt("USER_ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setName(rs.getString("NAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setActive(rs.getBoolean("ACTIVE"));
        user.setExpired(rs.getBoolean("EXPIRED"));
        user.setFailedAttempts(rs.getInt("FAILED_ATTEMPTS"));
        user.setExpiresOn(rs.getTimestamp("EXPIRES_ON"));
        user.setOutsideAccess(rs.getBoolean("OUTSIDE_ACCESS"));
        user.setRole(new RoleRowMapper().mapRow(rs, i));
        System.out.println(user);
        return user;
    }
}
