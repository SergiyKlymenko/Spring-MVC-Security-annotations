package com.mvc.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserInfo {
	 
    private String userName;
    private String password;
    private BCryptPasswordEncoder passwordEncoder;
     
    public UserInfo()  {
         
    }
 
    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
        
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return this.passwordEncoder.encode(password);            
    }
        
    public void setPassword(String password) {
     	this.password = this.passwordEncoder.encode(password);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
    	return this.passwordEncoder;    	        
    }
 
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
}
