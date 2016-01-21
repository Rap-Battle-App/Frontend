package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Albert on 01.12.2015.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<BattleOverview> data= new ArrayList<>();
    private Context context;
    private ClickListener cListener;

    public CustomAdapter(Context context, List<BattleOverview> data){

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data=data;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.customelement, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BattleOverview current = data.get(position);

        holder.rapper1.setText(current.getRapper1().getUsername());
        holder.rapper2.setText(current.getRapper2().getUsername());

        if (current.getRapper1().getProfile_picture() != null){
            Picasso.with(context).load(current.getRapper1().getProfile_picture()).resize(150,150).into(holder.imgRapper1);

        }
        if (current.getRapper2().getProfile_picture() != null){
            Picasso.with(context).load(current.getRapper2().getProfile_picture()).resize(150,150).into(holder.imgRapper2);

        }




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rapper1;
        TextView rapper2;
        TextView vs;
        ImageView imgRapper1;
        ImageView imgRapper2;

        public MyViewHolder(View itemView) {
            super(itemView);
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

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener){
        this.cListener = clickListener;
    }
}
