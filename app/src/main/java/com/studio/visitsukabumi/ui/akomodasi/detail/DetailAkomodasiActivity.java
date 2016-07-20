package com.studio.visitsukabumi.ui.akomodasi.detail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailAkomodasiActivity extends AppCompatActivity {
    // views
    @Bind(R.id.toolbar_detail_akomodasi)
    Toolbar toolbar;
    @Bind(R.id.imageview_detail_akomodasi)
    ImageView imageView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akomodasi);

        init();
     }

    private void init() {
        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(DetailAkomodasiActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailAkomodasiActivity.this, toolbar);

        Picasso.with(DetailAkomodasiActivity.this)
                .load("https://pix6.agoda.net/hotelimages/569/569229/569229_15102712260037199188.jpg?s=312x235")
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
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
        actionBar.setTitle("Detail Akomodasi");
    }
}
