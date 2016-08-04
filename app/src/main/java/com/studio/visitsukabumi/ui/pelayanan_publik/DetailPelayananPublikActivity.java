package com.studio.visitsukabumi.ui.pelayanan_publik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPelayananPublikActivity extends AppCompatActivity {
    // views
    @Bind(R.id.textview_detail_pelayanan_publik_alamat)
    TextView textViewAlamat;
    @Bind(R.id.textview_detail_pelayanan_publik_jenis)
    TextView textViewJenis;
    @Bind(R.id.textview_detail_pelayanan_publik_keterangan)
    TextView textViewKeterangan;
    @Bind(R.id.textview_detail_pelayanan_publik_nama)
    TextView textViewNama;
    @Bind(R.id.imageview_detail_pelayanan_publik)
    ImageView imageView;
    @Bind(R.id.toolbar_detail_pelayanan_publik)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    //vars
    Intent intent;
    PelayananPublikModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelayanan_publik);
        intent = getIntent();

        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((PelayananPublikModel) intent.getSerializableExtra(Constants.Code.TAG_PELAYANAN_PUBLIK));

        // show
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new PelayananPublikModel();
        this.progressDialog = new ProgressDialog(DetailPelayananPublikActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailPelayananPublikActivity.this, toolbar);
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
        actionBar.setTitle("Detail Pelayanan Publik");
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
        textViewAlamat.setText(model.getAlamat());
        textViewJenis.setText(model.getJenis());
        textViewKeterangan.setText(model.getKeterangan());
        Picasso.with(DetailPelayananPublikActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

    @OnClick(R.id.button_detail_pelayanan_publik)
    public void onClick() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(model.getLatitude()),
                Float.parseFloat(model.getLongitude()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
