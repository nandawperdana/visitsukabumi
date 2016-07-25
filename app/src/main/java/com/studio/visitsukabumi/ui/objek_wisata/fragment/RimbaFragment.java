package com.studio.visitsukabumi.ui.objek_wisata.fragment;

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
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel;
import com.studio.visitsukabumi.presentation.presenters.ObjekWisataPresenter;
import com.studio.visitsukabumi.ui.akomodasi.detail.DetailAkomodasiActivity;
import com.studio.visitsukabumi.ui.objek_wisata.ObjekWisataActivity;
import com.studio.visitsukabumi.ui.objek_wisata.adapter.GridAdapter;
import com.studio.visitsukabumi.ui.objek_wisata.detail.DetailObjekWisataActivity;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RimbaFragment extends Fragment {
    // views
    @Bind(R.id.recyclerview_menu_grid)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    @Bind(R.id.textview_menu_grid)
    TextView textView;
    ProgressDialog progressDialog;

    List<ObjekWisataModel> listItems;

    public RimbaFragment() {
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
                ObjekWisataModel item = listItems.get(position);
                openDetails(item);
            }
        }));
    }

    private void init(View view) {
        this.listItems = ((ObjekWisataActivity) getActivity()).doRetrieveModel().getListObjekRimba();
        initLayout(view);
    }

    private void initLayout(View view) {
        ButterKnife.bind(this, view);

        this.progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(new GridAdapter(getActivity(), listItems));
    }

    public void showItems() {
        if (listItems.isEmpty())
            showScreenState(ObjekWisataPresenter.ObjekWisataView.ScreenState.SCREEN_BLANK);
        else {
            recyclerView.getAdapter().notifyDataSetChanged();
            showScreenState(ObjekWisataPresenter.ObjekWisataView.ScreenState.SCREEN_NOT_BLANK);
        }
    }

    private void openDetails(ObjekWisataModel item) {
        Intent intent = new Intent(getActivity(), DetailObjekWisataActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    private void showScreenState(ObjekWisataPresenter.ObjekWisataView.ScreenState screenState) {
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
