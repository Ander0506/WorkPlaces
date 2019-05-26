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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText TxtUser, TxtPassword;
    private FirebaseAuth Auth;
    private Resources Res;
    private ArrayList<Place> places = new ArrayList<>();

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
                                /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference Ref = database.getReference("places");
                                Ref.child("2").setValue(new Place("2","Sala 1","3","3",true,0));
                                Ref.child("3").setValue(new Place("3","Sala 2","1","3",true,0));
                                Ref.child("4").setValue(new Place("4","Sala 3","10","3",false,0));
                                Ref.child("5").setValue(new Place("5","Sala 4","8","3",false,0));

                                Ref.child("6").setValue(new Place("6","Mesita 1","11","3",true,1));
                                Ref.child("7").setValue(new Place("7","Mesita 2","11","5",false,1));
                                Ref.child("8").setValue(new Place("8","Mesita 3","11","5",true,1));
                                Ref.child("9").setValue(new Place("9","Mesita 4","11","6",true,1));
                                Ref.child("10").setValue(new Place("10","Mesita 5","11","6",false,1));

                                Ref.child("11").setValue(new Place("11","Sala Pers 1","11","3",false,2));
                                Ref.child("12").setValue(new Place("12","Sala Pers 2","11","3",true,2));
                                Ref.child("13").setValue(new Place("13","Sala Pers 3","11","5",true,2));
                                Ref.child("14").setValue(new Place("14","Sala Pers 4","11","6",false,2));*/

                                Intent intent = new Intent(MainActivity.this, Menu_WP.class);

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
