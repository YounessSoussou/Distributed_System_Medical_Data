package com.kafka.AlertEtRecommendation;

import javax.jws.WebService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.config.KafkaConsumerConfig;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="com.kafka.alertAndRecommendation.AlertAndRecommendationService")
@Service
public class AlertAndRecommendationServiceImpl implements com.kafka.AlertEtRecommendation.AlertAndRecommendationService {

    private String FrequenceCardiaqueAlertMessage;
    private String SpO2AlertMessage;
    private String TemperatureAlertMessage;
    private String PressionArterielleDiastoliqueAlertMessage;
    private String PressionArterielleSystoliqueAlertMessage;
    private List<String> alerts;

    private String FrequenceCardiaqueRecommendationMessage;
    private String SpO2RecommendationMessage;
    private String TemperatureRecommendationMessage;
    private String PressionArterielleDiastoliqueRecommendationMessage;
    private String PressionArterielleSystoliqueRecommendationMessage;
    private List<String> recommendations;

    private final KafkaConsumerConfig kcc;

    public AlertAndRecommendationServiceImpl() {
    	this.kcc=new KafkaConsumerConfig();
    }

    @KafkaListener(topics="SpO2",groupId="groupId")
    public String getSpo2AlertAndRecommendation() {

        kcc.setTopicName("SpO2Alert");
        String data = kcc.getFromTopic();
        Double value = 0.0;
        try {
            value = Double.valueOf(data.replace(',', '.'));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if ((value >= 95) && (value <= 100)) {
            this.SpO2AlertMessage = "!!!- Le niveau de SpO2 est Normal";
            this.SpO2RecommendationMessage = "-> Le niveau de SpO2 est Normal !";
        } else if (value < 80) {
            this.SpO2AlertMessage = "!!!- La valeur de SpO2 est faible";
            this.SpO2RecommendationMessage = "-> Veillez consulter un professionnel de la santé";}
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(SpO2AlertMessage + "\n\"\"\"\n" + SpO2RecommendationMessage);
            return SpO2AlertMessage + "\n\"\"\"\n" + SpO2RecommendationMessage;
        }

    @KafkaListener(topics="PressionAD",groupId="adId")
    public String getPressionArterielleDiastoliqueAlertAndRecommendation() {

    	kcc.setTopicName("PressionArterielleDiastoliqueAlert");
    	String data=kcc.getFromTopic();
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=60.0) && (value<=80.0)){
            this.PressionArterielleDiastoliqueAlertMessage="!!!- La Pression Artérielle Diastolique est normal";
            this.PressionArterielleDiastoliqueRecommendationMessage="-> La valeur de La pression artérielle diastolique est très bonne !";
        }else if(value>80 && value<=89){
            this.PressionArterielleDiastoliqueAlertMessage="!!!- Pression Artérielle Diastolique faible";
            this.PressionArterielleDiastoliqueRecommendationMessage="vous avez la catégorie Hypertension stade 1. Il est recommandée de consulter un médecin";
        }else{
            this.PressionArterielleDiastoliqueAlertMessage="!!!- Pression Artérielle Diastolique trop faible";
            this.PressionArterielleDiastoliqueRecommendationMessage="-> vous avez la catégorie Hypertension stade 2. vous devez consulter un médecin";
        }
        System.out.println(PressionArterielleDiastoliqueAlertMessage+ "\n\"\"\"\n" +PressionArterielleDiastoliqueRecommendationMessage);
        return PressionArterielleDiastoliqueAlertMessage+ "\n\"\"\"\n" +PressionArterielleDiastoliqueRecommendationMessage;
    }

    @KafkaListener(topics="PressionAS",groupId="asId")
    public String getPressionArterielleSystoliqueAlertAndRecommendation() {

    	kcc.setTopicName("PressionArterielleSystoliqueAlert");
    	String data=kcc.getFromTopic();
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=60.0) && (value<=80.0)){
            this.PressionArterielleSystoliqueAlertMessage="!!!- La Pression Artérielle Systolique est normal";
            this.PressionArterielleSystoliqueRecommendationMessage="-> La valeur de La pression artérielle systolique est très bonne !";
        }else if(value>80){
            this.PressionArterielleSystoliqueAlertMessage="!!!- Pression Artérielle Systolique élevé";
            this.PressionArterielleSystoliqueRecommendationMessage="Verifier si vous avez pris votre medicament , veuillez consulter un médecin si le probléme dure longtemps";
        }else{
            this.PressionArterielleSystoliqueAlertMessage="!!!- Pression Artérielle Systolique trop faible";
            this.PressionArterielleSystoliqueRecommendationMessage="il est recommandée de : 1/Hydratation\n\t2/Élévation des Jambes\n\t3/Éviter les Changements Brusques de Position\n\t si le problème dure longtemps consulter le médecin";
        }
        System.out.println(PressionArterielleSystoliqueAlertMessage+ "\n\"\"\"\n" +PressionArterielleSystoliqueRecommendationMessage);
        return PressionArterielleSystoliqueAlertMessage+ "\n\"\"\"\n" +PressionArterielleSystoliqueRecommendationMessage;
    }

    @KafkaListener(topics="FrequenceC",groupId="fcId")
    public String getFrequenceCardiaqueAlertAndRecommendation() {

    	kcc.setTopicName("FrequenceCardiaqueAlert");
    	String data=kcc.getFromTopic();
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=60.0) && (value<=80.0)){
            this.FrequenceCardiaqueAlertMessage="!!!- La Frequence cardiaque est normal";
            this.FrequenceCardiaqueRecommendationMessage="-> La valeur de La Frequence cardiaque est très bonne !";
        }else if(value>80 && value<=89){
            this.FrequenceCardiaqueAlertMessage="!!!- Frequence cardiaque faible";
            this.FrequenceCardiaqueRecommendationMessage="vous avez la catégorie Hypertension stade 1. Il est recommandée de consulter un médecin";
        }else{
            this.FrequenceCardiaqueAlertMessage="!!!- Frequence cardiaque trop faible";
            this.FrequenceCardiaqueRecommendationMessage="-> vous avez la catégorie Hypertension stade 2. vous devez consulter un médecin";
        }
        System.out.println(FrequenceCardiaqueAlertMessage+ "\n\"\"\"\n" +FrequenceCardiaqueRecommendationMessage);
        return FrequenceCardiaqueAlertMessage+ "\n\"\"\"\n" +FrequenceCardiaqueRecommendationMessage;
    }

    @KafkaListener(topics="Temperature",groupId="tempID")
    public String getTemperatureAlertAndRecommendation() {
    	kcc.setTopicName("temperatureAlert");
    	String data=kcc.getFromTopic();
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=36.5) && (value<=37.5)){
            this.TemperatureAlertMessage="!!!- Température optimale";
            this.TemperatureRecommendationMessage="-> La valeur de la température est très bonne! ";
        }else if(value>37.5){
            this.TemperatureAlertMessage="!!!- Température est élevé";
            this.TemperatureRecommendationMessage="-> La valeur de la température est est trop élevée:\n\t1- Boire beaucoup de liquides pour rester hydraté.\n\t" +
                    "2- Se reposer et éviter les activités épuisantes.\n\t" +
                    "3- Prendre des médicaments contre la fièvre, tels que le paracétamol (acétaminophène) ou l'ibuprofène, selon les directives d'un professionnel de la santé.";
        }else{
            this.TemperatureAlertMessage="!!!- Témperature est faible";
            this.TemperatureRecommendationMessage="-> La valeur de la températureest trop basse:\n\ta: Éviter les Sources de Froid\n\t" +
                    "b: e Réchauffer Graduellement\n\t" +
                    "c: Consommer des Liquides Chauds.";
        }
        System.out.println(TemperatureAlertMessage+ "\n\"\"\"\n" +TemperatureRecommendationMessage);
        return TemperatureAlertMessage+ "\n\"\"\"\n" +TemperatureRecommendationMessage;
    }

    @Override
    public List<String> getAlerts(){
        alerts=new ArrayList<>();
        alerts.add(SpO2AlertMessage);
        alerts.add(PressionArterielleSystoliqueAlertMessage);
        alerts.add(PressionArterielleDiastoliqueAlertMessage);
        alerts.add(FrequenceCardiaqueAlertMessage);
        alerts.add(TemperatureAlertMessage);
        return alerts;
    }

    @Override
    public List<String> getRecommendations(){
        recommendations=new ArrayList<>();
        recommendations.add(SpO2RecommendationMessage);
        recommendations.add(PressionArterielleSystoliqueRecommendationMessage);
        recommendations.add(PressionArterielleDiastoliqueRecommendationMessage);
        recommendations.add(FrequenceCardiaqueRecommendationMessage);
        recommendations.add(TemperatureRecommendationMessage);
        return recommendations;
    }

}
