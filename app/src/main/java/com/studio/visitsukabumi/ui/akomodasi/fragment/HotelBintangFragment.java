package com.studio.visitsukabumi.ui.akomodasi.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.adapter.GridAdapter;
import com.studio.visitsukabumi.ui.akomodasi.detail.DetailAkomodasiActivity;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelBintangFragment extends Fragment {
    // views
    @Bind(R.id.recyclerview_menu_grid)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    @Bind(R.id.textview_menu_grid)
    TextView textView;
    ProgressDialog progressDialog;

    // vars
    List<AkomodasiModel.ItemModel> mListItem;

    public HotelBintangFragment() {
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AkomodasiModel.ItemModel item = mListItem.get(position);
                openDetails(item);
            }
        }));
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AkomodasiModel.ItemModel item = mListItem.get(position);
//                openDetails(item);
//            }
//        });
    }

    private void init(View view) {
        setDummyItem();
        initLayout(view);
    }

    private void initLayout(View view) {
        ButterKnife.bind(this, view);

        this.progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(new GridAdapter(getActivity(), mListItem));
//        gridView.setAdapter(new GridAdapter(getActivity(), mListItem));
    }

    private void setDummyItem() {
        this.mListItem = new ArrayList<>();

        mListItem.add(new AkomodasiModel.ItemModel("Inna Samudera", 4.5F, "https://pix6.agoda.net/hotelimages/569/569229/569229_15102712260037199188.jpg?s=312x235"));
        mListItem.add(new AkomodasiModel.ItemModel("Resort Pangrango", 3.5F, "https://pix6.agoda.net/hotelimages/267/267626/267626_15061714400029774906.jpg?s=312x235"));
        mListItem.add(new AkomodasiModel.ItemModel("Sulabintana Resort", 4.0F, "http://images2.travbuddy.com/hotel_21365777.jpg"));
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
//                gridView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            case SCREEN_NOT_BLANK:
                textView.setVisibility(View.GONE);
//                gridView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

}
