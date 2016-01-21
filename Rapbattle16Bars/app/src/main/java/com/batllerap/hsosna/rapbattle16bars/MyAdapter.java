package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Albert on 02.12.2015.
 */
public class MyAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;



    private Context context;
    private List<BattleOverview> mDataset = new ArrayList<>();

    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private static ClickListener cListener;

    public MyAdapter(List<BattleOverview> myDataSet, RecyclerView recyclerView, Context context) {
        this.context = context;
        this.mDataset = myDataSet;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }
    public MyAdapter(List<BattleOverview> myDataset) {
        myDataset = myDataset;
    }


    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customelement, parent, false);

            vh = new TextViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TextViewHolder) {
            BattleOverview current = mDataset.get(position);

            ((TextViewHolder) holder).rapper1.setText(current.getRapper1().getUsername());
            ((TextViewHolder) holder).rapper2.setText(current.getRapper2().getUsername());

            if (current.getRapper1().getProfile_picture() != null){
                Picasso.with(context).load(current.getRapper1().getProfile_picture()).fit().networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE).into(((TextViewHolder) holder).imgRapper1);

            }
            if (current.getRapper2().getProfile_picture() != null){
                Picasso.with(context).load(current.getRapper2().getProfile_picture()).fit().networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE).into(((TextViewHolder) holder).imgRapper2);

            }

;
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView rapper1;
        TextView rapper2;
        TextView vs;
        ImageView imgRapper1;
        ImageView imgRapper2;


        public TextViewHolder(View v) {
            super(v);
            itemView.setOnClickListener(this);
            rapper1= (TextView) itemView.findViewById(R.id.ListText);
            rapper2= (TextView) itemView.findViewById(R.id.ListText2);
            vs = (TextView) itemView.findViewById(R.id.VS);

            imgRapper1= (ImageView) itemView.findViewById(R.id.ListImage);
            imgRapper2= (ImageView) itemView.findViewById(R.id.ListImage2);
        }

        @Override
        public void onClick(View v) {

            if (cListener != null){
                cListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener){
        this.cListener = clickListener;
    }

}