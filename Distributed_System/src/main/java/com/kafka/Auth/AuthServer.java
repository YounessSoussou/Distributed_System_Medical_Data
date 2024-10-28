package com.kafka.Auth;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AuthServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            AuthService authService = new AuthServiceImpl();
            Naming.rebind("rmi://localhost/AuthService", authService);
            System.out.println("AuthServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
