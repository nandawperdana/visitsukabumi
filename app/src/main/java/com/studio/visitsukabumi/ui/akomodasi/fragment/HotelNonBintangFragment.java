package com.studio.visitsukabumi.ui.akomodasi.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.adapter.GridAdapter;
import com.studio.visitsukabumi.ui.akomodasi.detail.DetailAkomodasiActivity;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelNonBintangFragment extends Fragment {
    // views
    @Bind(R.id.gridview_menu_grid)
    GridView gridView;
    @Bind(R.id.textview_menu_grid)
    TextView textView;
    ProgressDialog progressDialog;

    // vars
    List<AkomodasiModel.ItemModel> mListItem;

    public HotelNonBintangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_grid, container, false);

        init(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AkomodasiModel.ItemModel item = mListItem.get(position);
                openDetails(item);
            }
        });
    }

    private void init(View view) {
        setDummyItem();
        initLayout(view);
    }

    private void initLayout(View view) {
        ButterKnife.bind(this, view);

        this.progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        gridView.setAdapter(new GridAdapter(getActivity(), mListItem));
    }

    private void setDummyItem() {
        this.mListItem = new ArrayList<>();

        mListItem.add(new AkomodasiModel.ItemModel("Nama 1", 2.3F, "asdf"));
        mListItem.add(new AkomodasiModel.ItemModel("Nama 2", 3.3F, "asdf"));
        mListItem.add(new AkomodasiModel.ItemModel("Nama 3", 4.3F, "asdf"));
        mListItem.add(new AkomodasiModel.ItemModel("Nama 4", 1.3F, "asdf"));
    }

    public void showItems() {
        if (mListItem.isEmpty())
            showScreenState(AkomodasiPresenter.AkomodasiView.ScreenState.SCREEN_BLANK);
        else
            showScreenState(AkomodasiPresenter.AkomodasiView.ScreenState.SCREEN_NOT_BLANK);
    }

    private void openDetails(AkomodasiModel.ItemModel item) {
        Intent intent = new Intent(getActivity(), DetailAkomodasiActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    private void showScreenState(AkomodasiPresenter.AkomodasiView.ScreenState screenState) {
        switch (screenState) {
            case SCREEN_BLANK:
                textView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                break;
            case SCREEN_NOT_BLANK:
                textView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                break;
        }
    }

}
