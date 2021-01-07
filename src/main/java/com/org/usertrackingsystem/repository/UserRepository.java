/**
 * 
 */
package com.org.usertrackingsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.usertrackingsystem.model.User;

/**
 * @author BadGateWay
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{

	public User findByUserName(String userName);
	
	public List<User> findByLicenseKeyAndActive(String licenseKey, String active);
	
	public List<User> findByLicenseKey(String licenseKey);
}
