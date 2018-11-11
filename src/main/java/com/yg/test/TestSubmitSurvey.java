package com.yg.test;

import com.yg.util.Login;
import com.yg.util.SubmitSurvey;

public class TestSubmitSurvey {
    public static void main(String[] args) throws Exception {
        Login log = new Login();
        log.Login();
        SubmitSurvey s = new SubmitSurvey();
        s.submitSurvey();
    }
}
