package com.studio.visitsukabumi.ui.akomodasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.adapter.ViewPagerAdapter;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AkomodasiActivity extends AppCompatActivity implements AkomodasiPresenter.AkomodasiView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_akomodasi)
    Toolbar toolbar;
    @Bind(R.id.tablayout_akomodasi)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_akomodasi)
    ViewPager viewPager;

    // vars
    AkomodasiModel mModel;
    AkomodasiPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akomodasi);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(AkomodasiActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new AkomodasiModel();
        this.mPresenter = new AkomodasiPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(AkomodasiActivity.this, toolbar);
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HotelBintangFragment(), "Bintang");
//        viewPagerAdapter.addFragment(new DipakaiFragment(), getResources().getString(R.string.belanjaan_tab_history));
//        viewPagerAdapter.addFragment(new KadaluarsaFragment(), getResources().getString(R.string.belanjaan_tab_expired));

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
    public void showState(ViewState state) {

    }

    @Override
    public AkomodasiModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = AkomodasiActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(AkomodasiActivity.this)
                            .content("")
                            .positiveText("")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    AkomodasiActivity.this.finish();
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
        Toast.makeText(AkomodasiActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
