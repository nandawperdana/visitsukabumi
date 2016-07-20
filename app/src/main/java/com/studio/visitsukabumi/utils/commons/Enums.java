package com.studio.visitsukabumi.utils.commons;

/**
 * Created by NwP.
 */
public class Enums {

    public enum APIRoute {
        LOGIN, SIGN_UP,
        CHILDREN_CREATE, CHILDREN_EDIT, CHILDREN_GET, CHILDREN_GET_HISTORIES, CHILDREN_GET_HISTORY, CHILDREN_UPLOAD,
        USERS_EDIT, USERS_GET_CHILDREN, USERS_UPLOAD, HISTORIES_GET, HISTORIES_EDIT, ATTRIBUTES_EDIT, ATTRIBUTES_CREATE, ATTRIBUTES_GET, USERS_GET
    }

    public enum Menu {
        OBJEK_WISATA, AKOMODASI, SENI_BUDAYA, TEMPAT_MAKAN, BELANJA,
        TRANSPORTASI, KANTOR, PEMERINTAHAN, LAYANAN_PUBLIK, AKTIVITAS, TIPS, PELAYANAN_PUBLIK, MAP, EVENT, PROFIL
    }
}
