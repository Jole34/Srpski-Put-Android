package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import static com.example.redzmaja.HomeScreenActivity.defaultActionBar;

public class CrnaGoraActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //rad sa bazom

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://srpski-put.firebaseio.com/manastiriCG.json", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Manastir> mn = new ArrayList();
                        final TextView odabirCG =  findViewById(R.id.odabirCG);
                        final TextView odabranCG = findViewById(R.id.odabranaCG);
                        final Button detaljiCG = findViewById(R.id.detaljiCG);
                        detaljiCG.setVisibility(View.GONE);

                        try {
                            JSONObject mm1 = response.getJSONObject("-MCmFRg7RhRjUXKde-vi");
                            Manastir m1 = new Manastir((String) mm1.getString("slika"), (String) mm1.getString("godina"), (String) mm1.getString("ime"));


                            JSONObject mm2 = response.getJSONObject("-MCmFkl58YSk3unMyt0e");
                            Manastir m2 = new Manastir((String) mm2.getString("slika"), (String) mm2.getString("godina"), (String) mm2.getString("ime"));

                            JSONObject mm3 = response.getJSONObject("-MCmG1fnfVjQD5l2TmGn");
                            Manastir m3 = new Manastir((String) mm3.getString("slika"), (String) mm3.getString("godina"), (String) mm3.getString("ime"));

                            JSONObject mm4 = response.getJSONObject("-MCmGcXT5r_HJGU7-ISs");
                            Manastir m4 = new Manastir((String) mm4.getString("slika"), (String) mm4.getString("godina"), (String) mm4.getString("ime"));

                            JSONObject mm5 = response.getJSONObject("-MCmH44lRa4gO3XdMW12");
                            Manastir m5 = new Manastir((String) mm5.getString("slika"), (String) mm5.getString("godina"), (String) mm5.getString("ime"));

                            mn.add(m1);
                            mn.add(m2);
                            mn.add(m4);
                            mn.add(m3);
                            mn.add(m5);


                            ListView ls2 = (ListView) findViewById(R.id.listaManastiraCG);


                            ManastirListAdapter adapter2 = new ManastirListAdapter(CrnaGoraActivity.this, R.layout.adapter_view, mn);
                            ls2.setAdapter(adapter2);

                            final Manastir[] selected = new Manastir[1];
                            ls2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selected[0] = (Manastir) parent.getItemAtPosition(position);
                                    odabirCG.setText("Одабрали сте: ");
                                    odabranCG.setText(selected[0].getNaziv());
                                    detaljiCG.setVisibility(View.VISIBLE);
                                }
                            });

                            detaljiCG.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent rezer = new Intent(CrnaGoraActivity.this, RezervacijaActivity.class);
                                    rezer.putExtra("ime", odabranCG.getText());
                                    rezer.putExtra("godine",  selected[0].getGodina());
                                    rezer.putExtra("aktivnost", 3);
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

        ActionBar actionBar = getSupportActionBar();
        defaultActionBar(actionBar);

        setContentView(R.layout.activity_crna_gora);
    }
}