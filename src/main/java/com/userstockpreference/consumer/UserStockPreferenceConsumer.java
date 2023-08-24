package com.userstockpreference.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.userstockpreference.constant.ConstantValue;
import com.userstockpreference.model.UserStockPreference;
import com.userstockpreference.service.UserStockPreferenceService;

import lombok.extern.slf4j.Slf4j;
  
@Component
@Slf4j
public class UserStockPreferenceConsumer {

@Autowired
private UserStockPreferenceService cserStockPreferenceService;
	
@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupid}", containerFactory = "NotificationContainerFactory")
public void consume(ConsumerRecord<String, String> payload, Acknowledgment ack)
{
	try {
	Gson gson = new Gson(); 
	UserStockPreference userStockPreference= gson.fromJson(payload.value(), UserStockPreference.class); 
	log.info("Notification service received order {} ");
	ack.acknowledge();
	if (userStockPreference.getOperation().equals(ConstantValue.CREATE_OPERATION )||userStockPreference.getOperation().equals(ConstantValue.UPDATE_OPERATION))
		{
		cserStockPreferenceService.createUpdatePreference(userStockPreference);
		log.info("create operation :" +userStockPreference);
		}
	
	else if (userStockPreference.getOperation().equals(ConstantValue.GET_OPERATION))
		{
		System.out.println("GET " + userStockPreference.getUserId());
		log.info("Get operation", userStockPreference);
		}
	
	else if (userStockPreference.getOperation().equals(ConstantValue.DELETE_OPERATION))
		{
		cserStockPreferenceService.deleteUserPreference(userStockPreference.getUserId());
		log.info("Delete operation :" +userStockPreference);
		}
	else
		log.info("Delete operation", userStockPreference);
	} catch (Exception e) {
		log.error("Consumer error consume()",e);
	}
}
}

