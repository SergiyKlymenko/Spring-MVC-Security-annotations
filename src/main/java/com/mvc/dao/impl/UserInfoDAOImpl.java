package com.mvc.dao.impl;

import java.util.List;

import javax.sql.DataSource;
 
import com.mvc.dao.UserInfoDAO;
import com.mvc.mapper.UserInfoMapper;
import com.mvc.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {
 
	private BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
    public UserInfoDAOImpl(DataSource dataSource, BCryptPasswordEncoder passwordEncoder) {
        this.setDataSource(dataSource);
        this.passwordEncoder = passwordEncoder;
    }
  
 
    @Override
    public UserInfo findUserInfo(String userLogin) {
    	String sql = "SELECT u.user_name, u.user_last_name, u.user_password "
                 + " FROM \"Users\" u WHERE u.user_login = ?";
    	 
        Object[] params = new Object[] { userLogin };
        UserInfoMapper mapper = new UserInfoMapper();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            userInfo.setPasswordEncoder(passwordEncoder);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
 
    @Override
    public List<String> getUserRoles(String userLogin) {
        String sql = "SELECT ur.user_role "//
                + " FROM \"User_roles\" ur LEFT JOIN \"Users\" u ON ur.id_user = u.id_user" 
                + " WHERE u.user_login = ?";
         
        Object[] params = new Object[] { userLogin };
        try { 
        	List<String> roles = this.getJdbcTemplate().queryForList(sql,params, String.class);
        return roles;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
     
}