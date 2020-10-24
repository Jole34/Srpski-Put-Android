package com.example.redzmaja;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RezervacijaFragment extends Fragment {
    private NotificationManagerCompat notificationManager;
    private String ime;
    private String god;
    private static int  br = 0;



    public RezervacijaFragment(String ime, String god) {
        this.ime = ime;
        this.god = god;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rezervacija, container, false);
    }

    private void takeScreenshot() {

        String txtsad = (String.valueOf(br++));

        try {
            View v1 = getActivity().getWindow().getDecorView().getRootView();

            v1.setDrawingCacheEnabled(true);

            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Rezervacija "+txtsad , "Screen");


        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void posalji(View v) {
        String naslov = "Резервисали сте посету!";
        Notification notification = new NotificationCompat.Builder(getActivity(), Notifcation.KANAL_ID)
                .setContentTitle(naslov)
                .setSmallIcon(R.drawable.grb)
                .setContentText(this.ime+"  "+this.god)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        final TextView naziv = getActivity().findViewById(R.id.textView4);
        TextView godina = getActivity().findViewById(R.id.textView6);
        final EditText plIme = getActivity().findViewById(R.id.editTextTextPersonName2);

        final TextView listar = getActivity().findViewById(R.id.rezT);
        final Button bt = getActivity().findViewById(R.id.button3);
        bt.setVisibility(View.GONE);
        naziv.setText(this.ime);
        godina.setText(this.god);



        ArrayList<String> lista  = new ArrayList();

        lista.add("22.9.2020 Нови Сад, Београд 13:00");
        lista.add("22.9.2020 Загреб, Сплит 13:00");
        lista.add("22.9.2020 Сарајево, Бањалука 13:00");

        Spinner ls = getActivity().findViewById(R.id.listaDatuma);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ls.setAdapter(adapter);

        int duration = Toast.LENGTH_SHORT;


        Toast toast = Toast.makeText(this.getContext(), "Попуните поље име!", duration);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 10, 80);
        toast.show();

        notificationManager = NotificationManagerCompat.from(getActivity());

        ls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listar.setText(parent.getItemAtPosition(position).toString());
                if (plIme.getText().toString().length() > 0) {
                    bt.setVisibility(View.VISIBLE);

                } else {
                    bt.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
                posalji(view);
                Intent home = new Intent(getActivity(), HomeScreenActivity.class);
                startActivity(home);
                getActivity().finish();
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}