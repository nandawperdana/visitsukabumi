package com.studio.visitsukabumi.ui.event;

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
import com.studio.visitsukabumi.api.v1.event.EventModel;
import com.studio.visitsukabumi.utils.commons.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailEventActivity extends AppCompatActivity {
    // views
    @Bind(R.id.textview_detail_event_tanggal)
    TextView textViewTanggal;
    @Bind(R.id.textview_detail_event_deskripsi)
    TextView textViewDeskripsi;
    @Bind(R.id.textview_detail_event_nama)
    TextView textViewNama;
    @Bind(R.id.imageview_detail_event)
    ImageView imageView;
    @Bind(R.id.toolbar_detail_event)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    //vars
    Intent intent;
    EventModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        intent = getIntent();

        init();

        parseBundleExtra(intent);
    }

    private void parseBundleExtra(Intent intent) {
        model = ((EventModel) intent.getSerializableExtra(Constants.Code.TAG_EVENT));

        // show
        showDetails();
    }

    private void init() {
        ButterKnife.bind(this);
        this.model = new EventModel();
        this.progressDialog = new ProgressDialog(DetailEventActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailEventActivity.this, toolbar);
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
        actionBar.setTitle("Detail Event");
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
        textViewTanggal.setText(model.getTanggal());
        Picasso.with(DetailEventActivity.this)
                .load(model.getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

}
