package com.example.workplaces;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    private Intent In;
    private int TypePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference("places");

        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Np funciona la forma de leer los datos
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    String Id = ds.child("id").getValue().toString();
                    String Nombre = ds.child("nombre").getValue().toString();
                    String Bloque = ds.child("bloque").getValue().toString();
                    String Piso = ds.child("piso").getValue().toString();
                    Boolean Disponible = Boolean.valueOf(ds.child("disponible").getValue().toString());
                    int Type = Integer.parseInt(ds.child("type").getValue().toString());

                    Data.Save(new Place(Id,Nombre,Bloque,Piso,Disponible,Type));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void listPcRooms(View view){
        TypePlace = 0;
        In= new Intent(Home.this,BuscarPlantilla.class);
        In.putExtra("TypePlace",TypePlace);
        startActivity(In);
    }

    public void listWorkTables(View view){
        TypePlace = 1;
        In= new Intent(Home.this,BuscarPlantilla.class);
        In.putExtra("TypePlace",TypePlace);
        startActivity(In);
    }

    public void listPersonalizedRooms(View view){
        TypePlace = 2;
        In= new Intent(Home.this,BuscarPlantilla.class);
        In.putExtra("TypePlace",TypePlace);
        startActivity(In);
    }

    public void listParking(View view){
        TypePlace = 3;
        In= new Intent(Home.this,BuscarPlantilla.class);
        In.putExtra("TypePlace",TypePlace);
        startActivity(In);
    }

    public void listClassRooms(View view){
        TypePlace = 4;
        In= new Intent(Home.this,BuscarPlantilla.class);
        In.putExtra("TypePlace",TypePlace);
        startActivity(In);
    }
}
