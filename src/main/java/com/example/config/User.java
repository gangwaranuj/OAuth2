package com.example.config;

public class User  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    
    public User() {
    	
    }


	
     

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

