package com.studio.visitsukabumi.ui.pelayanan_publik;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.PelayananPublikPresenter;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.MasjidFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.PolisiFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.PuskesmasFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.RumahSakitFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.mvp.PelayananPublikModel;
import com.studio.visitsukabumi.ui.pelayanan_publik.mvp.PelayananPublikPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PelayananPublikActivity extends AppCompatActivity implements PelayananPublikPresenter.PelayananPublikView {
    PelayananPublikPresenter mPresenter;
    PelayananPublikModel mModel;

    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_pelayanan)
    Toolbar toolbar;
    @Bind(R.id.tablayout_pelayanan)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_pelayanan)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayanan_publik);
        init();

        mPresenter.presentState(ViewState.LOAD_PELAYANANPUBLIK);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                doRetrieveModel().setPage(1);
                doRetrieveModel().setFragmentSelected(position);
                switch (position) {
                    case 0:
                        mPresenter.presentState(ViewState.LOAD_RUMAHSAKIT);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_PUSKESMAS);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_POLISI);
                        break;
                    case 3:
                        mPresenter.presentState(ViewState.LOAD_MASJID);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(PelayananPublikActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new PelayananPublikModel();
        this.mPresenter = new PelayananPublikPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(PelayananPublikActivity.this, toolbar);
        if (viewPager != null)
            setupViewPager(viewPager);
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
        actionBar.setTitle("Pelayanan Publik");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RumahSakitFragment(), "Rumah Sakit");
        viewPagerAdapter.addFragment(new PuskesmasFragment(), "Puskesmas");
        viewPagerAdapter.addFragment(new PolisiFragment(), "Kantor Polisi");
        viewPagerAdapter.addFragment(new MasjidFragment(), "Masjid");

        if (viewPager == null || tabLayout == null) {
            return;
        }
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
        //giving viewPager reference to tablayout so that the viewPager changes when tab is clicked
        tabLayout.setupWithViewPager(viewPager);
        //giving tablayout reference to viewPager so that the tablayout changes when viewPager is scrolled
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            if (fragment instanceof MasjidFragment) {
                doRetrieveModel().setMasjidFragment((MasjidFragment) fragment);
            }
            if (fragment instanceof PolisiFragment) {
                doRetrieveModel().setPolisiFragment((PolisiFragment) fragment);
            }
            if (fragment instanceof PuskesmasFragment) {
                doRetrieveModel().setPuskesmasFragment((PuskesmasFragment) fragment);
            }
            if (fragment instanceof RumahSakitFragment) {
                doRetrieveModel().setRumahSakitFragment((RumahSakitFragment) fragment);
            }
            Log.i("AttachFragment", "fragment " + fragment.toString());
        } catch (Exception e) {

        }
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
            case SHOW_RUMAHSAKIT:
                showRumahSakit();
                break;
            case SHOW_PUSKESMAS:
                showPuskesmas();
                break;
            case SHOW_POLISI:
                showPolisi();
                break;
            case SHOW_MASJID:
                showMasjid();
                break;
            case SHOW_PELAYANANPUBLIK:
                showPelayananPublik();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showPelayananPublik() {
        switch (this.viewPager.getCurrentItem()) {
            case 0:
                mPresenter.presentState(ViewState.LOAD_RUMAHSAKIT);
                break;
            case 1:
                mPresenter.presentState(ViewState.LOAD_PUSKESMAS);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_POLISI);
                break;
            case 3:
                mPresenter.presentState(ViewState.LOAD_MASJID);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_RUMAHSAKIT);
                break;
        }
    }

    private void showMasjid() {
        doRetrieveModel().getListPelayananPublikMasjid().clear();
        for (com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel item : doRetrieveModel().getListPelayananPublik()) {
            if (item.getJenis().equals(PelayananPublikModel.TAG_MASJID)) {
                doRetrieveModel().getListPelayananPublikMasjid().add(item);
            }
        }
        doRetrieveModel().getMasjidFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showPolisi() {
        doRetrieveModel().getListPelayananPublikPolisi().clear();
        for (com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel item : doRetrieveModel().getListPelayananPublik()) {
            if (item.getJenis().equals(PelayananPublikModel.TAG_POLISI)) {
                doRetrieveModel().getListPelayananPublikPolisi().add(item);
            }
        }
        doRetrieveModel().getPolisiFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showPuskesmas() {
        doRetrieveModel().getListPelayananPublikMasjid().clear();
        for (com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel item : doRetrieveModel().getListPelayananPublik()) {
            if (item.getJenis().equals(PelayananPublikModel.TAG_PUSKESMAS)) {
                doRetrieveModel().getListPelayananPublikPuskesmas().add(item);
            }
        }
        doRetrieveModel().getPuskesmasFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showRumahSakit() {
        doRetrieveModel().getListPelayananPublikMasjid().clear();
        for (com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel item : doRetrieveModel().getListPelayananPublik()) {
            if (item.getJenis().equals(PelayananPublikModel.TAG_RUMAH_SAKIT)) {
                doRetrieveModel().getListPelayananPublikRumahSakit().add(item);
            }
        }
        doRetrieveModel().getRumahSakitFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public PelayananPublikModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = PelayananPublikActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(PelayananPublikActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    PelayananPublikActivity.this.finish();
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
        Toast.makeText(PelayananPublikActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
