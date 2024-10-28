package com.kafka.Auth;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthServiceImpl extends UnicastRemoteObject implements AuthService {

    protected AuthServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean authenticate(String username, String password) throws RemoteException {
        // Logique d'authentification simple
        return "user".equals(username) && "password".equals(password);
    }
}

