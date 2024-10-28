package com.kafka.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {
	private String topicName;
	private Map<String,Object> props;
	private String bootstrapServer="localhost:9092";
	private ProducerRecord producerRecord;
	private KafkaProducer<String,String> kafkaProducer;
	public KafkaProducerConfig(String topicName) {
		this.topicName=topicName;
		props=new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProducer=new KafkaProducer(props);
	}

	public void sendToTopic(String data) {
		producerRecord=new ProducerRecord(topicName,"data",data);
		kafkaProducer.send(producerRecord);
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}

