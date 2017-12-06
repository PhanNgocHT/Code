package com.zuckerteam.database;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/5/2017.
 */

public interface DatabaseInterface<T> {
    abstract boolean openDatabase();
    boolean closeDatabase();
    boolean insertData(T object,String tableName);
    boolean updateData(T object,String tableName);
    boolean deleteData(T object,String tableName);
    void checkDatabase();
    void handleCoppyDatabaseFromAssets();
    ArrayList<Object> getData(String tableName);
}
