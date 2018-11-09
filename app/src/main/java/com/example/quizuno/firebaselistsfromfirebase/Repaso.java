package com.example.quizuno.firebaselistsfromfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Repaso extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaso);

        auth = FirebaseAuth.getInstance();
        //auth.signOut();
        db = FirebaseDatabase.getInstance();


        auth.createUserWithEmailAndPassword("mynaemisjeff@gmail.com", "12345678niggajeff").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    String uid = auth.getCurrentUser().getUid();

                    Usuario usuario = new Usuario();
                    usuario.uid = uid;
                    usuario.nombre = "jeef";
                    usuario.correo = "mynaemisjeff@gmail.com";
                    usuario.contra = "12345678niggajeff";

                    db.getReference().child("usuarios").child(uid).setValue(usuario);

                }else{

                    Toast.makeText(Repaso.this, ""+task.getException(), Toast.LENGTH_LONG).show();

                    }

            }
        });

        //auth.signInWithEmailAndPassword();
    }
}
