package com.studio.visitsukabumi.ui.dashboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class DashboardAdapter extends BaseAdapter {
    private Context mContext;
    private List<DashboardModel.Menu> mListMenu;

    // Constructor
    public DashboardAdapter(Context c, List<DashboardModel.Menu> menuList) {
        this.mContext = c;
        this.mListMenu = menuList;
    }

    @Override
    public int getCount() {
        return mListMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View row = inflater.inflate(R.layout.row_grid_dashboard, parent, false);
        ViewHolder holder = new ViewHolder(row);

        holder.textView.setText(mListMenu.get(position).getName());
        holder.imageView.setImageResource(mListMenu.get(position).getImage());

        return row;
    }

    static class ViewHolder {
        @Bind(R.id.textview_row_grid)
        TextView textView;
        @Bind(R.id.imageview_row_grid)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
