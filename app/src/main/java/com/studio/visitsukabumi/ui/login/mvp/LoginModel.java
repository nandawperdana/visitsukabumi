package com.studio.visitsukabumi.ui.login.mvp;

import com.studio.visitsukabumi.domains.models.UserDomain;

/**
 * Created by nandawperdana on 5/12/2016.
 */
public class LoginModel {
    public UserDomain userDomain;

    private String token;

    public LoginModel() {
        this.userDomain = new UserDomain();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
