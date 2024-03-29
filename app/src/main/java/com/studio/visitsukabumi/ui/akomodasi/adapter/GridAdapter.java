package com.studio.visitsukabumi.ui.akomodasi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context mContext;
    private List<AkomodasiModel> mListItem;

    // Constructor
    public GridAdapter(Context c, List<AkomodasiModel> itemlist) {
        this.mContext = c;
        this.mListItem = itemlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mListItem.get(position).getNama());
        Picasso.with(mContext)
                .load(mListItem.get(position).getFotoUrl())
                .placeholder(R.drawable.ic_akomodasi)
                .error(R.drawable.ic_akomodasi)
                .into(holder.imageView);
        holder.ratingBar.setRating(Float.parseFloat(mListItem.get(position).getBintang()));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_row_card_item)
        TextView textView;
        @Bind(R.id.imageview_row_card_item)
        ImageView imageView;
        @Bind(R.id.ratingbar_row_card_item)
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
