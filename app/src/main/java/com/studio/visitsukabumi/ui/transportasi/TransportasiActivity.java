package com.studio.visitsukabumi.ui.transportasi;

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
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.TransportasiPresenter;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;
import com.studio.visitsukabumi.ui.transportasi.fragment.PelabuhanFragment;
import com.studio.visitsukabumi.ui.transportasi.fragment.StasiunFragment;
import com.studio.visitsukabumi.ui.transportasi.fragment.TerminalFragment;
import com.studio.visitsukabumi.ui.transportasi.mvp.TransportasiModel;
import com.studio.visitsukabumi.ui.transportasi.mvp.TransportasiPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TransportasiActivity extends AppCompatActivity implements TransportasiPresenter.TransportasiView {
    TransportasiPresenter mPresenter;
    TransportasiModel mModel;

    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_transportasi)
    Toolbar toolbar;
    @Bind(R.id.tablayout_transportasi)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_transportasi)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);

        init();

        mPresenter.presentState(ViewState.LOAD_TRANSPORTASI);
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
                        mPresenter.presentState(ViewState.LOAD_TERMINAL);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_STASIUN);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_PELABUHAN);
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
        this.mProgressDialog = new ProgressDialog(TransportasiActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new TransportasiModel();
        this.mPresenter = new TransportasiPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(TransportasiActivity.this, toolbar);
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
        actionBar.setTitle("Transportasi");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TerminalFragment(), "Terminal");
        viewPagerAdapter.addFragment(new StasiunFragment(), "Stasiun");
        viewPagerAdapter.addFragment(new PelabuhanFragment(), "Pelabuhan");

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            if (fragment instanceof TerminalFragment) {
                doRetrieveModel().setTerminalFragment((TerminalFragment) fragment);
            }
            if (fragment instanceof StasiunFragment) {
                doRetrieveModel().setStasiunFragment((StasiunFragment) fragment);
            }
            if (fragment instanceof PelabuhanFragment) {
                doRetrieveModel().setPelabuhanFragment((PelabuhanFragment) fragment);
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
            case SHOW_TERMINAL:
                showTerminal();
                break;
            case SHOW_STASIUN:
                showStasiun();
                break;
            case SHOW_PELABUHAN:
                showPelabuhan();
                break;
            case SHOW_TRANSPORTASI:
                showTransportasi();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showStasiun() {
        doRetrieveModel().getListTransportasiStasiun().clear();
        for (com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel item : doRetrieveModel().getListTransportasi()) {
            if (item.getJenis().equals(TransportasiModel.TAG_STASIUN)) {
                doRetrieveModel().getListTransportasiStasiun().add(item);
            }
        }
        doRetrieveModel().getStasiunFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showTerminal() {
        doRetrieveModel().getListTransportasiTerminal().clear();
        for (com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel item : doRetrieveModel().getListTransportasi()) {
            if (item.getJenis().equals(TransportasiModel.TAG_TERMINAL)) {
                doRetrieveModel().getListTransportasiTerminal().add(item);
            }
        }
        doRetrieveModel().getTerminalFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showPelabuhan() {
        doRetrieveModel().getListTransportasiPelabuhan().clear();
        for (com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel item : doRetrieveModel().getListTransportasi()) {
            if (item.getJenis().equals(TransportasiModel.TAG_PELABUHAN)) {
                doRetrieveModel().getListTransportasiPelabuhan().add(item);
            }
        }
        doRetrieveModel().getPelabuhanFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showTransportasi() {
        switch (this.viewPager.getCurrentItem()) {
            case 1:
                mPresenter.presentState(ViewState.LOAD_TERMINAL);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_STASIUN);
                break;
            case 3:
                mPresenter.presentState(ViewState.LOAD_PELABUHAN);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_TERMINAL);
                break;
        }
    }

    @Override
    public TransportasiModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = TransportasiActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(TransportasiActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    TransportasiActivity.this.finish();
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
        Toast.makeText(TransportasiActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
