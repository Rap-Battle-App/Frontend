package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * Created by Albert on 20.01.2016.
 */
public class AsyncVideoCaptureUI extends AsyncTask<Void, Float, Void> {
    private TextView redDot;
    private TextView rec;
    private Chronometer timer;
    private Context mContext;
    private View view;

    public AsyncVideoCaptureUI(Context applicationContext, View viewById, Chronometer timer) {

        this.mContext =applicationContext;
        this.view = viewById;
        this.timer = timer;

    }



    @Override
    protected void onPreExecute(){
        redDot = (TextView) view.findViewById(R.id.rec_dot);
        rec = (TextView) view.findViewById(R.id.rec);

        redDot.setVisibility(View.VISIBLE);
        rec.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);


    }

    @Override
    protected Void doInBackground(Void... params) {

        while(isCancelled() != true){
            try {
                Thread.sleep(1000);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        redDot.setVisibility(View.GONE);
        rec.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled(){
        redDot.setVisibility(View.GONE);
        rec.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
    }

    @Override
    protected final void onProgressUpdate(Float... values){
        redDot.setVisibility(View.GONE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redDot.setVisibility(View.VISIBLE);
    }


}
