package com.mvc.dao;

import java.util.List;

import com.mvc.model.UserInfo;
 
public interface UserInfoDAO {
     
    public UserInfo findUserInfo(String userLogin);
     
    // [USER,AMIN,..]
    public List<String> getUserRoles(String userLogin);
    
}