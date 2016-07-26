package com.studio.visitsukabumi.ui.restoran;

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
import com.studio.visitsukabumi.presentation.presenters.RestoranPresenter;
import com.studio.visitsukabumi.ui.restoran.fragment.KafeFragment;
import com.studio.visitsukabumi.ui.restoran.fragment.RestoranFragment;
import com.studio.visitsukabumi.ui.restoran.fragment.RumahMakanFragment;
import com.studio.visitsukabumi.ui.restoran.mvp.RestoranModel;
import com.studio.visitsukabumi.ui.restoran.mvp.RestoranPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestoranActivity extends AppCompatActivity implements RestoranPresenter.RestoranView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_restoran)
    Toolbar toolbar;
    @Bind(R.id.tablayout_restoran)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_restoran)
    ViewPager viewPager;

    // vars
    RestoranPresenter mPresenter;
    RestoranModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);
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
                        mPresenter.presentState(ViewState.LOAD_RESTORAN);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_RUMAHMAKAN);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_KAFE);
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
        this.mProgressDialog = new ProgressDialog(RestoranActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new RestoranModel();
        this.mPresenter = new RestoranPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(RestoranActivity.this, toolbar);
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
        actionBar.setTitle("Tempat Makan");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RumahMakanFragment(), "Rumah Makan");
        viewPagerAdapter.addFragment(new RestoranFragment(), "Restoran");
        viewPagerAdapter.addFragment(new KafeFragment(), "Kafe");

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
            if (fragment instanceof RumahMakanFragment) {
                doRetrieveModel().setRumahMakanFragment((RumahMakanFragment) fragment);
            }
            if (fragment instanceof KafeFragment) {
                doRetrieveModel().setKafeFragment((KafeFragment) fragment);
            }
            if (fragment instanceof RestoranFragment) {
                doRetrieveModel().setRestoranFragment((RestoranFragment) fragment);
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
            case SHOW_ITEM:
                showItem();
                break;
            case SHOW_RESTORAN:
                showRestoran();
                break;
            case SHOW_RUMAHMAKAN:
                showRumahMakan();
                break;
            case SHOW_KAFE:
                showKafe();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showItem() {
        switch (this.viewPager.getCurrentItem()) {
            case 0:
                mPresenter.presentState(ViewState.LOAD_RUMAHMAKAN);
                break;
            case 1:
                mPresenter.presentState(ViewState.LOAD_RESTORAN);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_KAFE);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_RUMAHMAKAN);
                break;
        }
    }

    private void showRestoran() {
        doRetrieveModel().getListItemRestoran().clear();
        for (com.studio.visitsukabumi.api.v1.restoran.RestoranModel item : doRetrieveModel().getListItems()) {
            if (item.getJenis().equals(RestoranModel.TAG_RESTORAN)) {
                doRetrieveModel().getListItemRestoran().add(item);
            }
        }
        doRetrieveModel().getRestoranFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showRumahMakan() {
        doRetrieveModel().getListItemRumahMakan().clear();
        for (com.studio.visitsukabumi.api.v1.restoran.RestoranModel item : doRetrieveModel().getListItems()) {
            if (item.getJenis().equals(RestoranModel.TAG_RUMAHMAKAN)) {
                doRetrieveModel().getListItemRumahMakan().add(item);
            }
        }
        doRetrieveModel().getRumahMakanFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showKafe() {
        doRetrieveModel().getListItemKafe().clear();
        for (com.studio.visitsukabumi.api.v1.restoran.RestoranModel item : doRetrieveModel().getListItems()) {
            if (item.getJenis().equals(RestoranModel.TAG_KAFE)) {
                doRetrieveModel().getListItemKafe().add(item);
            }
        }
        doRetrieveModel().getKafeFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public RestoranModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = RestoranActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(RestoranActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    RestoranActivity.this.finish();
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
        Toast.makeText(RestoranActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
