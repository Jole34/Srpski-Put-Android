package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int  SPTIMEOUT = 5000;
    Animation topAnim, bottomAnim, txttop, txtbott;
    ImageView img, zastava;
    TextView citat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getWindow().getDecorView().setBackgroundColor(Color.rgb(25,25,25));


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        txttop = AnimationUtils.loadAnimation(this, R.anim.toptxt_animation);
        txtbott = AnimationUtils.loadAnimation(this, R.anim.bottomtxt_animation);


        citat = findViewById(R.id.citat);
        citat.setAnimation(txtbott);
        citat.setAnimation(txttop);

        zastava =  findViewById(R.id.zastava);
        zastava.setAnimation(topAnim);
        zastava.setAnimation(bottomAnim);

        img =  findViewById(R.id.logoPng);
        img.setAnimation(topAnim);
        img.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPTIMEOUT);
    }
}
