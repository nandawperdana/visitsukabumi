package com.studio.visitsukabumi.ui.seni_budaya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailSeniBudayaActivity extends AppCompatActivity {
    // views
    @Bind(R.id.textview_detail_seni_budaya_deskripsi)
    TextView textViewDeskripsi;
    @Bind(R.id.textview_detail_seni_budaya_nama)
    TextView textViewNama;
    @Bind(R.id.imageview_detail_seni_budaya)
    ImageView imageView;
    @Bind(R.id.toolbar_detail_seni_budaya)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    //vars
    Intent intent;
    SeniBudayaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_seni_budaya);
        intent = getIntent();
        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((SeniBudayaModel) intent.getSerializableExtra(Constants.Code.TAG_SENI_BUDAYA));

        // show
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new SeniBudayaModel();
        this.progressDialog = new ProgressDialog(DetailSeniBudayaActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailSeniBudayaActivity.this, toolbar);
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
        actionBar.setTitle("Detail Seni Budaya");
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

    private void showDetails() {
        textViewNama.setText(model.getNama());
        textViewDeskripsi.setText(model.getDeskripsi());
        Picasso.with(DetailSeniBudayaActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }
}
