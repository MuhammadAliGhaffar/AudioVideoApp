package com.example.audiovideoapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //UI Components

    private VideoView myvideoView;
    private Button btnPlayVideo,btnPlayMusic,btnPauseMusic;
    private MediaController mediaController;
    private MediaPlayer playerAudio;
    private SeekBar mSeekBarVoume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myvideoView=findViewById(R.id.myvideoView);
        btnPlayVideo=findViewById(R.id.btnPlayVideo);
        btnPlayMusic=findViewById(R.id.btnPlayMusic);
        btnPauseMusic=findViewById(R.id.btnPauseMusic);
        mSeekBarVoume=findViewById(R.id.seekBarVolume);


        btnPlayVideo.setOnClickListener(MainActivity.this);
        btnPlayMusic.setOnClickListener(MainActivity.this);
        btnPauseMusic.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);

        playerAudio= MediaPlayer.create(this, R.raw.music);



    }

    @Override
    public void onClick(View buttonView) {


        switch (buttonView.getId()){

            case R.id.btnPlayVideo:
                myvideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mow));
                myvideoView.setMediaController(mediaController);
                mediaController.setAnchorView(myvideoView);
                myvideoView.start();
                break;

            case R.id.btnPlayMusic:

                playerAudio.start();
                Log.i("TAG","Play Music Button is Tapped");
                break;

            case R.id.btnPauseMusic:

                playerAudio.pause();
                Log.i("TAG","Pause Music Button is Tapped");
                break;
        }



    }
}