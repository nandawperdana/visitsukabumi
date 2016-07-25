package com.studio.visitsukabumi.ui.objek_wisata;

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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.ObjekWisataPresenter;
import com.studio.visitsukabumi.ui.objek_wisata.adapter.ViewPagerAdapter;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.GunungFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.LautPantaiFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.RimbaFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.SungaiFragment;
import com.studio.visitsukabumi.ui.objek_wisata.mvp.ObjekWisataModel;
import com.studio.visitsukabumi.ui.objek_wisata.mvp.ObjekWisataPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ObjekWisataActivity extends AppCompatActivity implements ObjekWisataPresenter.ObjekWisataView {

    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_akomodasi)
    Toolbar toolbar;
    @Bind(R.id.tablayout_akomodasi)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_akomodasi)
    ViewPager viewPager;

    // vars
    ObjekWisataModel mModel;
    ObjekWisataPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akomodasi);

        init();

        mPresenter.presentState(ViewState.LOAD_OBJEK_WISATA);

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
                        mPresenter.presentState(ViewState.LOAD_LAUTPANTAI);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_RIMBA);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_GUNUNG);
                        break;
                    case 3:
                        mPresenter.presentState(ViewState.LOAD_SUNGAI);
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
        this.mProgressDialog = new ProgressDialog(ObjekWisataActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new ObjekWisataModel();
        this.mPresenter = new ObjekWisataPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(ObjekWisataActivity.this, toolbar);
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
        actionBar.setTitle("Objek Wisata");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LautPantaiFragment(), "Laut dan Pantai");
        viewPagerAdapter.addFragment(new RimbaFragment(), "Rimba");
        viewPagerAdapter.addFragment(new GunungFragment(), "Gunung");
        viewPagerAdapter.addFragment(new SungaiFragment(), "Sungai");

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
            if (fragment instanceof RimbaFragment) {
                doRetrieveModel().setRimbaFragment((RimbaFragment) fragment);
            }
            if (fragment instanceof LautPantaiFragment) {
                doRetrieveModel().setLautPantaiFragment((LautPantaiFragment) fragment);
            }
            if (fragment instanceof GunungFragment) {
                doRetrieveModel().setGunungFragment((GunungFragment) fragment);
            }
            if (fragment instanceof SungaiFragment) {
                doRetrieveModel().setSungaiFragment((SungaiFragment) fragment);
            }
            Log.i("AttachFragment", "fragment " + fragment.toString());
        } catch (Exception e) {
            Log.i("AttachFragment", "fragment " + fragment.toString());
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
            case SHOW_RIMBA:
                showRimbaItems();
                break;
            case SHOW_GUNUNG:
                showGunungItems();
                break;
            case SHOW_LAUTPANTAI:
                showLautPantaiItems();
                break;
            case SHOW_SUNGAI:
                showSungaiItems();
                break;
            case SHOW_ITEMS:
                showItems();
                break;
            case ERROR:
                break;
        }
    }

    private void showSungaiItems() {
        doRetrieveModel().getListObjekSungai().clear();
        for (com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel item : doRetrieveModel().getListObjekWisata()) {
            if (item.getJenis().equals(ObjekWisataModel.TAG_SUNGAI)) {
                doRetrieveModel().getListObjekSungai().add(item);
            }
        }
        doRetrieveModel().getSungaiFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showLautPantaiItems() {
        doRetrieveModel().getListObjekLautPantai().clear();
        for (com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel item : doRetrieveModel().getListObjekWisata()) {
            if (item.getJenis().equals(ObjekWisataModel.TAG_LAUT_DAN_PANTAI)) {
                doRetrieveModel().getListObjekLautPantai().add(item);
            }
        }
        doRetrieveModel().getLautPantaiFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showGunungItems() {
        doRetrieveModel().getListObjekGunung().clear();
        for (com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel item : doRetrieveModel().getListObjekWisata()) {
            if (item.getJenis().equals(ObjekWisataModel.TAG_GUNUNG)) {
                doRetrieveModel().getListObjekGunung().add(item);
            }
        }
        doRetrieveModel().getGunungFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);

    }

    private void showRimbaItems() {
        doRetrieveModel().getListObjekRimba().clear();
        for (com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel item : doRetrieveModel().getListObjekWisata()) {
            if (item.getJenis().equals(ObjekWisataModel.TAG_RIMBA)) {
                doRetrieveModel().getListObjekRimba().add(item);
            }
        }
        doRetrieveModel().getRimbaFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);

    }

    private void showItems() {
        switch (this.viewPager.getCurrentItem()) {
            case 1:
                mPresenter.presentState(ViewState.LOAD_LAUTPANTAI);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_RIMBA);
                break;
            case 3:
                mPresenter.presentState(ViewState.LOAD_GUNUNG);
                break;
            case 4:
                mPresenter.presentState(ViewState.LOAD_SUNGAI);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_LAUTPANTAI);
                break;
        }
    }

    @Override
    public ObjekWisataModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = ObjekWisataActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(ObjekWisataActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    ObjekWisataActivity.this.finish();
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

    }
}
