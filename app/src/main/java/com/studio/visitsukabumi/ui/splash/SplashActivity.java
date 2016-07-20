
package com.studio.visitsukabumi.ui.splash;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.BuildConfig;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.SplashPresenter;
import com.studio.visitsukabumi.ui.dashboard.DashboardActivity;
import com.studio.visitsukabumi.ui.login.LoginActivity;
import com.studio.visitsukabumi.ui.splash.mvp.SplashModel;
import com.studio.visitsukabumi.ui.splash.mvp.SplashPresenterImpl;
import com.studio.visitsukabumi.utils.commons.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.SplashView {
    SplashModel mModel;
    SplashPresenter mPresenter;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();

        mPresenter.presentState(ViewState.SHOW_SPLASH);
    }

    private void init() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(SplashActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new SplashModel();
        this.mPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public void showState(ViewState state) {
        switch (state) {
            case IDLE:
                showProgress(false);
                break;
            case LOADING:
                showProgress(false);
                break;
            case SHOW_SPLASH:
                showSplash();
                break;
            case OPEN_LOGIN:
                openLogin();
                break;
            case OPEN_DASHBOARD:
                openDashboard();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    @Override
    public SplashModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            mProgressDialog.show();
        else mProgressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(SplashActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Context con = SplashActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(SplashActivity.this)
                            .content("")
                            .positiveText("")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    SplashActivity.this.finish();
                                }
                            })
                            .autoDismiss(false)
                            .contentColor(getResources().getColor(R.color.black)) // notice no 'res' postfix for literal color
                            .backgroundColorRes(R.color.white)
                            .show();
                }
            });

        }
    }

    private void openLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void openDashboard() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void showSplash() {
        // with timer
        showSplashTimer(3000);

        // connect to database
//        new DownloadWebpageTask().execute();
    }

    private void showSplashTimer(int timeout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, timeout);
    }

    private boolean isServerActive() {
        if (isOnline()) {
            try {
                URL url;
                if (BuildConfig.BUILD_RELEASE)
                    url = new URL((Constants.Paths.ENDPOINT_RELEASE).replace("/public/api/v1", ""));
                else
                    url = new URL((Constants.Paths.ENDPOINT_STAGING).replace("/public/api/v1", ""));
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(45 * 1000);          // in s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.wtf("Connection", "Success !");
                    return true;
                } else return false;
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        } else
            return false;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private class DownloadWebpageTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            return isServerActive();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            doRetrieveModel().setIsServerActive(aBoolean);
            if (aBoolean) {
                mPresenter.presentState(ViewState.OPEN_DASHBOARD);
            } else {
                mPresenter.presentState(ViewState.ERROR);
                return;
            }
        }
    }
}
