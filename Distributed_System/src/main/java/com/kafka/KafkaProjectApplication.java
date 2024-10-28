package com.kafka;

import com.kafka.mysensor.TemperatureSensor;
import com.kafka.mysensor.FrequenceCardiaqueSensor;
import com.kafka.mysensor.SpO2Sensor;
import com.kafka.mysensor.PressionArterielleDiastoliqueSensor;
import com.kafka.mysensor.PressionArterielleSystoliqueSensor;
import com.kafka.AlertEtRecommendation.AlertAndRecommendationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(name="SensorService")
@SOAPBinding(style=Style.RPC)
@SpringBootApplication(scanBasePackages = "com.kafka")
public class KafkaProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaProjectApplication.class, args);

		// Get the KafkaTemplate bean from the application context
		KafkaTemplate<String, String> kafkaTemplate = context.getBean(KafkaTemplate.class);


// Create instances of each sensor
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		FrequenceCardiaqueSensor frequenceCardiaqueSensor = new FrequenceCardiaqueSensor();
		PressionArterielleDiastoliqueSensor pressionArterielleDiastoliqueSensor = new PressionArterielleDiastoliqueSensor();
		PressionArterielleSystoliqueSensor pressionArterielleSystoliqueSensor = new PressionArterielleSystoliqueSensor();
		SpO2Sensor spO2Sensor = new SpO2Sensor();

		AlertAndRecommendationServiceImpl ar = new AlertAndRecommendationServiceImpl();
//starting the sensor and generate the data
		while (true) {
			ar.getSpo2AlertAndRecommendation();
			spO2Sensor.captureData();

			ar.getPressionArterielleSystoliqueAlertAndRecommendation();
			pressionArterielleSystoliqueSensor.captureData();

			ar.getPressionArterielleDiastoliqueAlertAndRecommendation();
			pressionArterielleDiastoliqueSensor.captureData();

			ar.getFrequenceCardiaqueAlertAndRecommendation();
			frequenceCardiaqueSensor.captureData();

			ar.getTemperatureAlertAndRecommendation();
			temperatureSensor.captureData();
			try {
				//sleeping the thread for 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

	}
}