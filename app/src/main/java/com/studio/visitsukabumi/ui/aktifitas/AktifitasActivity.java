package com.studio.visitsukabumi.ui.aktifitas;

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
import com.studio.visitsukabumi.presentation.presenters.AktifitasPresenter;
import com.studio.visitsukabumi.ui.aktifitas.fragment.AktifitasFragment;
import com.studio.visitsukabumi.ui.aktifitas.mvp.AktifitasModel;
import com.studio.visitsukabumi.ui.aktifitas.mvp.AktifitasPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AktifitasActivity extends AppCompatActivity implements AktifitasPresenter.AktifitasView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_aktifitas)
    Toolbar toolbar;
    @Bind(R.id.tablayout_aktifitas)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_aktifitas)
    ViewPager viewPager;

    // vars
    AktifitasPresenter mPresenter;
    AktifitasModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktifitas);
        init();

        mPresenter.presentState(ViewState.LOAD_ITEM);

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
                        mPresenter.presentState(ViewState.LOAD_ITEM);
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
        this.mProgressDialog = new ProgressDialog(AktifitasActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new AktifitasModel();
        this.mPresenter = new AktifitasPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(AktifitasActivity.this, toolbar);
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
        actionBar.setTitle("Aktifitas");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AktifitasFragment(), "Aktifitas");

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
            if (fragment instanceof AktifitasFragment) {
                doRetrieveModel().setAktifitasFragment((AktifitasFragment) fragment);
                Log.i("AttachFragment", "fragment " + fragment.toString());
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
            case SHOW_ITEM:
                showItem();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showItem() {
        doRetrieveModel().getAktifitasFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public AktifitasModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = AktifitasActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(AktifitasActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    AktifitasActivity.this.finish();
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
        Toast.makeText(AktifitasActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
