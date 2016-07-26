package com.studio.visitsukabumi.ui.unggulan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;
import com.studio.visitsukabumi.ui.unggulan.UnggulanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AncilliaryFragment extends Fragment {
    // views
    @Bind(R.id.textview_unggulan_detail)
    TextView textView;
    private UnggulanModel model;

    public AncilliaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_unggulan_detail, container, false);

        init(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (model != null)
            textView.setText(model.getAncilliary());
    }

    private void init(View view) {
        initLayout(view);
        model = ((UnggulanActivity) getActivity()).doRetrieveModel().getUnggulanModel();
    }

    private void initLayout(View view) {
        ButterKnife.bind(this, view);
    }

    public void showItems() {

    }

}
