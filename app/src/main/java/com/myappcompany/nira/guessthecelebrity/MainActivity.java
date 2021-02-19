package com.myappcompany.nira.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
String result;
    //Download Webcontent(HTML)
    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result=" ";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url=new URL(urls[0]);
                InputStream in= urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data!=-1){
                    char current = (char)data;
                    result += current;
                    data= reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";
        }
    }

    public void downloadimage(View view){
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try{
            myImage = task.execute("").get();
            imageView.setImageBitmap(myImage);
        }catch(Exception e){
            e.printStackTrace();
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set the imageview
            imageView = findViewById(R.id.imageview);//add imageview in view
             String result=null;
        DownloadTask task = new DownloadTask();
        try{
            result = task.execute("https://www.imdb.com/list/ls052283250/").get();

        } catch(Exception e){
            e.printStackTrace();
        }
        Log.i("result",result);

    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}
