package com.myappcompany.nira.guessthecelebrity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

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
    }

    public class ImageDownloader extends AsyncTask<String, void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }
    }

}
