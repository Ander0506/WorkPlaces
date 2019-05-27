package com.example.workplaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference RefVar= database.getReference("globalVar");

        RefVar.addListenerForSingleValueEvent(new add);
    }
}
