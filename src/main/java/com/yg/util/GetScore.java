package com.yg.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yg.common.User;
import com.yg.domain.Score;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetScore {
    public List<Score> getScore() throws IOException {
        List<Score> scoreList = new ArrayList<Score>();
        Connection con = Jsoup.connect("http://jwgl.ntu.edu.cn/cjcx/Data/ScoreAllData.aspx")
                .cookie("ASP.NET_SessionId", User.SessionId)
                .referrer("http://jwgl.ntu.edu.cn/cjcx/Main.aspx")
                .ignoreContentType(true)
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
        Document doc = con.post();

        JSONObject jsonObject = JSON.parseObject(doc.body().text());
        Integer count = (Integer) jsonObject.get("totalCount");
        System.out.println(count);
        for(int i=0;i<count;i+=20) {
            con = Jsoup.connect("http://jwgl.ntu.edu.cn/cjcx/Data/ScoreAllData.aspx")
                    .cookie("ASP.NET_SessionId",User.SessionId)
                    .referrer("http://jwgl.ntu.edu.cn/cjcx/Main.aspx")
                    .ignoreContentType(true)
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
            con.data("start",String.valueOf(i));
            con.data("limit","20");
            Document scores = con.post();
            JSONObject scoresObject = JSON.parseObject(scores.body().text());
            JSONArray jsonArray = scoresObject.getJSONArray("data");
            Gson g = new Gson();
            List<Score> ps = g.fromJson(jsonArray.toString(), new TypeToken<List<Score>>(){}.getType());
            if(ps!=null) {
                scoreList.addAll(ps);
            }
        }
        return scoreList;
    }
}
