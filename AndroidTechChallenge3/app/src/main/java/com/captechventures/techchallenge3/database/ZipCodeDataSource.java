package com.captechventures.techchallenge3.database;

import android.content.Context;
import android.util.Log;

import com.captechventures.techchallenge3.model.ZipCodeEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mluansing on 10/9/17.
 */

public class ZipCodeDataSource implements Serializable {

    // Logging tag
    private static final String TAG = ZipCodeDataSource.class.getSimpleName();
    ZipCodeOpenHelper zipCodeOpenHelper;

    public ZipCodeDataSource(Context context) {
        Log.d(TAG, "Creating new datasource");

        // creates new OpenHelper
        zipCodeOpenHelper = new ZipCodeOpenHelper(context);
    }

    public void open() {
        Log.d(TAG, "opening writable database");
        zipCodeOpenHelper.getWritableDatabase();
    }

    public void close() {
        Log.d(TAG, "closing database...");
        zipCodeOpenHelper.close();
    }

    public List<ZipCodeEntry> search(String zipSearch, String localitySearch) {
        Log.d(TAG, "searching for " + zipSearch + " and " + localitySearch);
        return zipCodeOpenHelper.search(zipSearch, localitySearch);
    }

}
