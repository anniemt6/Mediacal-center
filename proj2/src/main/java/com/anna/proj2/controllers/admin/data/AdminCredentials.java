package com.anna.proj2.controllers.admin.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminCredentials {

    @Value("${admin.login}")
    private String mLogin;

    @Value("${admin.password}")
    private String mPassword;

    public boolean matches(String login, String password) {
        return mLogin.equals(login) && mPassword.equals(password);
    }
}
