package com.studio.visitsukabumi.ui.akomodasi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.studio.visitsukabumi.R;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelBintangFragment extends Fragment {
    // views
    @Bind(R.id.gridview_akomodasi)
    GridView gridView;


    public HotelBintangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akomodasi, container, false);
    }

}
