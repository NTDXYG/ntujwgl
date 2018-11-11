package com.yg.test;

import com.alibaba.fastjson.JSON;
import com.yg.util.GetScore;
import com.yg.util.Login;

import java.io.IOException;

public class TestGetScore {
    public static void main(String[] args) {
        Login log = new Login();
        log.Login();
        GetScore score = new GetScore();
        try {
            System.out.println(JSON.toJSONString(score.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
