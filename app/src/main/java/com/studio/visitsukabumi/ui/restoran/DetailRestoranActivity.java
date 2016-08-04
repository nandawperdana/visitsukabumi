package com.studio.visitsukabumi.ui.restoran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.restoran.RestoranModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailRestoranActivity extends AppCompatActivity {
    // views
    @Bind(R.id.toolbar_detail_restoran)
    Toolbar toolbar;
    @Bind(R.id.imageview_detail_restoran)
    ImageView imageView;
    ProgressDialog progressDialog;
    @Bind(R.id.textview_detail_restoran_nama)
    TextView textViewNama;
    @Bind(R.id.textview_detail_restoran_alamat)
    TextView textViewAlamat;
    @Bind(R.id.textview_detail_restoran_telp)
    TextView textViewTelp;
    @Bind(R.id.textview_detail_restoran_jenis)
    TextView textViewJenis;
    @Bind(R.id.textview_detail_restoran_jml_kursi)
    TextView textViewJmlKursi;
    @Bind(R.id.textview_detail_restoran_jml_meja)
    TextView textViewJmlMeja;
    @Bind(R.id.textview_detail_restoran_hidangan)
    TextView textViewHidangan;
    @Bind(R.id.textview_detail_restoran_pemilik)
    TextView textViewPemilik;
    @Bind(R.id.button_detail_restoran)
    Button button;

    // vars
    RestoranModel model;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restoran);
        intent = getIntent();
        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((RestoranModel) intent.getSerializableExtra(Constants.Code.TAG_RESTORAN));

        // show
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new RestoranModel();
        this.progressDialog = new ProgressDialog(DetailRestoranActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailRestoranActivity.this, toolbar);
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
        actionBar.setTitle("Detail Tempat Makan");
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
        textViewPemilik.setText(model.getNamaPemilik());
        textViewJmlKursi.setText(model.getJmlKursi());
        textViewJmlMeja.setText(model.getJmlMeja());
        textViewHidangan.setText(model.getHidangan());
        textViewTelp.setText(model.getTelepon());

        Picasso.with(DetailRestoranActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

    @OnClick(R.id.button_detail_restoran)
    public void onClick() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(model.getLatitude()),
                Float.parseFloat(model.getLongitude()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
