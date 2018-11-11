package com.yg.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.yg.common.User;

import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Login {
    public void Login() {
        String html = "http://jwgl.ntu.edu.cn/cjcx/";
        Cookie cookie = null;
        try {
            WebClient wc = new WebClient(BrowserVersion.CHROME);
            wc.getOptions().setUseInsecureSSL(true);
            wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(false); // 禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(true); // js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(0); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
            wc.getOptions().setDoNotTrackEnabled(false);
            wc.getCookieManager().setCookiesEnabled(true);
            HtmlPage page = wc.getPage(html);
            HtmlForm form = (HtmlForm) page.getElementById("form1");
            HtmlTextInput xh = (HtmlTextInput) form.getInputByName("xh");
            HtmlTextInput sfzh = (HtmlTextInput) form.getInputByName("sfzh");
            HtmlTextInput yzm = (HtmlTextInput) form.getInputByName("yzm");
            HtmlPasswordInput kl = (HtmlPasswordInput) form.getInputByName("kl");
            HtmlImage valiCodeImg = page.getFirstByXPath("//*[@id=\"loginTable\"]/tbody/tr[5]/td[2]/img");
            ImageReader imageReader = valiCodeImg.getImageReader();
            BufferedImage bufferedImage = imageReader.read(0);

            //方法1、人工识别验证码
            JFrame f2 = new JFrame();
            JLabel l = new JLabel();
            l.setIcon(new ImageIcon(bufferedImage));
            f2.getContentPane().add(l);
            f2.setSize(200, 200);
            f2.setTitle("验证码");
            f2.setVisible(true);
            String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");
            f2.setVisible(false);

            //方法二、使用OCR技术识别，识别率不理想
            //String valicodeStr = new Ocr().ChangeToString(bufferedImage);

            xh.setValueAttribute(User.xh);
            sfzh.setValueAttribute(User.sfzh);
            kl.setValueAttribute(User.kl);
            yzm.setValueAttribute(valicodeStr);
            HtmlInput btn = form.getInputByValue("登 录");
            HtmlPage htmlPage = btn.click(); // 点击
            cookie = wc.getCookieManager().getCookies().iterator().next();
            User.SessionId = cookie.getValue();
/*
            此方法在java1.8中过期
            cookie.stream().forEach(item -> {
                User.SessionId = item.getValue();
                System.out.println(User.SessionId);
            });*/
            wc.close();
            if (htmlPage.asText().contains("验证码有错！")) {
                System.out.println("验证码错误");
            }else {
                System.out.println("登录成功");
            }
        } catch (Exception e) {
        }
    }
}
