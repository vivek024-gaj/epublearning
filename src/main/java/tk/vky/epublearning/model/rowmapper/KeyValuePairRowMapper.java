/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tk.vky.epublearning.model.KeyValuePair;

/**
 *
 * @author akil
 */
public class KeyValuePairRowMapper implements RowMapper<KeyValuePair>{

    @Override
    public KeyValuePair mapRow(ResultSet rs, int rows) throws SQLException {
        return new KeyValuePair(rs.getString("key"), rs.getString("value"));
    }
    
}
