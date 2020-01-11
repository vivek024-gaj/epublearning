/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tk.vky.epublearning.model.BusinessFunction;


/**
 *
 * @author akil
 */
public class BusinessFunctionRowMapper implements RowMapper<BusinessFunction> {

    @Override
    public BusinessFunction mapRow(ResultSet rs, int rows) throws SQLException {
        BusinessFunction bf = new BusinessFunction();
        bf.setBusinessFunction(rs.getString("BUSINESS_FUNCTION"));
        bf.setBusinessFunctionDesc(rs.getString("BUSINESS_FUNCTION_DESC"));
        return bf;
    }
}
