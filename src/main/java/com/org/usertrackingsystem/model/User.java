/**
 * 
 */
package com.org.usertrackingsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 7000018390
 *
 */
@Document(collection="user")
public class User {

	@Id
	private String id;
	private String userName;
	private String password;
	private String licenseKey;
	private String role;
	private String active;
	
	public User(String id, String userName, String password) {
	    this.id = id;
	    this.userName = userName;
	    this.password = password;
	 }
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the licenseKey
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * @param licenseKey the licenseKey to set
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	
}
