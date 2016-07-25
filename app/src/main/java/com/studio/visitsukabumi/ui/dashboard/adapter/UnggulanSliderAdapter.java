package com.studio.visitsukabumi.ui.dashboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.visitsukabumi.R;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NwP.
 */
public class UnggulanSliderAdapter extends PagerAdapter {
    private List<UnggulanModel> listUnggulanModel;
    private LayoutInflater inflater;
    private Context context;

    public UnggulanSliderAdapter(Context context, List<UnggulanModel> data) {
        this.context = context;
        this.listUnggulanModel = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listUnggulanModel.size();
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_dashboard_slider, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        Picasso.with(context)
                .load(listUnggulanModel.get(position).getFotoUrl())
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .into(holder.image);
        holder.textViewTitle.setText(listUnggulanModel.get(position).getNama());
        holder.textViewSubTitle.setText(listUnggulanModel.get(position).getDeskripsi());

        viewGroup.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    static class ViewHolder {
        @Bind(R.id.imageview_dashboard)
        ImageView image;
        @Bind(R.id.textview_dashboard_title)
        TextView textViewTitle;
        @Bind(R.id.textview_dashboard_subtitle)
        TextView textViewSubTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
