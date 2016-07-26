package com.studio.visitsukabumi.ui.akomodasi.detail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.presentation.presenters.DetailAkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.detail.mvp.DetailAkomodasiPresenterImpl;
import com.studio.visitsukabumi.ui.akomodasi.detail.mvp.DetailModel;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailAkomodasiActivity extends AppCompatActivity implements DetailAkomodasiPresenter.DetailAkomodasiView {
    // views
    @Bind(R.id.toolbar_detail_akomodasi)
    Toolbar toolbar;
    @Bind(R.id.imageview_detail_akomodasi)
    ImageView imageView;
    ProgressDialog progressDialog;
    @Bind(R.id.textview_detail_akomodasi_nama)
    TextView textViewNama;
    @Bind(R.id.textview_detail_akomodasi_alamat)
    TextView textViewAlamat;
    @Bind(R.id.textview_detail_akomodasi_telp)
    TextView textViewTelp;
    @Bind(R.id.textview_detail_akomodasi_email)
    TextView textViewEmail;
    @Bind(R.id.textview_detail_akomodasi_fasilitas)
    TextView textViewFasilitas;
    @Bind(R.id.textview_detail_akomodasi_kamar)
    TextView textViewKamar;
    @Bind(R.id.textview_detail_akomodasi_pemilik)
    TextView textViewPemilik;
    @Bind(R.id.textview_detail_akomodasi_tempat_tidur)
    TextView textViewTempTidur;
    @Bind(R.id.textview_detail_akomodasi_tarif)
    TextView textViewTempTarif;
    @Bind(R.id.button_detail_akomodasi)
    Button button;

    // vars
    DetailModel mModel;
    DetailAkomodasiPresenter mPresenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akomodasi);
        intent = DetailAkomodasiActivity.this.getIntent();

        init();

        parseBundleExtra(intent);

        mPresenter.presentState(ViewState.SHOW_DETAILS);
    }

    private void parseBundleExtra(Intent intent) {
        doRetrieveModel().setAkomodasiModel((AkomodasiModel) intent.getSerializableExtra("item"));
    }

    private void init() {
        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(DetailAkomodasiActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        this.mModel = new DetailModel();
        this.mPresenter = new DetailAkomodasiPresenterImpl(this);

        initLayout();
    }

    private void initLayout() {
        initToolbar(DetailAkomodasiActivity.this, toolbar);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDetails() {
        textViewNama.setText(doRetrieveModel().getAkomodasiModel().getNama());
        textViewAlamat.setText(doRetrieveModel().getAkomodasiModel().getAlamat());
        textViewEmail.setText(doRetrieveModel().getAkomodasiModel().getEmail());
        textViewFasilitas.setText(doRetrieveModel().getAkomodasiModel().getFasilitas());
        textViewPemilik.setText(doRetrieveModel().getAkomodasiModel().getNamaPemilik());
        textViewKamar.setText(doRetrieveModel().getAkomodasiModel().getJmlKamar());
        textViewTempTidur.setText(doRetrieveModel().getAkomodasiModel().getJmlTempatTidur());
        textViewTempTarif.setText(doRetrieveModel().getAkomodasiModel().getTarif());
        Picasso.with(DetailAkomodasiActivity.this)
                .load(doRetrieveModel().getAkomodasiModel().getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);

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
            case SHOW_DETAILS:
                showDetails();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    @Override
    public DetailModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showError() {
        Context con = DetailAkomodasiActivity.this;
        if (con != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    new MaterialDialog.Builder(DetailAkomodasiActivity.this)
                            .content(R.string.error_general_title)
                            .positiveText(R.string.error_general_content)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    DetailAkomodasiActivity.this.finish();
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
            this.progressDialog.show();
        else this.progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {

    }

    @OnClick(R.id.button_detail_akomodasi)
    public void onClickMaps() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(doRetrieveModel().getAkomodasiModel().getLatitude()),
                Float.parseFloat(doRetrieveModel().getAkomodasiModel().getLongitude()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
