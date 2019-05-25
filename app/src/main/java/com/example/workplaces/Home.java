package com.example.workplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    private Intent In;
    private int TypePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
