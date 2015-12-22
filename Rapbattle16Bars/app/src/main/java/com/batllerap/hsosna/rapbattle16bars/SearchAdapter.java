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

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by markk on 21.12.2015.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ProfilePreview[] data;
    private ClickListener cListener;
    Bitmap  bmp = null;
    URL newurl = null;

    public SearchAdapter(Context context, ProfilePreview[] data) {

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
        final ProfilePreview current = data[position];
        holder.rname.setText(current.getUsername());
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    try {
                        newurl = new URL(current.getProfile_picture());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        bmp = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        holder.rProfilePic.setImageBitmap(bmp);
        holder.profileId = current.getUser_id();
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rname;
        public ImageView rProfilePic;
        public int profileId;

        public SearchViewHolder(View itemView) {
            super(itemView);

            rname = (TextView) itemView.findViewById(R.id.SearchName);
            rProfilePic = (ImageView) itemView.findViewById(R.id.SearchPicture);
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
