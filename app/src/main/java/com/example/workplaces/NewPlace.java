package com.example.workplaces;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewPlace extends AppCompatActivity {


    private TextView Id;
    private Spinner TipoPlace;
    private EditText Name;
    private EditText Bloque;
    private EditText Piso;
    private int index = 0;
    private int posTipo = 0;
    private Resources res;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference RefVar = database.getReference("globalVar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        RefVar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               DataSnapshot s = dataSnapshot;
                String it= s.child("indexPlaces").getValue().toString();
                    index = Integer.parseInt(it);
                    Id.setText((index+1)+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Id = (TextView) findViewById(R.id.np_txt_id);
        TipoPlace = (Spinner) findViewById(R.id.np_sp_tipoPlace);
        Name = (EditText) findViewById(R.id.np_txt_name);
        Bloque = (EditText) findViewById(R.id.np_txt_bloque);
        Piso = (EditText) findViewById(R.id.np_txt_piso);

       // Id.setText((index+1)+"");

        res = getResources();

        ArrayAdapter Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.TiposDeLugar));
        TipoPlace.setAdapter(Adapter);

        TipoPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    posTipo = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void save(View view){
        String id,name,bloque,piso;
        int tipoplace;

        if(Name.getText().toString().isEmpty()){
            Name.setError(res.getString(R.string.nombre_vacio));
        }else if(Bloque.getText().toString().isEmpty()){
            Bloque.setError(res.getString(R.string.bloque_vacio));
        }else if (Piso.getText().toString().isEmpty()){
            Piso.setError(res.getString(R.string.piso_vacio));
        }else if(Integer.parseInt(Piso.getText().toString())<=0){
            Piso.setError(res.getString(R.string.pisoNeg));
        }else{

            id= Id.getText().toString();
            tipoplace= posTipo;
            name = Name.getText().toString();
            bloque = Bloque.getText().toString();
            piso = Piso.getText().toString();

            Place p = new Place(id,name,bloque,piso,true,tipoplace);

            p.añadir();
            index++;
            RefVar.child("indexPlaces").setValue(index);
            this.finish();
        }



    }

}
