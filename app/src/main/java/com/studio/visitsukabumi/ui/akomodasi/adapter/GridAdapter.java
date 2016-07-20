package com.studio.visitsukabumi.ui.akomodasi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<AkomodasiModel.ItemModel> mListItem;

    // Constructor
    public GridAdapter(Context c, List<AkomodasiModel.ItemModel> itemlist) {
        this.mContext = c;
        this.mListItem = itemlist;
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View row = inflater.inflate(R.layout.row_grid_menu, parent, false);
        ViewHolder holder = new ViewHolder(row);

        holder.textView.setText(mListItem.get(position).getNama());
        Picasso.with(mContext)
                .load(mListItem.get(position).getImageURL())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(holder.imageView);
        holder.ratingBar.setRating(mListItem.get(position).getRating());

        return row;
    }

    static class ViewHolder {
        @Bind(R.id.textview_row_grid_menu_name)
        TextView textView;
        @Bind(R.id.imageview_row_grid_menu)
        ImageView imageView;
        @Bind(R.id.ratingbar_row_grid_menu)
        RatingBar ratingBar;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
