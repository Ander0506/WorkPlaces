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


public class MainActivity extends AppCompatActivity {

    private EditText TxtUser, TxtPassword;
    private FirebaseAuth Auth;
    private Resources Res;

    /* en el menu herramientas (firebase) escoger la opcion authentication
     y conectar con un proyecto existente o crear uno nuevo y seguir los pasos
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtUser = (EditText) findViewById(R.id.TxtUser);
        TxtPassword = (EditText) findViewById(R.id.TxtPassword);
        Res = this.getResources();
        Auth = FirebaseAuth.getInstance();

    }

    public void Login(View view){
//        capturamos los valores de los campos en variables string para poder validar la informacion en firebase
        String email = TxtUser.getText().toString();
        String pass = TxtPassword.getText().toString();

//        Verificación que los campos no se encuentren vacios
        if(email.isEmpty() || pass.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.err_empty),Toast.LENGTH_LONG);
            toast.show();
            TxtUser.setError("*");
            TxtPassword.setError("*");
        }else{

//            si los campos no se encuentran vacios ejecuta el codigo extraido de firebase

            Auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

//                                Si la autenticacion es valida procedemos a iniciar la siguiente pagina

                                Intent intent = new Intent(MainActivity.this, Home.class);
                                startActivity(intent);
                            } else {

//                                Si no mostramos un mensaje de error

                                Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.err_user),Toast.LENGTH_LONG);
                                toast.show();
                                TxtUser.setError("*");
                                TxtPassword.setError("*");
                            }

                        }
                    });
        }
    }
}
