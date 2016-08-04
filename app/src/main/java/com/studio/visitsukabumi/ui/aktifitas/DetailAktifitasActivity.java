package com.studio.visitsukabumi.ui.aktifitas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailAktifitasActivity extends AppCompatActivity {
    // views
    @Bind(R.id.textview_detail_aktifitas_nama)
    TextView textViewNama;
    @Bind(R.id.textview_detail_aktifitas_deskripsi)
    TextView textViewDeskripsi;
    @Bind(R.id.imageview_detail_aktifitas)
    ImageView imageView;
    @Bind(R.id.toolbar_detail_aktifitas)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    //vars
    Intent intent;
    AktifitasModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aktifitas);
        intent = getIntent();

        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((AktifitasModel) intent.getSerializableExtra(Constants.Code.TAG_AKTIFITAS));

        // hsow
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new AktifitasModel();
        this.progressDialog = new ProgressDialog(DetailAktifitasActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailAktifitasActivity.this, toolbar);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getColor(R.color.white));
        }
        actionBar.setTitle("Detail Aktifitas");
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
        Picasso.with(DetailAktifitasActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

}
