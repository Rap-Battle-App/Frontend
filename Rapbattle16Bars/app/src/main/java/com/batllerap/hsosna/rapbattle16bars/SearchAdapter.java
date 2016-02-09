package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by markk on 21.12.2015.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<ProfilePreview> data= new ArrayList<>();
    private ClickListener cListener;

    public SearchAdapter(Context context, List<ProfilePreview> data) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;

    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.searchelement, parent, false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        final ProfilePreview current = data.get(position);
        holder.rname.setText(current.getUsername());
        holder.profileId = current.getUser_id();
        if (current.getProfile_picture() != null){
            Picasso.with(context).load(current.getProfile_picture()).fit().networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.rProfilePic);
        }else {
            holder.rProfilePic.setImageResource(R.drawable.default_profile_pic);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rname;
        public ImageView rProfilePic;
        public int profileId;

        public SearchViewHolder(View itemView) {
            super(itemView);

            rname = (TextView) itemView.findViewById(R.id.searchName);
            rProfilePic = (ImageView) itemView.findViewById(R.id.searchPicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (cListener != null) {
                cListener.searchProfileClicked(v, getAdapterPosition());
            }

        }
    }

    public interface ClickListener {
        public void searchProfileClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.cListener = clickListener;
    }
}
