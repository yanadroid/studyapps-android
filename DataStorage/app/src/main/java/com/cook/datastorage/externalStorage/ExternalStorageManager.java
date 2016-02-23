package com.cook.datastorage.externalStorage;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.cook.datastorage.helper.FileHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by roma on 19.02.16.
 */
public class ExternalStorageManager {

    private static Context context;
    private static final String TAG = "ExternalStorageManager";
    private static final String EXTERNAL_FILE_NAME = "EXTERNAL_FILE_NAME";

    public static void init(Context context) {
        ExternalStorageManager.context = context;
    }

    /**
     * Checks if external storage is available for read and write
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if external storage is available to at least read
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Creates a directory for a new photo album in the public pictures directory:
     */
    public static File getAlbumStorageDir(String albumName) {
        File f = Environment.getExternalStoragePublicDirectory(null);

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    public static File getExternalDir() {
        return context.getExternalFilesDir(EXTERNAL_FILE_NAME);
    }

    public static File getExternalCacheDir() {
        return context.getExternalCacheDir();
    }

    public static void saveInExternalDir(String text) {
        FileHelper.saveInFile(text, getExternalDir());
    }

    public static String loadExternalDir() {
        return FileHelper.loadFromFile(getExternalDir());
    }

    public static void saveInExternalCacheDir(String text) {
        FileHelper.saveInFile(text, getExternalCacheDir());
    }

    public static String loadInternalCacheDir() {
        return FileHelper.loadFromFile(getExternalCacheDir());
    }


}