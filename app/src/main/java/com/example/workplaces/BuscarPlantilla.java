package com.example.workplaces;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuscarPlantilla extends AppCompatActivity {

    private Intent In;
    private  ArrayList<Place> places = new ArrayList<>();
    public  ArrayList<Place> PlaceToShow;
    private ArrayList<String> bloques;
    private ArrayList<String> pisos;

    private Place placeActual;
    ArrayList<String> titles = new ArrayList<>();

    private ArrayAdapter<String> StagesAdapter;
    private ArrayAdapter<String> BuildingsAdapter;

    private TextView title;
    private RecyclerView Rv;
    private EditText busqueda;
    private Spinner buildings;
    private Spinner stages;
    private int typePlace;
    private Resources res;

    private int posBloque;
    private int posPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        res = getResources();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference RefPlaces = database.getReference("places");


        RefPlaces.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Np funciona la forma de leer los datos
                places.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    String Id = ds.child("id").getValue().toString();
                    String Nombre = ds.child("nombre").getValue().toString();
                    String Bloque = ds.child("bloque").getValue().toString();
                    String Piso = ds.child("piso").getValue().toString();
                    Boolean Disponible = Boolean.valueOf(ds.child("disponible").getValue().toString());
                    int Type = Integer.parseInt(ds.child("type").getValue().toString());

                    places.add(new Place(Id,Nombre,Bloque,Piso,Disponible,Type));

                }
                LLenadoPlaceToshow();
                loadplaces(PlaceToShow);
            //    Toast.makeText(BuscarPlantilla.this, "LLegoData", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        setContentView(R.layout.buscar_plantilla);

        In = getIntent();

        typePlace = In.getIntExtra("TypePlace",5);

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
        bloques.add(getResources().getString(R.string.bloque));
        pisos.add(getResources().getString(R.string.piso));

        placeActual = Data.getPlaceActual();
        //llenado del placetoshow y de los spinners
        LLenadoPlaceToshow();


        //Evento para la RECYCLEVIEW
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
                    loadplaces(BuscarPorBloque(posBloque));

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
                if (count==0){
                    loadplaces(PlaceToShow);
                }else {
                    loadplaces(BuscarString(s.toString()));
                }
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

        if (posBloque == 0 && posBloque == 0){

            for (int i = 0; i<PlaceToShow.size();i++ ) {
                Place place = PlaceToShow.get(i);
                if (place.getNombre().toLowerCase().contains(st.toLowerCase())) {
                    PlaceToSearchAux.add(place);
                }
            }

        }else if (posPiso == 0) {


            for (int i = 0; i<PlaceToShow.size();i++ ) {
                Place place = PlaceToShow.get(i);
                if (place.getNombre().toLowerCase().contains(st.toLowerCase())) {

                    if (place.getNombre().toLowerCase().contains(st.toLowerCase())&&
                            place.getBloque().toLowerCase().equals(bloques.get(posBloque))) {
                        PlaceToSearchAux.add(place);
                    }

                }
            }

        }else{
            for (int i = 0; i<PlaceToShow.size();i++ ) {
                Place place = PlaceToShow.get(i);
                if (place.getNombre().toLowerCase().contains(st.toLowerCase())) {

                    if (place.getNombre().toLowerCase().contains(st.toLowerCase())&&
                            place.getBloque().toLowerCase().equals(bloques.get(posBloque))&&
                            place.getPiso().toLowerCase().equals(pisos.get(posPiso))) {
                        PlaceToSearchAux.add(place);
                    }

                }
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
                //nothingHere
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
            if (place.getBloque().equals(Building) && place.getPiso().equals(Stage)){
                PlaceToSearchAux.add(place);

            }else{
                //nothing here
            }
        }
        return PlaceToSearchAux;
    }

    public void loadplaces(final ArrayList<Place> places){

        PlaceAdapter adapter = new PlaceAdapter(BuscarPlantilla.this,places);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Place selected = places.get(Rv.getChildAdapterPosition(v));

                if (selected.getDisponible()){

                    if (Data.getPlaceActual()==null){

                        AlertDialog.Builder usar = new AlertDialog.Builder(BuscarPlantilla.this);
                        usar.setMessage(R.string.Utilisar)
                                .setCancelable(false)
                                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        selected.changeAvailable();
                                        Data.setPlaceActual(selected);



                                    }
                                })
                                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog titulo = usar.create();
                        titulo.setTitle(getResources().getString(R.string.usar));
                        titulo.show();

                    }else{
                            placeActual = Data.getPlaceActual();

                        AlertDialog.Builder usar = new AlertDialog.Builder(BuscarPlantilla.this);
                        usar.setMessage(res.getString(R.string.yaUsandoEsta)+"\n"
                                +"    "+Data.getPlaceActual().getNombre()+" "+res.getString(R.string.en)
                                +" "+res.getString(R.string.bloque)+" "+Data.getPlaceActual().getBloque()+"\n"
                                +res.getString(R.string.deseaUsar))
                                .setCancelable(false)
                                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Data.DesocuparPlaceActual();
                                        selected.changeAvailable();
                                        Data.setPlaceActual(selected);

                                    }
                                })
                                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog titulo = usar.create();
                        titulo.setTitle(getResources().getString(R.string.usar));
                        titulo.show();



                    }
                }else{
                    if (Data.esActual(selected)) {
                        AlertDialog.Builder usar = new AlertDialog.Builder(BuscarPlantilla.this);
                        usar.setMessage(R.string.yaUsandoEsta)
                                .setCancelable(true)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog titulo = usar.create();
                        titulo.setTitle(getResources().getString(R.string.usar));
                        titulo.show();

                    }else{
                            AlertDialog.Builder usar = new AlertDialog.Builder(BuscarPlantilla.this);
                            usar.setMessage(R.string.ya_ocupado)
                                    .setCancelable(true)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();

                                        }
                                    });

                            AlertDialog titulo = usar.create();
                            titulo.setTitle(getResources().getString(R.string.ocupado));
                            titulo.show();
                        }


            }






            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(BuscarPlantilla.this);
        Rv.setLayoutManager(llm);
        Rv.setAdapter(adapter);
    }

    public void updatevista(){

    }

    public void LLenadoPlaceToshow(){
        PlaceToShow.clear();
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

    }


}
