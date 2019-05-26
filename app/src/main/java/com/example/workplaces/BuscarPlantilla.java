package com.example.workplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuscarPlantilla extends AppCompatActivity {

    private Intent In;
    private ArrayList<Place> places = new ArrayList<>();
    private ArrayList<Place> PlaceToShow;
    private ArrayList<String> bloques;
    private ArrayList<String> pisos;

    ArrayList<String> titles = new ArrayList<>();

    private ArrayAdapter<String> StagesAdapter;
    private ArrayAdapter<String> BuildingsAdapter;

    private TextView title;
    private RecyclerView Rv;
    private EditText busqueda;
    private Spinner buildings;
    private Spinner stages;

    private int posBloque;
    private int posPiso;






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
        buildings = (Spinner) findViewById(R.id.bp_sp_buildings);
        stages = (Spinner) findViewById(R.id.bp_sp_stages);


        String[] titles = getResources().getStringArray(R.array.TiposDeLugar);

        title.setText(titles[typePlace]);
        //PREPARACION DE LA LISTVIEW CON TODOS LOS COMPONENTES PERTINENTES
        PlaceToShow = new ArrayList<>();
        //utilizo para llenar los spinner para ahorrar tiempo
        bloques = new ArrayList<>();
        pisos = new ArrayList<>();
        bloques.add(getResources().getString(R.string.todo));
        pisos.add(getResources().getString(R.string.todo));

        for (int i =0; i <places.size();i++){
            Place actual = places.get(i);
            if (actual.getType() == typePlace){
                PlaceToShow.add(actual);
                if (!bloques.contains(actual.getBloque())){
                    bloques.add(actual.getBloque());
                }

            }else{

            }
        }
       /* PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,PlaceToShow);
        LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
        Rv.setLayoutManager(llm);
        Rv.setAdapter(adapter);*/
         loadplaces(PlaceToShow);

        //PREPARACION DE SPINNER DE LOS BLOQUES
        BuildingsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,bloques);
        buildings.setAdapter(BuildingsAdapter);

        //PREPARACION DE SPINNER DE LOS PISOS
        StagesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,pisos);

        buildings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    loadplaces(BuscarPorBloque(position));
                    stages.setAdapter(StagesAdapter);
                    posBloque = position;
                }else{
                    loadplaces(PlaceToShow);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    posPiso = position;
                    loadplaces(BuscarPorBloqueyPiso(posPiso));

                }else{
                    //nothing
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //EVENTO ONTYPING DEL EDITTEXT BUSQUEDA
        busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadplaces(BuscarString(s.toString()));
              /*  PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,BuscarString(s.toString()));
                LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
                Rv.setLayoutManager(llm);
                Rv.setAdapter(adapter);*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    //MEtodo devuelve Array con lo que se escribe en ST
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

    public ArrayList<Place> BuscarPorBloque(int pos){
        ArrayList PlaceToSearchAux = new ArrayList();
        String Building = bloques.get(pos);
        for (int i = 0; i<PlaceToShow.size();i++ ){
            Place place = PlaceToShow.get(i);
            if (place.getBloque().equals(Building)){
                PlaceToSearchAux.add(place);
                if (!pisos.contains(place.getPiso())){
                    pisos.add(place.getPiso());
                }
            }else{

            }

        }
        return PlaceToSearchAux;
    }

    public ArrayList<Place> BuscarPorBloqueyPiso(int pos){
        ArrayList PlaceToSearchAux = new ArrayList();

        String Building = bloques.get(posBloque);
        String Stage = pisos.get(posPiso);
        for (int i = 0; i<PlaceToShow.size();i++ ){
            Place place = PlaceToShow.get(i);
            if (place.getBloque().equals(Building) && place.getPiso().equals(posPiso)){
                PlaceToSearchAux.add(place);

            }else{

            }
        }
        return PlaceToSearchAux;
    }




    public void loadplaces(ArrayList<Place> places){
        PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,places);
        LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
        Rv.setLayoutManager(llm);
        Rv.setAdapter(adapter);
    }

}
