package com.batllerap.hsosna.rapbattle16bars;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark on 20.01.2016.
 */
public class TabFragment1AsyncTasks extends android.os.AsyncTask<Object, Void, Integer> {
    private CustomAdapter oAdapter;
    private CustomAdapter cAdapter;

    @Override
    protected Integer doInBackground(Object... params) {
        String task = (String) params[0];
        if (task == "trending") {
            List<BattleOverview> dataRequest = (List<BattleOverview>) params[1];
            this.cAdapter = (CustomAdapter)params[2];
            BattleOverview[] bla = new BattleOverview[0];
            try {
                bla = BattleController.getTrendingBattles(0, 50).getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataRequest.addAll(Arrays.asList(bla));
            return 1;
        } else if (task == "open") {
            List<BattleOverview> dataBattleOverview = (List<BattleOverview>) params[1];
            this.oAdapter = (CustomAdapter)params[2];
            BattleOverview[] bla = new BattleOverview[0];
            try {
                bla = BattleController.getOpenForVotingBattles( 0, 50).getData();

            } catch (IOException e) {
                e.printStackTrace();
            }
            dataBattleOverview.addAll(Arrays.asList(bla));
            return 2;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result == 1) {
            cAdapter.notifyDataSetChanged();
        } else if (result == 2) {
            oAdapter.notifyDataSetChanged();
        }
    }
}
