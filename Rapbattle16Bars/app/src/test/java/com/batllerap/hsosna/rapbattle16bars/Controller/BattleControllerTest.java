package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by woors on 15.11.2015.
 */
public class BattleControllerTest {
    @Test
    public void testVoteBattle() throws Exception {
        Assert.assertTrue(BattleController.voteBattle(2,1));
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