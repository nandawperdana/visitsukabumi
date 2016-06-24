package com.studio.visitsukabumi.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.LoginPresenter;
import com.studio.visitsukabumi.ui.login.mvp.LoginModel;
import com.studio.visitsukabumi.ui.login.mvp.LoginPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView {
    LoginPresenter mPresenter;
    LoginModel mModel;
    CallbackManager mCallbackManager;

    @Bind(R.id.button_login_facebook)
    LoginButton buttonLoginFb;
    @Bind(R.id.button_login_facebook_overlay)
    Button buttonLoginOverlay;
    @Bind(R.id.button_login_email)
    Button buttonLoginEmail;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Facebook stuffs
        FacebookSdk.sdkInitialize(LoginActivity.this);
        AppEventsLogger.activateApp(this);
        this.mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        ButterKnife.bind(LoginActivity.this);
        this.progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        this.mModel = new LoginModel();
        this.mPresenter = new LoginPresenterImpl(this);
    }

    // #OnClick Region
    @OnClick(R.id.button_login_facebook_overlay)
    public void onClickLoginFb() {
        buttonLoginFb.performClick();
        mPresenter.presentState(ViewState.LOAD_LOGIN_FACEBOOK);
    }

    // End Region


    @Override
    protected void onResume() {
        super.onResume();
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(LoginActivity.this);
            if (mCallbackManager == null) {
                this.mCallbackManager = CallbackManager.Factory.create();
            }
        }
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(LoginActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(LoginActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showState(ViewState state) {
        switch (state) {
            case IDLE:
                showProgress(false);
                break;
            case LOADING:
                showProgress(true);
                break;
            case LOAD_LOGIN_FACEBOOK:
                break;
            case SHOW_LOGIN:
                showLogin();
                mPresenter.presentState(LoginPresenter.LoginView.ViewState.IDLE);
                break;
            case OPEN_MAIN:
                openMainActivity();
                break;
            case ERROR:
                mPresenter.presentState(ViewState.IDLE);
                break;
        }
    }

    @Override
    public LoginModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            progressDialog.show();
        else progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(LoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    // #ShowState method region
    private void showLogin() {
        showToast("Berhasil login facebook");
        mPresenter.presentState(ViewState.OPEN_MAIN);
    }

    public void showLoginDialog() {
        FragmentManager fm = getSupportFragmentManager();
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(fm, null);
    }

    private void openMainActivity() {
//        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//        startActivity(intent);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        finish();
    }

    // end region
}
