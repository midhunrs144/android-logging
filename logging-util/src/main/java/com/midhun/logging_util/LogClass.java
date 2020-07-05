package com.midhun.logging_util;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;

public class LogClass {
    public static void removeLines(File file, int startline, int numlines)
    {
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(file));

            //String buffer to store contents of the file
            StringBuffer sb=new StringBuffer("");

            //Keep track of the line number
            int linenumber=1;
            String line;

            while((line=br.readLine())!=null)
            {
                //Store each valid line in the string buffer
                if(linenumber<startline||linenumber>=startline+numlines)
                    sb.append(line+"\n");
                linenumber++;
            }
            if(startline+numlines>linenumber)
                System.out.println("End of file reached.");
            br.close();

            FileWriter fw=new FileWriter(file);
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Something went horribly wrong: "+e.getMessage());
        }
    }

    public static BufferedWriter out;
    public static void createFileOnDevice(String message) throws IOException {
        /*
         * Function to initially create the log file and it also writes the time of creation to file.
         */
        File Root = Environment.getExternalStorageDirectory();
        Log.d("canWrite",Root.canWrite()+"");
        if(Root.canWrite()){
            File  LogFile = new File(Root, "CustomsLogLib.txt");
            Log.d("fileSizeInMB",(LogFile.length()/1024)/1024+"mb");

            if ((LogFile.length()/1024)/1024 > 1){
                Log.d("fileSizeInMB",(LogFile.length()/1024)/1024+"mb  inside if");

                int linesInFile = 0;
                InputStream inputStream = new FileInputStream(LogFile);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
                try {
                    lineNumberReader.skip(Long.MAX_VALUE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                linesInFile = lineNumberReader.getLineNumber() + 1;
                int linesToBeDeleted = 0;
                Log.d("fileSizeInMB",(LogFile.length()/1024)/1024+"mb  inside if"+linesInFile);
                linesToBeDeleted = Integer.valueOf(linesInFile/4);
                Log.d("fileSizeInMB",linesToBeDeleted + "lines to be deleted");

                removeLines(LogFile,1,linesToBeDeleted);




            }

            FileWriter LogWriter = new FileWriter(LogFile, true);
            out = new BufferedWriter(LogWriter);
            Date date = new Date();
            out.write( String.valueOf("\r\n" +date.getDate() + "  ---  " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()+ " ---" +message));
            out.close();

        }


    }



}

