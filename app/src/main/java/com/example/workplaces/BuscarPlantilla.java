package com.example.workplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuscarPlantilla extends AppCompatActivity {

    private Intent In;
    private ArrayList<Place> places = new ArrayList<>();
    private ArrayList<Place> PlaceToShow;

    ArrayList<String> titles = new ArrayList<>();

    private TextView title;
    private RecyclerView Rv;
    private EditText busqueda;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_plantilla);

        In = getIntent();
        places = Data.Get();

        int typePlace = In.getIntExtra("TypePlace",5);

        title = (TextView) findViewById(R.id.bp_txt_Title);
        Rv = (RecyclerView) findViewById(R.id.bp_Rv_Datos);
        busqueda = (EditText) findViewById(R.id.bp_txt_Busqueda);




        String[] titles = getResources().getStringArray(R.array.TiposDeLugar);

        title.setText(titles[typePlace]);

        PlaceToShow = new ArrayList<>();


        for (int i =0; i <places.size();i++){
            Place actual = places.get(i);
            if (actual.getType() == typePlace){
                PlaceToShow.add(actual);
            }else{

            }
        }

      //  Toast.makeText(this,"llego2", Toast.LENGTH_LONG).show();

        //Ingreso los datos en la recycleView
        PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,PlaceToShow);
        LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
        Rv.setLayoutManager(llm);
        Rv.setAdapter(adapter);

       busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,BuscarString(s.toString()));
                LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
                Rv.setLayoutManager(llm);
                Rv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




     //   Toast.makeText(this,"llego4", Toast.LENGTH_LONG).show();

    }

    public ArrayList<Place> BuscarString(String st){
        ArrayList PlaceToSearchAux = new ArrayList();
        for (int i = 0; i<PlaceToShow.size();i++ ){
            Place place = PlaceToShow.get(i);
            if (place.getNombre().toLowerCase().contains(st.toLowerCase())){
                PlaceToSearchAux.add(place);
            }else{

            }

        }
        return PlaceToSearchAux;
    }

}
