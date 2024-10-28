package com.kafka.Auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class AuthClient {

    @Bean(name = "authServiceClient")
    public RmiProxyFactoryBean authService() {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceUrl("rmi://localhost:1099/AuthService");
        proxy.setServiceInterface(AuthService.class);
        return proxy;
    }
}

