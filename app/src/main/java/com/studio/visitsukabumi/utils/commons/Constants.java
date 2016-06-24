package com.studio.visitsukabumi.utils.commons;

/**
 * Created by NwP.
 */
public final class Constants {
    public class Paths {
        public static final String ENDPOINT_RELEASE = "http://10.10.1.116/kartu-imunisasi-api/public/api/v1/";
        public static final String ENDPOINT_STAGING = "http://10.10.1.116/kartu-imunisasi-api/public/api/v1/";
    }

    public class Keys {
        public static final String GCM_SERVER_KEY = "AIzaSyBdmPu1VfyqsN8EAnMP0tIX7RgYO-42pHU";
    }

    public class Code {
        // shared preferences
        public static final String TAG_PREF_AUTH_TOKEN = "auth_token";

        public static final String TAG_PREF_SENT_TOKEN = "sent_token";
        public static final String TAG_PREF_REG_COMPLETED = "registration_completed";
    }
}
