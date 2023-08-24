package com.userstockpreference.config;

//Importing required classes
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.userstockpreference.model.UserStockPreference;

//Annotations
@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Value("${kafka.groupid}")
	private String groupId;
	@Value("${kafka.bootstrap-servers}")
	private String kafkaServer;
	@Value("${kafka.topic.offset}")
	private String offset;
	
	public Map commonKafkaProerty() {
		Map<String, Object> props = new HashMap<>();
	      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
	      props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	     // props.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
	      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	      props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

	      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offset);
		return props;

	}

	/*
	 * @Bean public ConsumerFactory<String, UserStockPreference>
	 * stockConsumerFactory() {
	 * 
	 * return new DefaultKafkaConsumerFactory<>(commonKafkaProerty(), new
	 * StringDeserializer(), new JsonDeserializer<>(UserStockPreference.class)); }
	 * 
	 * @Bean public ConcurrentKafkaListenerContainerFactory<String,
	 * UserStockPreference> stockPreferenceListner() {
	 * ConcurrentKafkaListenerContainerFactory<String, UserStockPreference> factory
	 * = new ConcurrentKafkaListenerContainerFactory<>();
	 * factory.setConsumerFactory(stockConsumerFactory()); return factory; }
	 */
	
	@Bean("NotificationConsumerFactory")
	  public ConsumerFactory<String, UserStockPreference> createOrderConsumerFactory() {
	
	      return new DefaultKafkaConsumerFactory<>(commonKafkaProerty(),new StringDeserializer(),
	              new JsonDeserializer<>(UserStockPreference.class));
	  }
	
	@Bean("NotificationContainerFactory")
	  public ConcurrentKafkaListenerContainerFactory<String, UserStockPreference> createOrderKafkaListenerContainerFactory() {
	      ConcurrentKafkaListenerContainerFactory<String, UserStockPreference> factory =
	              new ConcurrentKafkaListenerContainerFactory<>();
	      factory.setConsumerFactory(createOrderConsumerFactory());
	      factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
	      return factory;
	  }
	
	}
