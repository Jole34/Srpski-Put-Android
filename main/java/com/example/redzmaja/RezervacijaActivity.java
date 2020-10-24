package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RezervacijaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        HomeScreenActivity.defaultActionBar(actionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervacija);

        String ime = getIntent().getStringExtra("ime");
        String godina =  getIntent().getStringExtra("godine");



        final MapeFragment gogleFr  = new MapeFragment(ime);
        final RezervacijaFragment rezervacijaFragment = new RezervacijaFragment(ime, godina);
        Button rezervacija = findViewById(R.id.button);
        Button google = findViewById(R.id.button2);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameFre, rezervacijaFragment).commit();


        rezervacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameFre, rezervacijaFragment).commit();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frameFre, gogleFr).commit();

            }
        });


    }

}