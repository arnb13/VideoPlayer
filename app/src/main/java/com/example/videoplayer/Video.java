package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class Video extends AppCompatActivity {
    VideoView videoView;
    int position;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoView.stopPlayback();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        position = getIntent().getIntExtra("position", 0);
        arrayList = getIntent().getStringArrayListExtra("array");


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse(arrayList.get(position));


        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position + 1 == arrayList.size()) {
                    position = 0;
                }

                Uri uri = Uri.parse(arrayList.get(position));
                videoView.setVideoURI(uri);
                videoView.start();

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position - 1 < 0) {
                    position = arrayList.size() - 1;
                }
                Uri uri = Uri.parse(arrayList.get(position));
                videoView.setVideoURI(uri);
                videoView.start();

            }
        });
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();

        //videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Uri uri = Uri.parse(arrayList.get(position = position + 1));
                videoView.setVideoURI(uri);
                videoView.start();

            }
        });








    }
}
