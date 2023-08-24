package com.userstockpreference.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_STOCK_PREFERENCE")
public class UserPreference {
    @Id
    private String userId;
    private Map<String, List<String>> watchlists;
    
}