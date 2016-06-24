package com.studio.visitsukabumi.domains.models;

import com.studio.visitsukabumi.api.v1.user.UsersModel;

/**
 * Created by nandawperdana on 5/12/2016.
 */
public class UserDomain {
    UsersModel usersModel;

    public UsersModel getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(UsersModel usersModel) {
        this.usersModel = usersModel;
    }
}
