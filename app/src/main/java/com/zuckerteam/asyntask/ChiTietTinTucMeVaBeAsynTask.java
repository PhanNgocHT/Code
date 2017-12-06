package com.zuckerteam.asyntask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zuckerteam.Constant;
import com.zuckerteam.mevabe.main.MainActivity;
import com.zuckerteam.model.ChiTietTinTucMeVaBe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/1/2017.
 * Lớp PagerAdapter dùng chung cho các item chuyên mục
 * Dinh dưỡng bà bầu ,
 * kinh nghiệm đi đẻ ,
 * Chăm sóc trẻ
 * Dạy con
 * Góc hài hước
 */

public class ChiTietTinTucMeVaBeAsynTask extends AsyncTask<String, Void, ArrayList<ChiTietTinTucMeVaBe>> {
    public static final int WHAT_ON_POST_EXECUTE_TIN_TUC_ME_VA_BE = 1;
    private Handler handler;
    private ProgressDialog dialog;
    private Context context;
    private GetData getData;

    public ChiTietTinTucMeVaBeAsynTask(GetData getData) {
        this.getData = getData;
    }

    public ChiTietTinTucMeVaBeAsynTask(Handler handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if(dialog==null) {
            dialog = new ProgressDialog(this.context);
            dialog.setMessage("Loading...");
            dialog.show();
        }
        super.onPreExecute();
    }

    @Override
    protected ArrayList<ChiTietTinTucMeVaBe> doInBackground(String... strings) {
        String link = strings[0];
        ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();//lay ma nguon.
            Element solr_content_class = document.select("div.solr-content").get(0);
            Elements li = solr_content_class.getElementsByTag("li");
            for (Element element : li) {
                String path = element.getElementsByTag("a").get(0).attr("href");
                //Vì trang này cắt bớt đường dần nên phải thêm vào để load được nên wepView.
                String duongDanTinTuc = Constant.duongDanTrangChu + path;

                String imgAnhTinTuc = element.getElementsByTag("img").attr("src");
                Element solr_sapo_class = element.select("div.solr-sapo").get(0);
                String tieuDeTinTuc = solr_sapo_class.getElementsByTag("a").text();
                String moTaTinTuc = solr_sapo_class.getElementsByTag("p").text();

                chiTietTinTucMeVaBes.add(new ChiTietTinTucMeVaBe(duongDanTinTuc, imgAnhTinTuc, tieuDeTinTuc, moTaTinTuc));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return chiTietTinTucMeVaBes;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<ChiTietTinTucMeVaBe> tinTucMeVaBes) {
        super.onPostExecute(tinTucMeVaBes);
        Message msg = new Message();
        msg.what = WHAT_ON_POST_EXECUTE_TIN_TUC_ME_VA_BE;
        msg.obj = tinTucMeVaBes;
        handler.sendMessage(msg);

        if (dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        //getData.onSuccess(tinTucMeVaBes);
    }

    public interface GetData {
        void onSuccess(ArrayList<ChiTietTinTucMeVaBe> tinTucMeVaBes);

        void onFail();
    }
}
