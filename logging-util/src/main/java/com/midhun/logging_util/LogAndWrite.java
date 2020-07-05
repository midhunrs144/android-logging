package com.midhun.logging_util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class LogAndWrite extends AsyncTask<String, Void, Void> {

    static LogAndWrite logAndWrite;

    public static LogAndWrite newInstance(){
        if (logAndWrite==null){
            return new LogAndWrite();
        }
        else return logAndWrite;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            LogClass.createFileOnDevice(strings[0]);
            Log.d("Log Util",strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
