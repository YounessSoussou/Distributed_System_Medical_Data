package com.kafka.listener;

import com.kafka.AlertEtRecommendation.AlertAndRecommendationServiceImpl;
import com.kafka.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.rmi.RemoteException ;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.ArrayList;
import java.util.List;

@RequestMapping("/kafka-api-v1")
@RestController
public class MessageController {


    @Autowired
    @Qualifier("authServiceClient")
    private AuthService authService;


    @Autowired
    KafkaListeners listener;

    @Autowired
    AlertAndRecommendationServiceImpl alertAndRecommendationManager;

    @GetMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<String>> getMessages(@RequestHeader(value = "username") String username,
                                                    @RequestHeader("password") String password) {
        try {
            if (!authService.authenticate(username, password)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(listener.getMessages());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/messages-auth")
    @ResponseBody
    public List<String> getMessage() {
        return listener.getMessages();
    }

    @GetMapping("/alerts")
    @ResponseBody
    public List<String> getAlerts(){
        List<String> alerts=alertAndRecommendationManager.getAlerts();
        return alerts;
    }

    @GetMapping("/recommendations")
    @ResponseBody
    public List<String> getRecommendations(){
        List<String> recommendations=alertAndRecommendationManager.getRecommendations();
        return recommendations;
    }
}