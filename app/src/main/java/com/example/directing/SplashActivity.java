package com.example.directing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


import android.os.Handler;

public class SplashActivity extends AppCompatActivity
{
    Handler handler;
    Animation zoom;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

        img = (ImageView) findViewById(R.id.imageVieww);

        zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        zoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        img.startAnimation(zoom);

       handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,Background.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}

