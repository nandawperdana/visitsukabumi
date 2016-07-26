package com.studio.visitsukabumi.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NwP.
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private List<RowListItem> listItems;
    private Context mContext;

    public ListItemAdapter(Context mContext, List<RowListItem> listAuthor) {
        this.listItems = listAuthor;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewTitle.setText(listItems.get(position).getTitle());
        holder.textViewSubTitle.setText(listItems.get(position).getSubTitle());
        Picasso.with(mContext)
                .load(listItems.get(position).getImage())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_row_list_item)
        TextView textViewTitle;
        @Bind(R.id.textview_row_list_item_subtitle)
        TextView textViewSubTitle;
        @Bind(R.id.imageview_row_list_item)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
