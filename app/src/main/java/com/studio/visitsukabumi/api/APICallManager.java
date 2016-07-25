package com.studio.visitsukabumi.api;

import com.studio.visitsukabumi.BuildConfig;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiService;
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataService;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanService;
import com.studio.visitsukabumi.api.v1.user.UserService;
import com.studio.visitsukabumi.api.v1.user.UsersModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NwP.
 */
public class APICallManager {
    public String mEndPoint = Constants.Paths.ENDPOINT_RELEASE;
    public static APICallManager mInstance;
    private static Retrofit mRetrofit;

    private final String mContentType = "application/json";
    private String authorizationKey;

    public UserManager userManager;
    public UnggulanManager unggulanManager;
    public AkomodasiManager akomodasiManager;
    public ObjekWisataManager objekWisataManager;

    /**
     * singleton class instance
     *
     * @return APICallManager
     */
    public static APICallManager getInstance() {
        if (mInstance == null) {
            synchronized (APICallManager.class) {
                if (mInstance == null) {
                    mInstance = new APICallManager();
                }
            }
        }
        return mInstance;
    }

    public APICallManager() {
        if (BuildConfig.BUILD_RELEASE)
            mEndPoint = Constants.Paths.ENDPOINT_RELEASE;

        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // registering API endpoint manager
        this.userManager = new UserManager();
        this.unggulanManager = new UnggulanManager();
        this.akomodasiManager = new AkomodasiManager();
        this.objekWisataManager = new ObjekWisataManager();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public class UserManager {
        public Call<UsersModel> login(String email, String password) {
            UserService service = getService(UserService.class);
            return service.login(email, password);
        }

        public Call<UsersModel> login(String token) {
            UserService service = getService(UserService.class);
            return service.login(token);
        }

        public Call<UsersModel> signUp(String email, String password) {
            UserService service = getService(UserService.class);
            return service.signUp(email, password);
        }

        public Call<UsersModel> getUsers() {
            UserService service = getService(UserService.class);
            return service.getUsers();
        }

        public Call<UsersModel> edit(Integer id, String name, String country, String state,
                                     String address, String phone, String birthPlace,
                                     String birthDate) {
            UserService service = getService(UserService.class);
            return service.putEdit(getAuthorizationKey(), id, name, country, state, address, phone,
                    birthPlace, birthDate);
        }

        public Call<UsersModel> getUser(Integer id) {
            UserService service = getService(UserService.class);
            return service.getUser(getAuthorizationKey(), id);
        }

        public Call<UsersModel> uploadImage(RequestBody image) {
            UserService service = getService(UserService.class);
            return service.postUploadImage(getAuthorizationKey(), image);
        }

        public Call<UsersModel> editUserGCMId(String snsRegistrationId) {
            UserService userService = getService(UserService.class);
            return userService.putUsersSNSRegistrationId(getAuthorizationKey(), snsRegistrationId);
        }
    }

    public class AkomodasiManager {
        public Call<List<AkomodasiModel>> getAll() {
            AkomodasiService service = getService(AkomodasiService.class);
            return service.getAll();
        }

        public Call<AkomodasiModel> getById(String id) {
            AkomodasiService service = getService(AkomodasiService.class);
            return service.getById(id);
        }
    }

    public class UnggulanManager {
        public Call<List<UnggulanModel>> getAll() {
            UnggulanService service = getService(UnggulanService.class);
            return service.getAll();
        }

        public Call<List<UnggulanModel>> getById(String id) {
            UnggulanService service = getService(UnggulanService.class);
            return service.getById(id);
        }
    }

    public class ObjekWisataManager {
        public Call<List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel>> getAll() {
            ObjekWisataService service = getService(ObjekWisataService.class);
            return service.getAll();
        }

        public Call<List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel>> getById(String id) {
            ObjekWisataService service = getService(ObjekWisataService.class);
            return service.getById(id);
        }
    }
}
