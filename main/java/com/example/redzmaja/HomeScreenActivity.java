package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import static com.example.redzmaja.R.id.cgStart;
import static com.example.redzmaja.R.id.fcStart;
import static com.example.redzmaja.R.id.ksStart;
import static com.example.redzmaja.R.id.ockStart;

public class HomeScreenActivity extends AppCompatActivity {
    Button frGora;
    Button kosovo;
    Button cg;
    Button ock;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        defaultActionBar(actionBar);
        setContentView(R.layout.activity_home_screen);

        frGora =(Button) findViewById(fcStart);
        kosovo = (Button) findViewById(ksStart);
        cg = (Button) findViewById(cgStart);
        ock = (Button) findViewById(ockStart);

        frGora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fruskagora = new Intent(HomeScreenActivity.this, FruskaGoraActivity1.class);
                startActivity(fruskagora);
            }
        });

        kosovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kosovoI = new Intent(HomeScreenActivity.this, KosovoActivity.class);
                startActivity(kosovoI);
            }
        });

        cg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crnaGora = new Intent(HomeScreenActivity.this, CrnaGoraActivity.class);
                startActivity(crnaGora);
            }
        });

        ock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ocKlisura = new Intent(HomeScreenActivity.this, OKActivity.class);
                startActivity(ocKlisura);
            }
        });

    }


    public static void defaultActionBar(ActionBar actionBar){
        actionBar.setTitle(" "+"Српски пут");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(64, 224,208)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf =  getMenuInflater();
        inf.inflate(R.menu.mainmenu, menu);

        return true;
    }
}