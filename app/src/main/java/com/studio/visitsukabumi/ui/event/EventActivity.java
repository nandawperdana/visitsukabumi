package com.studio.visitsukabumi.ui.event;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.utils.adapter.ListItemAdapter;
import com.studio.visitsukabumi.utils.adapter.RowListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {
    @Bind(R.id.recyclerview_event)
    RecyclerView recyclerView;
    @Bind(R.id.textview_event)
    TextView textView;
    @Bind(R.id.toolbar_event)
    Toolbar toolbar;
    ProgressDialog progressDialog;

    List<RowListItem> listItemShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        init();

        showItems();
    }

    private void init() {
        initLayout();
    }

    private void initLayout() {
        ButterKnife.bind(this);
        initToolbar(EventActivity.this, toolbar);

        this.listItemShow = new ArrayList<>();

        this.progressDialog = new ProgressDialog(EventActivity.this);
        progressDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));
        recyclerView.setAdapter(new ListItemAdapter(EventActivity.this, listItemShow));
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
        actionBar.setTitle("Event");
    }

    public void showItems() {
        listItemShow.add(new RowListItem("Hari Jadi Kabupaten Sukabumi", "2016-10-01", "http://sku-aspirasirakyat.com/wp-content/uploads/2014/10/Karnaval-hari-jadi-kab-sukabumi.jpg"));
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
