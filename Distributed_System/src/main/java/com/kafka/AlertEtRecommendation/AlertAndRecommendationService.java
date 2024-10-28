package com.kafka.AlertEtRecommendation;

import javax.jws.WebMethod;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;

@WebService(name="AlertAndRecommendation",serviceName="AlertService",portName="AlertPort")
@SOAPBinding(style= Style.RPC)
public interface AlertAndRecommendationService {
    @WebMethod(operationName = "alerts")
    @WebResult(name="alerts")
    List<String> getAlerts();
    @WebMethod(operationName = "recommendations")
    @WebResult(name="recommendations")
    List<String> getRecommendations();

}

