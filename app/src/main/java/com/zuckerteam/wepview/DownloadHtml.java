package com.zuckerteam.wepview;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zuckerteam.Constant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Dung Ali on 8/24/2017.
 */

public class DownloadHtml extends AsyncTask<String, Void, String> {
    Handler handler;

    public DownloadHtml(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        String thanTinTucHtml = "";
        String a = "";
        try {
            Document document = Jsoup.connect(link).get();//lay ma nguon.
            Element e1 = document.select("h1.d-title").get(0);
            String tieuDe = e1.toString();
            Element e2 = document.select("h2.sapo.fl.mgt10.mgb10").get(0);
            String moTa = e2.toString();
            Element e3 = document.select("div.detail_content.fl.mgt15").get(0);
            String noiDung = e3.toString();
            thanTinTucHtml=tieuDe+moTa+noiDung;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thanTinTucHtml;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Message message = new Message();
        message.what = Constant.WHAT_DOWNLOAD_HTML;
        message.obj = s;
        handler.sendMessage(message);
    }
}
