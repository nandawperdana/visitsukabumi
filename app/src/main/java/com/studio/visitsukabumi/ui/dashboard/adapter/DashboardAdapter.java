package com.studio.visitsukabumi.ui.dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private Context mContext;
    private List<DashboardModel.Menu> mListMenu;

    // Constructor
    public DashboardAdapter(Context c, List<DashboardModel.Menu> menuList) {
        this.mContext = c;
        this.mListMenu = menuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card_dashboard, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mListMenu.get(position).getName());
        holder.imageView.setImageResource(mListMenu.get(position).getImage());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mListMenu.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_row_dashboard)
        TextView textView;
        @Bind(R.id.imageview_row_dashboard)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
