package com.studio.visitsukabumi.ui.kontak;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KontakActivity extends AppCompatActivity {
    //views
    @Bind(R.id.toolbar_kontak)
    Toolbar toolbar;
    @Bind(R.id.imageview_kontak)
    ImageView imageView;
    @Bind(R.id.textview_kontak_nama)
    TextView textViewNama;
    @Bind(R.id.textview_kontak_alamat)
    TextView textViewAlamat;
    @Bind(R.id.textview_kontak_email)
    TextView textViewEmail;
    @Bind(R.id.textview_kontak_telp)
    TextView textViewTelp;
    @Bind(R.id.textview_kontak_website)
    TextView textViewWebsite;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        init();

        showKontak();
    }

    private void init() {
        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(KontakActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        initLayout();
    }

    private void initLayout() {
        initToolbar(KontakActivity.this, toolbar);
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
        actionBar.setTitle("Detail Kontak");
    }

    private void showKontak() {
        textViewNama.setText("Disparbudpora Kabupaten Sukabumi");
        textViewAlamat.setText("Jl. Jend. Sudirman, Komplek Perkantoran Pemda Jajaway Palabuhanratu 43364, Jawa Barat");
        textViewEmail.setText("kontak@disparbudporakabsukabumi.co.id");
        textViewTelp.setText("(0266) 7070337");
        textViewWebsite.setText("http://disparbudporakabsukabumi.co.id/");
        Picasso.with(KontakActivity.this)
                .load("http://2.bp.blogspot.com/-kTBV4DtzRYA/VZ8Z6QySC-I/AAAAAAAAAZ0/yE7k9PdUHBk/s320/disparbudpora%2Bsmi.png")
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(imageView);
    }

    @OnClick(R.id.textview_kontak_telp)
    public void onClickTelp() {
        String phone = textViewTelp.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @OnClick(R.id.textview_kontak_website)
    public void onClickWeb() {
        String webUrl = textViewWebsite.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
        startActivity(browserIntent);
    }
}
