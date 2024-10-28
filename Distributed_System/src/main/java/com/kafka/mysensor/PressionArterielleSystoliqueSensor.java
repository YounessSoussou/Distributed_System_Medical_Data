package com.kafka.mysensor;

import javax.jws.WebService;
import com.kafka.config.KafkaProducerConfig;
import java.util.Date;

@WebService(endpointInterface = "com.kafka.mysensor.Sensor")
public class PressionArterielleSystoliqueSensor implements Sensor {
    public final String topicName;
    public double temperatureValue;
    public final String sensorType;
    public final String alertTopic;
    public final String unit;
    public Date date;
    private final KafkaProducerConfig kpc;

    public PressionArterielleSystoliqueSensor() {
        this.topicName = "pression artérielle systolique";
        this.sensorType = "PRESSIONAS";
        this.alertTopic="PressionASSensor";
        this.unit = "mmHg";
        kpc=new KafkaProducerConfig(topicName);
    }


    @Override
    public String getData() {
        return this.toString();
    }

    @Override
    public String getSensorType() {
        return sensorType;
    }
    @Override
    public String toString() {
        return "{Date: " + this.date + ", Sensor type: " + this.sensorType + ", Value: " + String.format("%.2f",this.temperatureValue) + ", Unit: " +this.unit+ "}";
    }
    public void generateData(){
        //generate a random temperature value between 35.5 and 38.5 ºC
        this.temperatureValue=Math.random()*80+90;
        this.date=new Date();
    }

    public void captureData() {
        //generating the data from the sensor
        generateData();
        kpc.setTopicName(topicName);
        kpc.sendToTopic(toString());

        kpc.setTopicName(alertTopic);
        kpc.sendToTopic(String.format("%.2f",this.temperatureValue));

        synchronized (Sensor.SHARED_TOPIC){
            kpc.setTopicName(Sensor.SHARED_TOPIC);
            kpc.sendToTopic(toString());
        }

    }
}
