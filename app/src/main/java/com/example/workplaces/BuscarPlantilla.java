package com.example.workplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscarPlantilla extends AppCompatActivity {

    private Intent In;
    private ArrayList<Place> places;
    ArrayList<Place> PlaceToShow;

    ArrayList<String> titles = new ArrayList<>();

    private TextView title;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_plantilla);

        In = getIntent();
        places = Data.Get();

        int typePlace = In.getIntExtra("TypePlace",5);

        title = (TextView) findViewById(R.id.bp_txt_Title);
        String[] titles = getResources().getStringArray(R.array.TiposDeLugar);

        title.setText(titles[typePlace]);
        loadContact(places,typePlace);


    }

    public void loadContact(ArrayList<Place> places, int type){
        PlaceToShow = new ArrayList<>();
        for (int i = 0; i <places.size();i++){
            Place actual = places.get(i);
            if (actual.getType() == type){
                PlaceToShow.add(actual);
            }
        }

    }
}
