package com.userstockpreference.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.userstockpreference.model.UserPreference;
import com.userstockpreference.repository.UserStockPreferenceRepository;


import com.userstockpreference.repository.UserStockPreferenceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ResponseStatus(HttpStatus.NOT_FOUND)
 class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
@Service
public class UserPreferenceService {
    private final UserStockPreferenceRepository userPreferenceRepository;

    @Autowired
    public UserPreferenceService(UserStockPreferenceRepository userPreferenceRepository) {
        this.userPreferenceRepository = userPreferenceRepository;
    }

   
}