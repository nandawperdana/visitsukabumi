package com.studio.visitsukabumi.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.DashboardPresenter;
import com.studio.visitsukabumi.ui.akomodasi.AkomodasiActivity;
import com.studio.visitsukabumi.ui.aktifitas.AktifitasActivity;
import com.studio.visitsukabumi.ui.belanja.BelanjaActivity;
import com.studio.visitsukabumi.ui.dashboard.adapter.DashboardAdapter;
import com.studio.visitsukabumi.ui.dashboard.adapter.UnggulanSliderAdapter;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardModel;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardPresenterImpl;
import com.studio.visitsukabumi.ui.event.EventActivity;
import com.studio.visitsukabumi.ui.objek_wisata.ObjekWisataActivity;
import com.studio.visitsukabumi.ui.pelayanan_publik.PelayananPublikActivity;
import com.studio.visitsukabumi.ui.restoran.RestoranActivity;
import com.studio.visitsukabumi.ui.seni_budaya.SeniBudayaActivity;
import com.studio.visitsukabumi.ui.transportasi.TransportasiActivity;
import com.studio.visitsukabumi.utils.commons.Enums;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.DashboardView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_dashboard)
    Toolbar toolbar;
    @Bind(R.id.coordinatorlayout_dashboard)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.appbar_dashboard)
    AppBarLayout appBarLayout;
    @Bind(R.id.viewpager_dashboard)
    ViewPager viewPager;
    @Bind(R.id.indicator_dashboard)
    CirclePageIndicator circlePageIndicator;
    @Bind(R.id.recyclerview_dashboard)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    // vars
    DashboardModel mModel;
    DashboardPresenter mPresenter;
    List<DashboardModel.Menu> mListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();

        // get unggulan items
        mPresenter.presentState(ViewState.LOAD_UNGGULAN);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(DashboardActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DashboardModel.Menu menu = mListMenu.get(position);
                doRetrieveModel().setMenu(menu.getId());
                mPresenter.presentState(ViewState.OPEN_MENU);
            }
        }));

    }

    private void init() {
        initLayout();
    }

    private void initLayout() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(DashboardActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new DashboardModel();
        this.mPresenter = new DashboardPresenterImpl(this);
        initToolbar(DashboardActivity.this, toolbar);

        initMenu();

        gridLayoutManager = new GridLayoutManager(DashboardActivity.this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(new DashboardAdapter(DashboardActivity.this, mListMenu));

        viewPager.setAdapter(new UnggulanSliderAdapter(DashboardActivity.this, doRetrieveModel().getListUnggulanModel()));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(10);
        viewPager.setPageMargin(-20);

        circlePageIndicator.setViewPager(viewPager);
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
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Visit Sukabumi");
    }

    private void initMenu() {
        this.mListMenu = new ArrayList<DashboardModel.Menu>();

        // menu list
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.OBJEK_WISATA, "Objek Wisata", R.drawable.ic_objek_wisata));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.AKOMODASI, "Akomodasi", R.drawable.ic_akomodasi));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.TEMPAT_MAKAN, "Tempat Makan", R.drawable.ic_rumah_makan));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.TRANSPORTASI, "Transportasi", R.drawable.ic_transportasi));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.SENI_BUDAYA, "Seni Budaya", R.drawable.ic_seni_budaya));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.BELANJA, "Belanja", R.drawable.ic_belanja));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.MAP, "Map", R.drawable.ic_map));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.PELAYANAN_PUBLIK, "Pelayanan Publik", R.drawable.ic_pelayanan_publik));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.EVENT, "Event", R.drawable.ic_event));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.AKTIVITAS, "Aktifitas", R.drawable.ic_aktivitas));
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
            case SHOW_UNGGULAN:
                showUnggulanItems();
                mPresenter.presentState(ViewState.IDLE);
                break;
            case OPEN_MENU:
                openMenu(doRetrieveModel().getMenu());
                break;
            case OPEN_UNGGULAN_DETAILS:
                openUnggulanDetails();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void openUnggulanDetails() {

    }

    private void showUnggulanItems() {
        viewPager.getAdapter().notifyDataSetChanged();
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
                            .title(R.string.error_general_title)
                            .content(R.string.error_general_content)
                            .positiveText("OKE")
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
                openActivity(ObjekWisataActivity.class);
                break;
            case AKOMODASI:
                openActivity(AkomodasiActivity.class);
                break;
            case SENI_BUDAYA:
                openActivity(SeniBudayaActivity.class);
                break;
            case TEMPAT_MAKAN:
                openActivity(RestoranActivity.class);
                break;
            case BELANJA:
                openActivity(BelanjaActivity.class);
                break;
            case TRANSPORTASI:
                openActivity(TransportasiActivity.class);
                break;
            case PELAYANAN_PUBLIK:
                openActivity(PelayananPublikActivity.class);
                break;
            case AKTIVITAS:
                openActivity(AktifitasActivity.class);
                break;
            case EVENT:
                openActivity(EventActivity.class);
                break;
        }
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(DashboardActivity.this, activity);
        startActivity(intent);
    }
}
