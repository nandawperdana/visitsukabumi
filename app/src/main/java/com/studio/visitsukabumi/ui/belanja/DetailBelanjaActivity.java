package com.studio.visitsukabumi.ui.belanja;

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
import com.studio.visitsukabumi.api.v1.belanja.BelanjaModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailBelanjaActivity extends AppCompatActivity {
    // views
    @Bind(R.id.textview_detail_belanja_alamat)
    TextView textViewAlamat;
    @Bind(R.id.textview_detail_belanja_jenis)
    TextView textViewJenis;
    @Bind(R.id.textview_detail_belanja_keterangan)
    TextView textViewKeterangan;
    @Bind(R.id.textview_detail_belanja_nama)
    TextView textViewNama;
    @Bind(R.id.imageview_detail_belanja)
    ImageView imageView;
    @Bind(R.id.toolbar_detail_belanja)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    //vars
    Intent intent;
    BelanjaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_belanja);
        intent = getIntent();

        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((BelanjaModel) intent.getSerializableExtra(Constants.Code.TAG_BELANJA));

        // show
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new BelanjaModel();
        this.progressDialog = new ProgressDialog(DetailBelanjaActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailBelanjaActivity.this, toolbar);
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
        actionBar.setTitle("Detail Belanja");
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
        Picasso.with(DetailBelanjaActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

    @OnClick(R.id.button_detail_belanja)
    public void onClick() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(model.getLatitude()),
                Float.parseFloat(model.getLongitude()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
