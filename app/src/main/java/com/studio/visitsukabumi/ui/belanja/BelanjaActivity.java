package com.studio.visitsukabumi.ui.belanja;

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
import com.studio.visitsukabumi.presentation.presenters.BelanjaPresenter;
import com.studio.visitsukabumi.ui.belanja.fragment.MallFragment;
import com.studio.visitsukabumi.ui.belanja.fragment.OlehFragment;
import com.studio.visitsukabumi.ui.belanja.fragment.PasarFragment;
import com.studio.visitsukabumi.ui.belanja.mvp.BelanjaModel;
import com.studio.visitsukabumi.ui.belanja.mvp.BelanjaPresenterImpl;
import com.studio.visitsukabumi.ui.transportasi.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BelanjaActivity extends AppCompatActivity implements BelanjaPresenter.BelanjaView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_belanja)
    Toolbar toolbar;
    @Bind(R.id.tablayout_belanja)
    TabLayout tabLayout;
    @Bind(R.id.viewpager_belanja)
    ViewPager viewPager;

    // vars
    BelanjaPresenter mPresenter;
    BelanjaModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja);
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
                        mPresenter.presentState(ViewState.LOAD_PASAR);
                        break;
                    case 1:
                        mPresenter.presentState(ViewState.LOAD_OLEHOLEH);
                        break;
                    case 2:
                        mPresenter.presentState(ViewState.LOAD_MALL);
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
        this.mProgressDialog = new ProgressDialog(BelanjaActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        this.mModel = new BelanjaModel();
        this.mPresenter = new BelanjaPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(BelanjaActivity.this, toolbar);
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
        actionBar.setTitle("Belanja");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PasarFragment(), "Pasar Tradisional");
        viewPagerAdapter.addFragment(new OlehFragment(), "Oleh-oleh");
        viewPagerAdapter.addFragment(new MallFragment(), "Mall");

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
            if (fragment instanceof PasarFragment) {
                doRetrieveModel().setPasarFragment((PasarFragment) fragment);
            }
            if (fragment instanceof OlehFragment) {
                doRetrieveModel().setOlehFragment((OlehFragment) fragment);
            }
            if (fragment instanceof MallFragment) {
                doRetrieveModel().setMallFragment((MallFragment) fragment);
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
            case SHOW_PASAR:
                showPasar();
                break;
            case SHOW_OLEHOLEH:
                showOlehOleh();
                break;
            case SHOW_MALL:
                showMall();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showItem() {
        switch (this.viewPager.getCurrentItem()) {
            case 0:
                mPresenter.presentState(ViewState.LOAD_PASAR);
                break;
            case 1:
                mPresenter.presentState(ViewState.LOAD_OLEHOLEH);
                break;
            case 2:
                mPresenter.presentState(ViewState.LOAD_MALL);
                break;
            default:
                mPresenter.presentState(ViewState.LOAD_PASAR);
                break;
        }
    }

    private void showPasar() {
        doRetrieveModel().getListBelanjaPasar().clear();
        for (com.studio.visitsukabumi.api.v1.belanja.BelanjaModel item : doRetrieveModel().getListBelanja()) {
            if (item.getJenis().equals(BelanjaModel.TAG_PASAR)) {
                doRetrieveModel().getListBelanjaPasar().add(item);
            }
        }
        doRetrieveModel().getPasarFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showOlehOleh() {
        doRetrieveModel().getListBelanjaOleh().clear();
        for (com.studio.visitsukabumi.api.v1.belanja.BelanjaModel item : doRetrieveModel().getListBelanja()) {
            if (item.getJenis().equals(BelanjaModel.TAG_OLEH)) {
                doRetrieveModel().getListBelanjaOleh().add(item);
            }
        }
        doRetrieveModel().getOlehFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    private void showMall() {
        doRetrieveModel().getListBelanjaMall().clear();
        for (com.studio.visitsukabumi.api.v1.belanja.BelanjaModel item : doRetrieveModel().getListBelanja()) {
            if (item.getJenis().equals(BelanjaModel.TAG_MAL)) {
                doRetrieveModel().getListBelanjaMall().add(item);
            }
        }
        doRetrieveModel().getMallFragment().showItems();
        mPresenter.presentState(ViewState.IDLE);
    }

    @Override
    public BelanjaModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = BelanjaActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(BelanjaActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    BelanjaActivity.this.finish();
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
        Toast.makeText(BelanjaActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
