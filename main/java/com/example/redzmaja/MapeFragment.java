package com.example.redzmaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng manastir;
    private String naziv;
    private static HashMap <String, double[]> manastiri = new HashMap<>();

    static {
       manastiri.put("Мала Ремета", new double[]{45.110684, 19.744353});
       manastiri.put("Крушедол", new double[]{45.116450, 19.936290});
       manastiri.put("Петковица", new double[]{44.670790, 19.436270});
       manastiri.put("Јазак", new double[]{45.104560, 19.765180});
       manastiri.put("Врдник", new double[]{45.121360, 19.793990});

       manastiri.put("Грачаница", new double[]{42.597010, 21.193350});
       manastiri.put("Високи Дечани", new double[]{42.546276, 20.266243});
       manastiri.put("Зочиште", new double[]{42.379323, 20.704049});
       manastiri.put("Светих Архангела", new double[]{42.200823, 20.763815});

       manastiri.put("Благовештење", new double[]{44.148319, 20.629070});
       manastiri.put("Манастир Јовање", new double[]{43.934627, 20.267329});
       manastiri.put("У. Пресвете Богородице", new double[]{43.891323, 20.335991});
       manastiri.put("Манастир Преображење", new double[]{43.902713, 20.196109});
       manastiri.put("Манастир Сретење", new double[]{43.896025, 20.206298});

       manastiri.put("Острог", new double[]{42.675218, 19.031346});
       manastiri.put("Морача", new double[]{42.766316, 19.391487});
       manastiri.put("Пива", new double[]{43.137000, 18.819780});
       manastiri.put("Цетињски манастир", new double[]{42.387892, 18.921835});
       manastiri.put("Манастир Савина", new double[]{42.452206, 18.554447});
    }


    public MapeFragment(String ime){
        double[] lgln;
        String ime2;

        for (HashMap.Entry<String, double[]> entry : manastiri.entrySet()){
            lgln = entry.getValue();
            ime2 = entry.getKey();

            if (ime.equalsIgnoreCase(ime2)){
                manastir = new LatLng(lgln[0], lgln[1]);
                naziv=ime2;
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_mape_activitiy, null, false);

        SupportMapFragment mapFragmentF = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragmentF.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(11.0f);
        mMap.addMarker(new MarkerOptions().position(this.manastir).title(naziv));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(this.manastir));
    }
}