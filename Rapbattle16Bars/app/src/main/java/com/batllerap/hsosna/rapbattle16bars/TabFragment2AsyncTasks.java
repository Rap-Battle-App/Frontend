package com.batllerap.hsosna.rapbattle16bars;

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


    private CustomAdapter oAdapter;
    private ChallengeAdapter cAdapter;
    /*
    private List<BattleOverview> dataBattleOverview = new ArrayList<>();
    private List<Request> dataRequest = new ArrayList<>();
    private String userString = null;

    public TabFragment2AsyncTasks(List<BattleOverview> data, CustomAdapter oAdapter) {
        this.dataBattleOverview = data;
        this.oAdapter = oAdapter;
    }

    public TabFragment2AsyncTasks(String userName, List<Request> data, ChallengeAdapter cAdapter) {
        this.userString = userName;
        this.dataRequest = data;
        this.cAdapter = cAdapter;
    }
*/
    @Override
    protected Integer doInBackground(Object... params) {
        String username = (String) params[0];
        if (username != null) {
            List<Request> dataRequest = (List<Request>) params[1];
            this.cAdapter = (ChallengeAdapter)params[2];
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
            this.oAdapter = (CustomAdapter)params[2];
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
        if (result == 1) {
            cAdapter.notifyDataSetChanged();
        } else if (result == 2) {
            oAdapter.notifyDataSetChanged();
        }
    }
}
