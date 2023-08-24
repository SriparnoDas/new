package com.userstockpreference.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userstockpreference.model.UserPreference;
import com.userstockpreference.model.UserStockPreference;
import com.userstockpreference.repository.UserStockPreferenceRepository;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class UserStockPreferenceService {
	
	  private final UserStockPreferenceRepository userPreferenceRepository;

	
	  
	    @Autowired
	    public UserStockPreferenceService(UserStockPreferenceRepository userPreferenceRepository) {
	        this.userPreferenceRepository = userPreferenceRepository;
	    }

	    public Optional<UserPreference> getUserPreference(String userId) {
	        return userPreferenceRepository.findById(userId);
	    }

	    public UserPreference createUpdatePreference(UserStockPreference userStockPreference) {
	    	log.info("inside >>>createUpdatePreference "+userStockPreference);
	    	UserPreference userPreference= new UserPreference();
	    	userPreference.setUserId(userStockPreference.getUserId());
	    	Map<String, List<String>> m= new HashMap<>();
    		m.put(userStockPreference.getWatchList(),userStockPreference.getStock());
    		userPreference.setWatchlists(m);
	    	Optional<UserPreference> userPreferences =getUserPreference(userStockPreference.getUserId());
	    	
	    	if(!userPreferences.isEmpty())
	    	{
	    		m=userPreferences.get().getWatchlists();
	    		List stocks= m.get(userStockPreference.getWatchList());
	    		if(stocks!=null) {
	    		stocks.addAll(userStockPreference.getStock());
	    		Set<String> s= new HashSet<>(stocks);
	    		stocks= new ArrayList<>(s);
	    		userPreferences.get().getWatchlists().put(userStockPreference.getWatchList(),stocks);
	    		userPreference=userPreferences.get();
	    		}
	    		
	    		else
	    			userPreferences.get().getWatchlists().put(userStockPreference.getWatchList(),userStockPreference.getStock());
	    			userPreference=userPreferences.get();
	    	}
	    	
	    	return userPreferenceRepository.save(userPreference);
	    }

	    public void deleteUserPreference(String userId) {
	        userPreferenceRepository.deleteById(userId);
	    }

	   
//	    }
	}