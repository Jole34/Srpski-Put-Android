package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.redzmaja.HomeScreenActivity.*;

public class KosovoActivity extends AppCompatActivity implements SensorEventListener {

    private  Intent at;
    private SensorManager sensorManager;
    private Sensor sensorShake;

    private  boolean isSensor;
    private boolean firstTime = false;

    private float currX, currY, currZ;
    private float lastX, lastY, lastZ;
    private float xRazlika, yRazlika, zRazlika;

    private boolean stopped  = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://srpski-put.firebaseio.com/manastiriKS.json", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Manastir> mn = new ArrayList();
                        final TextView odabirKS =  findViewById(R.id.odabirKS);
                        final TextView odabranKS = findViewById(R.id.odabranaKS);
                        final Button detaljiKS = findViewById(R.id.detaljnijeKS);

                        detaljiKS.setVisibility(View.GONE);

                        try {
                            JSONObject mm1 = response.getJSONObject("-MCmQ3t74fxnji_8KP4E");
                            Manastir m1 = new Manastir((String) mm1.getString("slika"), (String) mm1.getString("godina"), (String) mm1.getString("ime"));


                            JSONObject mm2 = response.getJSONObject("-MCmQaD6EvArC6w6C_2g");
                            Manastir m2 = new Manastir((String) mm2.getString("slika"), (String) mm2.getString("godina"), (String) mm2.getString("ime"));

                            JSONObject mm3 = response.getJSONObject("-MCmRylVncDhRrAN5h8J");
                            Manastir m3 = new Manastir((String) mm3.getString("slika"), (String) mm3.getString("godina"), (String) mm3.getString("ime"));

                            JSONObject mm4 = response.getJSONObject("-MCmSaMtGIy82n9Hdozs");
                            Manastir m4 = new Manastir((String) mm4.getString("slika"), (String) mm4.getString("godina"), (String) mm4.getString("ime"));


                            mn.add(m1);
                            mn.add(m2);
                            mn.add(m4);
                            mn.add(m3);

                            ListView ls2 = (ListView) findViewById(R.id.listaManastiraK);


                            ManastirListAdapter adapter2 = new ManastirListAdapter(KosovoActivity.this, R.layout.adapter_view, mn);
                            ls2.setAdapter(adapter2);

                            final Manastir[] selected = new Manastir[1];
                            ls2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selected[0] = (Manastir) parent.getItemAtPosition(position);
                                    odabirKS.setText("Одабрали сте: ");
                                    odabranKS.setText(selected[0].getNaziv());
                                    detaljiKS.setVisibility(View.VISIBLE);

                                }
                            });

                            detaljiKS.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent rezer = new Intent(KosovoActivity.this, RezervacijaActivity.class);
                                    rezer.putExtra("ime", odabranKS.getText());
                                    rezer.putExtra("godine",  selected[0].getGodina());
                                    rezer.putExtra("aktivnost", 2);

                                    startActivity(rezer);
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);

        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if ((sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) != null ){
            sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensor = true;
        } else {
            isSensor = false;
        }

        at = new Intent(KosovoActivity.this, BackgroundSoundService.class);
        startService(at);

        ActionBar actionBar = getSupportActionBar();
        defaultActionBar(actionBar);

        setContentView(R.layout.activity_kosovo);
    }



    @Override
    protected void onStop() {
        super.onStop();
        stopService(at);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(at);
        if(isSensor) {
            sensorManager.unregisterListener(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!stopped){
            startService(at);
        }

        if(isSensor){
            sensorManager.registerListener(this, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        
        currX = event.values[0];
        currY = event.values[1];
        currZ = event.values[2];


        if (firstTime){
            xRazlika = Math.abs(lastX - currX);
            yRazlika = Math.abs(lastY - currY);
            zRazlika = Math.abs(lastZ - currZ);

            boolean x = false;
            boolean y =false;
            boolean z = false;

            float prag = 5f;

            if ( (xRazlika > prag) && (yRazlika > prag)){
                x = true;
            }
            if ( (xRazlika > prag) && (zRazlika > prag)){
                z = true;
            }
            if ( (yRazlika > prag) && (zRazlika > prag)){
                y = true;
            }

            if (x || y || z){
                stopService(at);
                stopped = true;
            }
        }

        lastX = currX;
        lastY = currY;
        lastZ = currZ;

        firstTime = true;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}