package com.studio.visitsukabumi.ui.belanja.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.belanja.BelanjaModel;
import com.studio.visitsukabumi.presentation.presenters.TransportasiPresenter;
import com.studio.visitsukabumi.ui.belanja.BelanjaActivity;
import com.studio.visitsukabumi.ui.belanja.DetailBelanjaActivity;
import com.studio.visitsukabumi.utils.adapter.ListItemAdapter;
import com.studio.visitsukabumi.utils.adapter.RowListItem;
import com.studio.visitsukabumi.utils.commons.Constants;
import com.studio.visitsukabumi.utils.commons.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PasarFragment extends Fragment {
    // views
    @Bind(R.id.recyclerview_menu_list)
    RecyclerView recyclerView;
    @Bind(R.id.textview_menu_list)
    TextView textView;
    ProgressDialog progressDialog;

    List<RowListItem> listItemShow;
    List<BelanjaModel> listItems;

    public PasarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);

        init(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BelanjaModel item = listItems.get(position);
                openDetails(item);
            }
        }));
    }

    private void init(View view) {
        this.listItemShow = new ArrayList<>();
        this.listItems = ((BelanjaActivity) getActivity()).doRetrieveModel().getListBelanjaPasar();
        initLayout(view);
    }

    private void initLayout(View view) {
        ButterKnife.bind(this, view);

        this.progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ListItemAdapter(getActivity(), listItemShow));
    }

    public void showItems() {
        if (listItems.isEmpty())
            showScreenState(TransportasiPresenter.TransportasiView.ScreenState.SCREEN_BLANK);
        else {
            listItemShow.clear();
            for (BelanjaModel item : listItems) {
                listItemShow.add(new RowListItem(item.getNama(), item.getAlamat(), item.getFotoUrl()));
            }

            recyclerView.getAdapter().notifyDataSetChanged();
            showScreenState(TransportasiPresenter.TransportasiView.ScreenState.SCREEN_NOT_BLANK);
        }
    }

    private void openDetails(BelanjaModel item) {
        Intent intent = new Intent(getActivity(), DetailBelanjaActivity.class);
        intent.putExtra(Constants.Code.TAG_BELANJA, item);
        startActivity(intent);
    }

    private void showScreenState(TransportasiPresenter.TransportasiView.ScreenState screenState) {
        switch (screenState) {
            case SCREEN_BLANK:
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                break;
            case SCREEN_NOT_BLANK:
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

}
