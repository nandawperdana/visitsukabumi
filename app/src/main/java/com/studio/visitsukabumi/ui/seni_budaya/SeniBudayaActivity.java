package com.studio.visitsukabumi.ui.seni_budaya;

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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.SeniBudayaPresenter;
import com.studio.visitsukabumi.ui.seni_budaya.fragment.SeniBudayaFragment;
import com.studio.visitsukabumi.ui.seni_budaya.mvp.SeniBudayaModel;
import com.studio.visitsukabumi.ui.seni_budaya.mvp.SeniBudayaPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeniBudayaActivity extends AppCompatActivity implements SeniBudayaPresenter.SeniBudayaView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_seni_budaya)
    Toolbar toolbar;
    @Bind(R.id.tablayout_seni_budaya)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_seni_budaya)
    ViewPager viewPager;

    // vars
    SeniBudayaPresenter mPresenter;
    SeniBudayaModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seni_budaya);
        init();

        mPresenter.presentState(SeniBudayaPresenter.SeniBudayaView.ViewState.LOAD_SENIBUDAYA);

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
                        mPresenter.presentState(ViewState.LOAD_SENIBUDAYA);
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
        this.mProgressDialog = new ProgressDialog(SeniBudayaActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new SeniBudayaModel();
        this.mPresenter = new SeniBudayaPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(SeniBudayaActivity.this, toolbar);
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
        actionBar.setTitle("Seni Budaya");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SeniBudayaFragment(), "Seni Budaya");

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
            if (fragment instanceof SeniBudayaFragment) {
                doRetrieveModel().setSeniBudayaFragment((SeniBudayaFragment) fragment);
            }
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
            case SHOW_SENIBUDAYA:
                showSeniBudaya();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showSeniBudaya() {
        doRetrieveModel().getSeniBudayaFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public SeniBudayaModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = SeniBudayaActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(SeniBudayaActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    SeniBudayaActivity.this.finish();
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
        Toast.makeText(SeniBudayaActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
