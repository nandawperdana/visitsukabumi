package com.studio.visitsukabumi.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.DashboardPresenter;
import com.studio.visitsukabumi.ui.dashboard.adapter.DashboardAdapter;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardModel;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardPresenterImpl;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.DashboardView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_dashboard)
    Toolbar toolbar;
    @Bind(R.id.gridview_dashboard)
    GridView gridView;

    // vars
    DashboardModel mModel;
    DashboardPresenter mPresenter;
    List<DashboardModel.Menu> mListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DashboardModel.Menu menu = mListMenu.get(position);
                doRetrieveModel().setMenu(menu.getId());
                mPresenter.presentState(ViewState.OPEN_MENU);
            }
        });
    }

    private void init() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(DashboardActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new DashboardModel();
        this.mPresenter = new DashboardPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initMenu();
        initToolbar(DashboardActivity.this, toolbar);
        gridView.setAdapter(new DashboardAdapter(this, mListMenu));
    }

    private void initToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        if (toolbar == null || appCompatActivity == null) {
            throw new IllegalArgumentException("toolbar or appCompatActivity is null");
        }
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initMenu() {
        this.mListMenu = new ArrayList<DashboardModel.Menu>();

        mListMenu.add(new DashboardModel.Menu(Enums.Menu.OBJEK_WISATA, "Objek Wisata", R.drawable.ic_objek_wisata));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.AKOMODASI, "Akomodasi", R.drawable.ic_aktivitas));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.SENI_BUDAYA, "Seni Budaya", R.drawable.ic_seni_budaya));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.RUMAH_MAKAN, "Rumah Makan", R.drawable.ic_rumah_makan));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.BELANJA, "Belanja", R.drawable.ic_belanja));
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
            case OPEN_MENU:
                openMenu(doRetrieveModel().getMenu());
                break;
            case ERROR:
                showError();
                break;
        }
    }

    @Override
    public DashboardModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = DashboardActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(DashboardActivity.this)
                            .content("")
                            .positiveText("")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    DashboardActivity.this.finish();
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

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            mProgressDialog.show();
        else mProgressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(DashboardActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }

    private void openMenu(Enums.Menu menu) {
        switch (menu) {
            case OBJEK_WISATA:
                break;
            case AKOMODASI:
                break;
            case SENI_BUDAYA:
                break;
            case RUMAH_MAKAN:
                break;
            case BELANJA:
                break;
            case TRANSPORTASI:
                break;
            case KANTOR:
                break;
            case PEMERINTAHAN:
                break;
            case LAYANAN_PUBLIK:
                break;
            case AKTIVITAS:
                break;
            case TIPS:
                break;
            case PROFIL:
                break;
        }
    }
}
