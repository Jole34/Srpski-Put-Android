package com.example.redzmaja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class OKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://srpski-put.firebaseio.com/manastiriOK.json", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Manastir> mn = new ArrayList();
                        final TextView odabirOK =  findViewById(R.id.odabirOK);
                        final TextView odabranOK = findViewById(R.id.odabranaOK);
                        final Button detaljiOK = findViewById(R.id.detaljiOK);
                        detaljiOK.setVisibility(View.GONE);

                        try {
                            JSONObject mm1 = response.getJSONObject("-MCmXLiD1zmOaNIxyIkx");
                            Manastir m1 = new Manastir((String) mm1.getString("slika"), (String) mm1.getString("godina"), (String) mm1.getString("ime"));


                            JSONObject mm2 = response.getJSONObject("-MCmXyIGRr1D4JXYuatr");
                            Manastir m2 = new Manastir((String) mm2.getString("slika"), (String) mm2.getString("godina"), (String) mm2.getString("ime"));

                            JSONObject mm3 = response.getJSONObject("-MCmYfq6pn6RU7HhL5xn");
                            Manastir m3 = new Manastir((String) mm3.getString("slika"), (String) mm3.getString("godina"), (String) mm3.getString("ime"));

                            JSONObject mm4 = response.getJSONObject("-MCmYruJyUl1aijRiBvH");
                            Manastir m4 = new Manastir((String) mm4.getString("slika"), (String) mm4.getString("godina"), (String) mm4.getString("ime"));

                            JSONObject mm5 = response.getJSONObject("-MCmZ5XaIe-wIJ8mQF1g");
                            Manastir m5 = new Manastir((String) mm5.getString("slika"), (String) mm5.getString("godina"), (String) mm5.getString("ime"));

                            mn.add(m1);
                            mn.add(m2);
                            mn.add(m4);
                            mn.add(m3);
                            mn.add(m5);

                            ListView ls2 = (ListView) findViewById(R.id.listaManastiraOK);


                            ManastirListAdapter adapter2 = new ManastirListAdapter(OKActivity.this, R.layout.adapter_view, mn);
                            ls2.setAdapter(adapter2);

                            final Manastir[] selected = new Manastir[1];
                            ls2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selected[0] = (Manastir) parent.getItemAtPosition(position);
                                    odabirOK.setText("Одабрали сте: ");
                                    odabranOK.setText(selected[0].getNaziv());
                                    detaljiOK.setVisibility(View.VISIBLE);
                                }
                            });

                            detaljiOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent rezer = new Intent(OKActivity.this, RezervacijaActivity.class);
                                    rezer.putExtra("ime", odabranOK.getText());
                                    rezer.putExtra("godine",  selected[0].getGodina());
                                    rezer.putExtra("aktivnost", 4);

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
        HomeScreenActivity.defaultActionBar(actionBar);

        setContentView(R.layout.activity_o_k);
    }
}