package com.batllerap.hsosna.rapbattle16bars;

import android.app.ProgressDialog;
import android.content.Context;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Request;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark on 20.01.2016.
 */
public class TabFragment2AsyncTasks extends android.os.AsyncTask<Object, Void, Integer> {



   /* private Context context;

    ProgressDialog pd;

    public TabFragment2AsyncTasks(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        pd = ProgressDialog.show(context, "", "Battles werden geladen", false);
    }*/

    @Override
    protected Integer doInBackground(Object... params) {
        String username = (String) params[0];
        if (username != null) {
            List<Request> dataRequest = (List<Request>) params[1];

            Request[] bla = new Request[0];
            try {
                bla = BattleController.getRequestList(username).getOpponent_requests();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataRequest.addAll(Arrays.asList(bla));
            return 1;
        } else {
            List<BattleOverview> dataBattleOverview = (List<BattleOverview>) params[1];

            BattleOverview[] bla = new BattleOverview[0];
            try {
                bla = BattleController.getOpenBattles(0).getData();

            } catch (IOException e) {
                e.printStackTrace();
            }
            dataBattleOverview.addAll(Arrays.asList(bla));
            return 2;
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        MyBus.getInstance().post(new AsyncTaskResult(result));
    }
}
