package com.zuckerteam.database;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/18/2017.
 */

public class FileManager {

    public ArrayList<File> getListFileMusic(File fileRoot) {
        ArrayList<File> arrFile = new ArrayList<>();
        File[] file = fileRoot.listFiles();
        for (File f : file) {
            if (f.getName().endsWith(".mp3")) {
                arrFile.add(f);
            }
        }
        return arrFile;
    }
}
