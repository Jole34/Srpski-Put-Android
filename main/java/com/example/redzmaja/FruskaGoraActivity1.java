package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FruskaGoraActivity1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        ActionBar actionBar = getSupportActionBar();
        HomeScreenActivity.defaultActionBar(actionBar);

        setContentView(R.layout.activity_fruska_gora1);


        //rad sa bazom
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://srpski-put.firebaseio.com/manastiri.json", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Manastir> mn = new ArrayList();
                        final TextView odabirFG =  findViewById(R.id.odabirFG);
                        final TextView odabranFG = findViewById(R.id.odabranaFG);
                        final Button detaljiFG = findViewById(R.id.detaljiFG);
                        detaljiFG.setVisibility(View.GONE);

                        try {
                            JSONObject mm1 = response.getJSONObject("-MCkz1UQK8kqIUjuXdzR");
                            Manastir m1 = new Manastir((String) mm1.getString("slika"), (String) mm1.getString("godina"), (String) mm1.getString("ime"));

                            JSONObject mm2 = response.getJSONObject("-MCl-02gUc9lW7YDWKop");
                            Manastir m2 = new Manastir((String) mm2.getString("slika"), (String) mm2.getString("godina"), (String) mm2.getString("ime"));

                            JSONObject mm3 = response.getJSONObject("-MCl-MtzAEoOsEsTCrQR");
                            Manastir m3 = new Manastir((String) mm3.getString("slika"), (String) mm3.getString("godina"), (String) mm3.getString("ime"));

                            JSONObject mm4 = response.getJSONObject("-MCl-dbfxJLwcb-GCf23");
                            Manastir m4 = new Manastir((String) mm4.getString("slika"), (String) mm4.getString("godina"), (String) mm4.getString("ime"));

                            JSONObject mm5 = response.getJSONObject("-MCl01KBVULWTyaoV98f");
                            Manastir m5 = new Manastir((String) mm5.getString("slika"), (String) mm5.getString("godina"), (String) mm5.getString("ime"));

                            mn.add(m1);
                            mn.add(m2);
                            mn.add(m4);
                            mn.add(m3);
                            mn.add(m5);

                            ListView ls = (ListView) findViewById(R.id.listaManastiraF);


                            ManastirListAdapter adapter = new ManastirListAdapter(FruskaGoraActivity1.this, R.layout.adapter_view, mn);
                            ls.setAdapter(adapter);

                            final Manastir[] selected = new Manastir[1];
                            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selected[0] = (Manastir) parent.getItemAtPosition(position);
                                    odabirFG.setText("Одабрали сте: ");
                                    odabranFG.setText(selected[0].getNaziv());
                                    detaljiFG.setVisibility(View.VISIBLE);
                                }
                            });

                            detaljiFG.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent rezer = new Intent(FruskaGoraActivity1.this, RezervacijaActivity.class);
                                    rezer.putExtra("ime", odabranFG.getText());
                                    rezer.putExtra("godine",  selected[0].getGodina());
                                    rezer.putExtra("aktivnost", 1);

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf =  getMenuInflater();
        inf.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}