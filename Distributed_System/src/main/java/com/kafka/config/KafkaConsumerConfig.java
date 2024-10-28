package com.kafka.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerConfig {

	private String topicName;
	private final Map<String,Object> props;
	private final String bootstrapServer="localhost:9092";
	private final String consumerGroupId;
	private final KafkaConsumer<String,String> kafkaConsumer;

	public KafkaConsumerConfig() {
		this.consumerGroupId="consumerGroupId";
		props=new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,consumerGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        kafkaConsumer=new KafkaConsumer<>(props);
	}
	public KafkaConsumerConfig(String topicName) {
		this();
		this.topicName=topicName;
	}


	public String getFromTopic(){
		String messages="";
		kafkaConsumer.subscribe(Arrays.asList(topicName));
		ConsumerRecords<String,String> records=kafkaConsumer.poll(Duration.ofMillis(1000));
		for(ConsumerRecord<String,String> r : records) {
			messages+=r.value();
		}
		return messages;
	}

	public void setTopicName(String topicName) {
		this.topicName=topicName;
	}


}
