package com.example.audiovideoapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView myvideoView;
    private Button btnPlayVideo;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myvideoView=findViewById(R.id.myvideoView);
        btnPlayVideo=findViewById(R.id.btnPlayVideo);

        btnPlayVideo.setOnClickListener(MainActivity.this);
        mediaController = new MediaController(MainActivity.this);

    }

    @Override
    public void onClick(View view) {


        myvideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mow));
        myvideoView.setMediaController(mediaController);
        mediaController.setAnchorView(myvideoView);

        myvideoView.start();
    }
}