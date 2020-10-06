package com.example.music2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.music2.R;

public class MainActivity extends AppCompatActivity {

    Button D = null;
    Button Ri = null;
    Button M = null;
    Button F = null;
    Button S = null;
    Button L = null;
    Button X = null;

    private MediaPlayer Dou = new MediaPlayer();
    private MediaPlayer Re = new MediaPlayer();
    private MediaPlayer Mi = new MediaPlayer();
    private MediaPlayer Fa = new MediaPlayer();
    private MediaPlayer So = new MediaPlayer();
    private MediaPlayer La = new MediaPlayer();
    private MediaPlayer Xi = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        D = (Button) findViewById(R.id.D);
        Ri = (Button) findViewById(R.id.R);
        M = (Button) findViewById(R.id.M);
        F = (Button) findViewById(R.id.F);
        S = (Button) findViewById(R.id.S);
        L = (Button) findViewById(R.id.L);
        X = (Button) findViewById(R.id.X);



        final MediaPlayer Dou = MediaPlayer.create(this,R.raw.zhao);
        final MediaPlayer Re = MediaPlayer.create(this,R.raw.hong);
        final MediaPlayer Mi = MediaPlayer.create(this,R.raw.yi);
        final MediaPlayer Fa = MediaPlayer.create(this,R.raw.shi);
        final MediaPlayer So = MediaPlayer.create(this,R.raw.da);
        final MediaPlayer La = MediaPlayer.create(this,R.raw.s);
        final MediaPlayer Xi = MediaPlayer.create(this,R.raw.b);


        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dou.start() ;


            }
        });


        Ri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Re.start() ;


            }
        });



        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mi.start() ;


            }
        });



        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fa.start() ;


            }
        });



        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                So.start() ;


            }
        });



        L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                La.start() ;


            }
        });



        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Xi.start() ;


            }
        });

    }



}

