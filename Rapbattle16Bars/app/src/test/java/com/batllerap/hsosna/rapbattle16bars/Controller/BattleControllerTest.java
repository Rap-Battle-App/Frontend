package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattlePreview;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woors on 15.11.2015.
 */
public class BattleControllerTest {

    @Test
    public void testGetTrendingBattles() throws Exception {
        BattlePreview[] prev = BattleController.getTrendingBattles(5,1);
        if (prev.length > 5){
            Assert.fail();
        }
    }

    @Test
    public void testGetOpenForVotingBattles() throws Exception {

        BattlePreview[] prev = BattleController.getOpenForVotingBattles(5,1,1);
        if (prev.length > 5){
            Assert.fail();
        }
    }

    @Test
    public void testGetCompletedBattles() throws Exception {

        BattlePreview[] prev = BattleController.getCompletedBattles(5,1,1);
        if (prev.length > 5){
            Assert.fail();
        }
    }

    @Test
    public void testGetOpenBattles() throws Exception {

        BattlePreview[] prev = BattleController.getOpenBattles(5);
        if (prev.length > 5){
            Assert.fail();
        }
    }

    @Test
    public void testVoteBattle() throws Exception {
        Assert.assertTrue(BattleController.voteBattle(2));
    }

    @Test
    public void testGetBattle() throws Exception {
        Battle battle = BattleController.getBattle(1);

    }

    @Test
    public void testGetRequestList() throws Exception {
        BattleController.getRequestList("testRapper");

    }

    @Test
    public void testSendRequest() throws Exception {

    }

    @Test
    public void testSendRequest1() throws Exception {

    }

    @Test
    public void testAnswerRequest() throws Exception {

    }
}