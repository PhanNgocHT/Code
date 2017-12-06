package com.zuckerteam.asyntask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.zuckerteam.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dung Ali on 9/18/2017.
 */

public class DownloadFileAudio extends AsyncTask<String, Void,String> {

    Handler handler;

    public DownloadFileAudio(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String pathAudio = "";
        try {
            URL url = new URL(strings[0]);
            String songName = strings[1];
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            pathAudio = Constant.pathFileAudio+songName+".mp3";
            FileOutputStream os = createFileOutputStream(Constant.pathFileAudio + songName + ".mp3");

            byte[] b = new byte[1024];
            int lenght = inputStream.read(b);
            while (lenght != -1) {
                os.write(b, 0, lenght);
                lenght = inputStream.read(b);
            }

            os.flush();
            os.close();
            inputStream.close();
            return pathAudio;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return pathAudio;
    }

    public FileOutputStream createFileOutputStream(String path) throws IOException {
        File f = new File(path);
        File fileParent = f.getParentFile();
        if (!fileParent.exists())
            fileParent.mkdirs();
        if (!f.exists())
            f.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(f);
        return outputStream;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Message message = new Message();
        message.what = Constant.WHAT_DOWNLOAD_FILE_AUDIO;
        message.obj = s;
        handler.sendMessage(message);
    }
}
