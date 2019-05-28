package com.example.workplaces;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText TxtUser, TxtPassword;
    private Resources Res;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TxtUser = (EditText) findViewById(R.id.TextNewEmail);
        TxtPassword = (EditText) findViewById(R.id.TextNewPassword);
        Auth = FirebaseAuth.getInstance();
        Res = this.getResources();

    }

    public void NewUser (View view){
        String email = TxtUser.getText().toString();
        String pass = TxtPassword.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast toast = Toast.makeText(Register.this, Res.getString(R.string.err_empty),Toast.LENGTH_LONG);
            TxtUser.setError("*");
            TxtPassword.setError("*");
        }else{
            Auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(Register.this, Res.getString(R.string.err_register),Toast.LENGTH_LONG);
                                TxtUser.setError("*");
                                TxtPassword.setError("*");
                            }

                            // ...
                        }
                    });

        }
    }
}
