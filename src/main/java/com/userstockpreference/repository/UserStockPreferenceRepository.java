package com.userstockpreference.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.userstockpreference.model.UserPreference;
import com.userstockpreference.model.UserStockPreference;

@Repository
public interface UserStockPreferenceRepository extends MongoRepository<UserPreference, String> {

	/*
	 * @DeleteQuery void deleteByUserId(String userid);
	 * 
	 * @Query(value="{'id' : $0}", delete = true) public void deleteById (String
	 * id);
	 */
//	UserPreference getByPreference_list_name(String preference_list_name);
}