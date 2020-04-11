package com.project.intellispirit.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;

public class SplashActivity extends AppCompatActivity {
ImageView imageView_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView_logo=findViewById(R.id.imageView_logo);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        imageView_logo.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, 3000);
    }
}
