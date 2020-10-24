package com.example.redzmaja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ManastirListAdapter extends ArrayAdapter<Manastir> {

    private Context cont;
    int res;

    public ManastirListAdapter(@NonNull Context cont, int resource, @NonNull ArrayList<Manastir> objects) {
        super(cont, resource, objects);
        this.cont = cont;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String slika = getItem(position).getSlika();
        String naziv = getItem(position).getNaziv();
        String godina = getItem(position).getGodina();

        Manastir novi = new Manastir(slika, naziv, godina);

        LayoutInflater inflater = LayoutInflater.from(cont);
        convertView = inflater.inflate(res, parent, false);

        TextView nazT = (TextView) convertView.findViewById(R.id.nazivT);
        TextView godT = (TextView) convertView.findViewById(R.id.godinaT);
        ImageView img = (ImageView) convertView.findViewById(R.id.slikaM);

        nazT.setText(naziv);
        godT.setText(godina);
        Picasso.get().load(slika).into(img);


        return convertView;
    }
}
