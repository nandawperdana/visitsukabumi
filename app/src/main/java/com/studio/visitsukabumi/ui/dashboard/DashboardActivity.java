package com.studio.visitsukabumi.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.DashboardPresenter;
import com.studio.visitsukabumi.ui.akomodasi.AkomodasiActivity;
import com.studio.visitsukabumi.ui.dashboard.adapter.DashboardAdapter;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardModel;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardPresenterImpl;
import com.studio.visitsukabumi.utils.commons.Enums;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;

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
    //    @Bind(R.id.viewpager_dashboard)
//    ViewPager viewPager;
//    @Bind(R.id.indicator_dashboard)
//    CirclePageIndicator circlePageIndicator;
//    @Bind(R.id.gridview_dashboard)
//    GridView gridView;
    @Bind(R.id.recyclerview_dashboard)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    @Bind(R.id.imageview_dashboard_backdrop)
    ImageView imageView;

    // vars
    DashboardModel mModel;
    DashboardPresenter mPresenter;
    List<DashboardModel.Menu> mListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(DashboardActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DashboardModel.Menu menu = mListMenu.get(position);
                doRetrieveModel().setMenu(menu.getId());
                mPresenter.presentState(ViewState.OPEN_MENU);
            }
        }));

//        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DashboardModel.Menu menu = mListMenu.get(position);
//                doRetrieveModel().setMenu(menu.getId());
//                mPresenter.presentState(ViewState.OPEN_MENU);
//            }
//        });
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
//        gridView.setAdapter(new DashboardAdapter(this, mListMenu));
        Picasso.with(DashboardActivity.this)
                .load("https://sukabumionline.files.wordpress.com/2011/10/sukabumi-dalam-bingkai.jpg")
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
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
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.TIPS, "Tips Berwisata", R.drawable.ic_pelayanan_publik));
        mListMenu.add(new DashboardModel.Menu(Enums.Menu.PROFIL, "Profil", R.drawable.ic_objek_wisata));
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
                openActivity(AkomodasiActivity.class);
                break;
            case AKOMODASI:
                openActivity(AkomodasiActivity.class);
                break;
            case SENI_BUDAYA:
                openActivity(AkomodasiActivity.class);
                break;
            case TEMPAT_MAKAN:
                openActivity(AkomodasiActivity.class);
                break;
            case BELANJA:
                openActivity(AkomodasiActivity.class);
                break;
            case TRANSPORTASI:
                openActivity(AkomodasiActivity.class);
                break;
            case KANTOR:
                openActivity(AkomodasiActivity.class);
                break;
            case PEMERINTAHAN:
                openActivity(AkomodasiActivity.class);
                break;
            case LAYANAN_PUBLIK:
                openActivity(AkomodasiActivity.class);
                break;
            case AKTIVITAS:
                openActivity(AkomodasiActivity.class);
                break;
            case TIPS:
                openActivity(AkomodasiActivity.class);
                break;
            case PROFIL:
                openActivity(AkomodasiActivity.class);
                break;
        }
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(DashboardActivity.this, activity);
        startActivity(intent);
    }
}
