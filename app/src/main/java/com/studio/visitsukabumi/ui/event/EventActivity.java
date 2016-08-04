package com.studio.visitsukabumi.ui.event;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.event.EventModel;
import com.studio.visitsukabumi.ui.belanja.DetailBelanjaActivity;
import com.studio.visitsukabumi.utils.adapter.ListItemAdapter;
import com.studio.visitsukabumi.utils.adapter.RowListItem;
import com.studio.visitsukabumi.utils.commons.Constants;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;

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
    List<EventModel> listBelanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        init();

        showItems();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(EventActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventModel item = listBelanja.get(position);
                openDetails(item);
            }
        }));
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

    private void init() {
        initLayout();
    }

    private void initLayout() {
        ButterKnife.bind(this);
        initToolbar(EventActivity.this, toolbar);

        this.listItemShow = new ArrayList<>();
        this.listBelanja = new ArrayList<>();

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
        EventModel model = new EventModel(1, "Hari Jadi Kabupaten Sukabumi", "2016-10-01", "", "http://sku-aspirasirakyat.com/wp-content/uploads/2014/10/Karnaval-hari-jadi-kab-sukabumi.jpg");
        listBelanja.add(model);
        listItemShow.add(new RowListItem(model.getNama(), model.getTanggal(), model.getFotoUrl()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void openDetails(EventModel item) {
        Intent intent = new Intent(EventActivity.this, DetailEventActivity.class);
        intent.putExtra(Constants.Code.TAG_EVENT, item);
        startActivity(intent);
    }
}
