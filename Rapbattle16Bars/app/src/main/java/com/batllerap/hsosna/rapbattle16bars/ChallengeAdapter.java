
package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Request;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Albert on 09.12.2015.
 */

public class ChallengeAdapter  extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>  {

    private LayoutInflater inflater;
    private Context context;
    private List<Request> data;
    private ClickListener cListener;

    public ChallengeAdapter(Context context, List<Request> data){

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data=data;

    }


    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.challengeelement, parent, false);

        ChallengeViewHolder holder = new ChallengeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {
        Request current = data.get(position);
        holder.rname.setText(current.getOpponent().getUsername());
        if (current.getOpponent().getProfile_picture() != null){
            Picasso.with(context).load(current.getOpponent().getProfile_picture()).resize(150,150).networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.rProfilePic);
        }else{
            holder.rProfilePic.setImageResource(R.drawable.default_profile_pic);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChallengeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView rname;
        public ImageView rProfilePic;
        public ImageView cAccepted;
        public ImageView cDeclined;

        public ChallengeViewHolder(View itemView) {
            super(itemView);

            rname = (TextView) itemView.findViewById(R.id.ChallengeRapper);
            rProfilePic = (ImageView) itemView.findViewById(R.id.ChallengeImage);
            cAccepted = (ImageView) itemView.findViewById(R.id.challenge_accepted);
            cDeclined = (ImageView) itemView.findViewById(R.id.challenge_declined);


            cAccepted.setOnClickListener(this);
            cDeclined.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            if (cListener != null){
                if (v.equals(cAccepted)){
                    cListener.itemAccepted(v, getAdapterPosition());
                    removeAt(getAdapterPosition());
                    return;

                }

                if (v.equals(cDeclined)){
                    cListener.itemDeclined(v, getAdapterPosition());
                    removeAt(getAdapterPosition());
                    return;

                }

                cListener.ChallengeClicked(v, getAdapterPosition());
            }

        }
    }
    public interface ClickListener{
        public void ChallengeClicked(View view, int position);
        public void itemAccepted(View view, int position);
        public void itemDeclined(View view, int position);
    }

    public void setClickListener(ClickListener clickListener){
        this.cListener = clickListener;
    }

    public void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
}

