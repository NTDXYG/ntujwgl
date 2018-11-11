package com.yg.util;

import com.yg.common.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class SubmitSurvey {
    public void submitSurvey() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        FileReader file = new FileReader("F://新建文本文档.txt");
        BufferedReader reader = new BufferedReader(file);
        while (true) {
            String nextline = reader.readLine();
            if (nextline == null)
                break;
            String[] split = nextline.split(":");
            map.put(split[0], split[1].trim());
        }
        reader.close();
        Connection con = Jsoup.connect("http://jwgl.ntu.edu.cn/cjcx/actions/SubmitSurvey.aspx")
                .cookie("ASP.NET_SessionId", User.SessionId)
                .referrer("http://jwgl.ntu.edu.cn/cjcx/Main.aspx").ignoreContentType(true)
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36")
                .data(map);
        Document doc = con.post();
        System.out.println(doc.body().text());
    }
}
