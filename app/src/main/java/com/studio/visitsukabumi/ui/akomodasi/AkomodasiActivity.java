package com.studio.visitsukabumi.ui.akomodasi;

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
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelNonBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.fragment.PondokFragment;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

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
    com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel mModel;
    AkomodasiPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akomodasi);

        init();

        mPresenter.presentState(ViewState.LOAD_AKOMODASI);

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
                        mPresenter.presentState(ViewState.LOAD_HOTEL_BINTANG);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_HOTEL_NONBINTANG);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_PONDOK);
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
        this.mProgressDialog = new ProgressDialog(AkomodasiActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel();
        this.mPresenter = new AkomodasiPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(AkomodasiActivity.this, toolbar);
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
        actionBar.setTitle("Akomodasi");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HotelBintangFragment(), "Hotel Bintang");
        viewPagerAdapter.addFragment(new HotelNonBintangFragment(), "Hotel Non Bintang");
        viewPagerAdapter.addFragment(new PondokFragment(), "Pondok");

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
            if (fragment instanceof HotelBintangFragment) {
                doRetrieveModel().setHotelBintangFragment((HotelBintangFragment) fragment);
            }
            if (fragment instanceof HotelNonBintangFragment) {
                doRetrieveModel().setHotelNonBintangFragment((HotelNonBintangFragment) fragment);
            }
            if (fragment instanceof PondokFragment) {
                doRetrieveModel().setPondokFragment((PondokFragment) fragment);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
            case SHOW_HOTEL_BINTANG:
                showHotelBintangItems();
                break;
            case SHOW_HOTEL_NONBINTANG:
                showHotelNonBintangItems();
                break;
            case SHOW_PONDOK:
                showPondokItems();
                break;
            case SHOW_ITEMS:
                showItems();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showItems() {
        switch (this.viewPager.getCurrentItem()) {
            case 1:
                mPresenter.presentState(ViewState.LOAD_HOTEL_BINTANG);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_HOTEL_NONBINTANG);
                break;
            case 3:
                mPresenter.presentState(ViewState.LOAD_PONDOK);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_HOTEL_BINTANG);
                break;
        }
    }

    private void showPondokItems() {
        doRetrieveModel().getListAkomodasiModelPondok().clear();
        for (AkomodasiModel item : doRetrieveModel().getListAkomodasiModel()) {
            if (item.getJenis().equals(com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel.TAG_PONDOK)) {
                doRetrieveModel().getListAkomodasiModelPondok().add(item);
            }
        }
        doRetrieveModel().getPondokFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showHotelNonBintangItems() {
        doRetrieveModel().getListAkomodasiModelNonBintang().clear();
        for (AkomodasiModel item : doRetrieveModel().getListAkomodasiModel()) {
            if (item.getJenis().equals(com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel.TAG_HOTEL_NON_BINTANG)) {
                doRetrieveModel().getListAkomodasiModelNonBintang().add(item);
            }
        }
        doRetrieveModel().getHotelNonBintangFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showHotelBintangItems() {
        doRetrieveModel().getListAkomodasiModelBintang().clear();
        for (AkomodasiModel item : doRetrieveModel().getListAkomodasiModel()) {
            if (item.getJenis().equals(com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel.TAG_HOTEL_BINTANG)) {
                doRetrieveModel().getListAkomodasiModelBintang().add(item);
            }
        }
        doRetrieveModel().getHotelBintangFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = AkomodasiActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(AkomodasiActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
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
