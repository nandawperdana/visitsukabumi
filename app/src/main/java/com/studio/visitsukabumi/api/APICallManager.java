package com.studio.visitsukabumi.api;

import com.studio.visitsukabumi.BuildConfig;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiService;
import com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel;
import com.studio.visitsukabumi.api.v1.aktifitas.AktifitasService;
import com.studio.visitsukabumi.api.v1.belanja.BelanjaModel;
import com.studio.visitsukabumi.api.v1.belanja.BelanjaService;
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel;
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataService;
import com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel;
import com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikService;
import com.studio.visitsukabumi.api.v1.restoran.RestoranModel;
import com.studio.visitsukabumi.api.v1.restoran.RestoranService;
import com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel;
import com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaService;
import com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel;
import com.studio.visitsukabumi.api.v1.transportasi.TransportasiService;
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
    public TransportasiManager transportasiManager;
    public SeniBudayaManager seniBudayaManager;
    public PelayananPublikManager pelayananPublikManager;
    public RestoranManager restoranManager;
    public BelanjaManager belanjaManager;
    public AktifitasManager aktifitasManager;

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
        this.transportasiManager = new TransportasiManager();
        this.seniBudayaManager = new SeniBudayaManager();
        this.pelayananPublikManager = new PelayananPublikManager();
        this.restoranManager = new RestoranManager();
        this.belanjaManager = new BelanjaManager();
        this.aktifitasManager = new AktifitasManager();
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
        public Call<List<ObjekWisataModel>> getAll() {
            ObjekWisataService service = getService(ObjekWisataService.class);
            return service.getAll();
        }

        public Call<List<ObjekWisataModel>> getById(String id) {
            ObjekWisataService service = getService(ObjekWisataService.class);
            return service.getById(id);
        }
    }

    public class TransportasiManager {
        public Call<List<TransportasiModel>> getAll() {
            TransportasiService service = getService(TransportasiService.class);
            return service.getAll();
        }

        public Call<List<TransportasiModel>> getById(String id) {
            TransportasiService service = getService(TransportasiService.class);
            return service.getById(id);
        }
    }

    public class SeniBudayaManager {
        public Call<List<SeniBudayaModel>> getAll() {
            SeniBudayaService service = getService(SeniBudayaService.class);
            return service.getAll();
        }

        public Call<List<SeniBudayaModel>> getById(String id) {
            SeniBudayaService service = getService(SeniBudayaService.class);
            return service.getById(id);
        }
    }

    public class PelayananPublikManager {
        public Call<List<PelayananPublikModel>> getAll() {
            PelayananPublikService service = getService(PelayananPublikService.class);
            return service.getAll();
        }

        public Call<List<PelayananPublikModel>> getById(String id) {
            PelayananPublikService service = getService(PelayananPublikService.class);
            return service.getById(id);
        }
    }

    public class RestoranManager {
        public Call<List<RestoranModel>> getAll() {
            RestoranService service = getService(RestoranService.class);
            return service.getAll();
        }

        public Call<List<RestoranModel>> getById(String id) {
            RestoranService service = getService(RestoranService.class);
            return service.getById(id);
        }
    }

    public class BelanjaManager {
        public Call<List<BelanjaModel>> getAll() {
            BelanjaService service = getService(BelanjaService.class);
            return service.getAll();
        }

        public Call<List<BelanjaModel>> getById(String id) {
            BelanjaService service = getService(BelanjaService.class);
            return service.getById(id);
        }
    }

    public class AktifitasManager {
        public Call<List<AktifitasModel>> getAll() {
            AktifitasService service = getService(AktifitasService.class);
            return service.getAll();
        }

        public Call<List<AktifitasModel>> getById(String id) {
            AktifitasService service = getService(AktifitasService.class);
            return service.getById(id);
        }
    }
}
