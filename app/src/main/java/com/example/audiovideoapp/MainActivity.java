package com.example.audiovideoapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    //UI Components

    private VideoView myvideoView;
    private Button btnPlayVideo,btnPlayMusic,btnPauseMusic;
    private MediaController mediaController;
    private MediaPlayer playerAudio;
    private SeekBar mSeekBarVoume,mSeekBarMove;
    private AudioManager mAudioManager;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myvideoView=findViewById(R.id.myvideoView);
        btnPlayVideo=findViewById(R.id.btnPlayVideo);
        btnPlayMusic=findViewById(R.id.btnPlayMusic);
        btnPauseMusic=findViewById(R.id.btnPauseMusic);
        mSeekBarVoume=findViewById(R.id.seekBarVolume);
        mSeekBarMove=findViewById(R.id.seekBarMove);


        btnPlayVideo.setOnClickListener(MainActivity.this);
        btnPlayMusic.setOnClickListener(MainActivity.this);
        btnPauseMusic.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);

        playerAudio= MediaPlayer.create(this, R.raw.music);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maximumVoulumeOfUserDevice = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVoulumeOfUserDevice =  mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //int minimumVoulumeOfUserDevice =  mAudioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC);

        mSeekBarVoume.setMax(maximumVoulumeOfUserDevice);
        mSeekBarVoume.setProgress(currentVoulumeOfUserDevice);

        mSeekBarVoume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){


                    //Toast.makeText(MainActivity.this,progress+"",Toast.LENGTH_SHORT).show();

                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);


                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBarMove.setOnSeekBarChangeListener(MainActivity.this);
        mSeekBarMove.setMax(playerAudio.getDuration());

        playerAudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                timer.cancel();
                Toast.makeText(MainActivity.this,"Music is Ended", Toast.LENGTH_SHORT).show();

            }
        });


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
                timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        mSeekBarMove.setProgress(playerAudio.getCurrentPosition());
                    }
                },0,1000);


                Log.i("TAG","Play Music Button is Tapped");
                break;

            case R.id.btnPauseMusic:

                playerAudio.pause();
                timer.cancel();
                Log.i("TAG","Pause Music Button is Tapped");
                break;
        }



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            //Toast.makeText(MainActivity.this,progress+"", Toast.LENGTH_SHORT).show();
            playerAudio.seekTo(progress);

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        playerAudio.pause();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        playerAudio.start();
    }
}