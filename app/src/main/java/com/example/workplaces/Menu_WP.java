package com.example.workplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu_WP extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent In;
    private int TypePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        setContentView(R.layout.activity_menu__wp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu__w, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

//    Procedimientos de items de menu


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pc_room) {
            TypePlace = 0;
            In= new Intent(Menu_WP.this,BuscarPlantilla.class);
            In.putExtra("TypePlace",TypePlace);
            startActivity(In);

        } else if (id == R.id.nav_table_work) {
            TypePlace = 1;
            In= new Intent(Menu_WP.this,BuscarPlantilla.class);
            In.putExtra("TypePlace",TypePlace);
            startActivity(In);

        } else if (id == R.id.nav_custom_room) {
            TypePlace = 2;
            In= new Intent(Menu_WP.this,BuscarPlantilla.class);
            In.putExtra("TypePlace",TypePlace);
            startActivity(In);

        } else if (id == R.id.nav_parking) {
            TypePlace = 3;
            In= new Intent(Menu_WP.this,BuscarPlantilla.class);
            In.putExtra("TypePlace",TypePlace);
            startActivity(In);

        } else if(id == R.id.nav_room){
            TypePlace = 4;
            In= new Intent(Menu_WP.this,BuscarPlantilla.class);
            In.putExtra("TypePlace",TypePlace);
            startActivity(In);
        } else if (id == R.id.nav_add_place){
            In= new Intent(Menu_WP.this,NewPlace.class);
            startActivity(In);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
