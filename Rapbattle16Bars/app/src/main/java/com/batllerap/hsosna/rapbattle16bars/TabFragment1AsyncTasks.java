package com.batllerap.hsosna.rapbattle16bars;

import android.app.ProgressDialog;
import android.content.Context;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark on 20.01.2016.
 */
public class TabFragment1AsyncTasks extends android.os.AsyncTask<Object, Void, Integer> {

   /* private Context context;

    ProgressDialog pd;

    public TabFragment1AsyncTasks(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        pd = ProgressDialog.show(context, "", "Battles werden geladen", false);
    }
*/
    @Override
    protected Integer doInBackground(Object... params) {
        String task = (String) params[0];
        if (task == "trending") {
            List<BattleOverview> dataRequest = (List<BattleOverview>) params[1];
            BattleOverview[] temp = new BattleOverview[0];
            try {
                temp = BattleController.getTrendingBattles(0, 50).getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataRequest.addAll(Arrays.asList(temp));
            return 1;
        } else if (task == "open") {
            List<BattleOverview> dataBattleOverview = (List<BattleOverview>) params[1];
            BattleOverview[] temp = new BattleOverview[0];
            try {
                temp = BattleController.getOpenForVotingBattles( 0, 50).getData();

            } catch (IOException e) {
                e.printStackTrace();
            }
            dataBattleOverview.addAll(Arrays.asList(temp));
            return 2;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {

            MyBus.getInstance().post(new AsyncTaskResult(result));

    }
}
