package com.userstockpreference.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TestJson {

	
	  public static void main(String[] args) { List l= new ArrayList();
	  Map<String, String> m= new HashMap(); 
	  m.put("qw", "11");
	  m.put("ww", "qq");
	  
	  
	  
	  System.out.println(new Gson().toJson(m));
	  
	  }
	 

}
