package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.mvc.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;
 
public class UserInfoMapper implements RowMapper<UserInfo> {
 
	@Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        String userName = rs.getString("user_name") + " " + rs.getString("user_last_name");
        String password = rs.getString("user_password");
 
        return new UserInfo(userName, password);
    }
 
}
