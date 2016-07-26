package com.studio.visitsukabumi.ui.unggulan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.UnggulanPresenter;
import com.studio.visitsukabumi.ui.unggulan.fragment.AccessibilityFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.ActivitiesFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AmenitiesFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AncilliaryFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AttractionFragment;
import com.studio.visitsukabumi.ui.unggulan.mvp.UnggulanModel;
import com.studio.visitsukabumi.ui.unggulan.mvp.UnggulanPresenterImpl;
import com.studio.visitsukabumi.utils.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UnggulanActivity extends AppCompatActivity implements UnggulanPresenter.UnggulanView {
    // views
    ProgressDialog mProgressDialog;
    @Bind(R.id.toolbar_unggulan)
    Toolbar toolbar;
    @Bind(R.id.imageview_unggulan_tab)
    ImageView imageView;
    @Bind(R.id.tablayout_unggulan_tab)
    TabLayout tabLayout;
    @Bind(R.id.textview_unggulan_nama)
    TextView textViewNama;
    @Bind(R.id.viewpager_unggulan_tab)
    ViewPager viewPager;

    // vars
    UnggulanPresenter mPresenter;
    UnggulanModel mModel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mModel = new UnggulanModel();
        this.mPresenter = new UnggulanPresenterImpl(this);

        intent = UnggulanActivity.this.getIntent();
        parseBundleExtra(intent);

        setContentView(R.layout.activity_unggulan);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void parseBundleExtra(Intent intent) {
        doRetrieveModel().setUnggulanModel((com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel) intent.getSerializableExtra("unggulan"));
    }

    private void init() {
        ButterKnife.bind(this);
        this.mProgressDialog = new ProgressDialog(UnggulanActivity.this);
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(UnggulanActivity.this, toolbar);
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
        actionBar.setTitle("Unggulan Detail");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AmenitiesFragment(), "Amenities");
        viewPagerAdapter.addFragment(new AttractionFragment(), "Attraction");
        viewPagerAdapter.addFragment(new AncilliaryFragment(), "Ancilliary");
        viewPagerAdapter.addFragment(new AccessibilityFragment(), "Accessibility");
        viewPagerAdapter.addFragment(new ActivitiesFragment(), "Activities");

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
            if (fragment instanceof AmenitiesFragment) {
                doRetrieveModel().setAmenitiesFragment((AmenitiesFragment) fragment);
            }
            if (fragment instanceof AncilliaryFragment) {
                doRetrieveModel().setAncilliaryFragment((AncilliaryFragment) fragment);
            }
            if (fragment instanceof AttractionFragment) {
                doRetrieveModel().setAttractionFragment((AttractionFragment) fragment);
            }
            if (fragment instanceof AccessibilityFragment) {
                doRetrieveModel().setAccessibilityFragment((AccessibilityFragment) fragment);
            }
            if (fragment instanceof ActivitiesFragment) {
                doRetrieveModel().setActivitiesFragment((ActivitiesFragment) fragment);
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
                mPresenter.presentState(ViewState.IDLE);
                break;
            case ERROR:
                showError();
                break;
        }
    }

    private void showItem() {
        textViewNama.setText(doRetrieveModel().getUnggulanModel().getNama());
        Picasso.with(UnggulanActivity.this)
                .load(doRetrieveModel().getUnggulanModel().getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

    @Override
    public UnggulanModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = UnggulanActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(UnggulanActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    UnggulanActivity.this.finish();
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
        Toast.makeText(UnggulanActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }
}
