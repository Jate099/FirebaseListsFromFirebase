package com.example.quizuno.firebaselistsfromfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Conteo extends AppCompatActivity {

    Button firmar1;
    Button firmar2;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteo);

        firmar1 = findViewById(R.id.firmar1);
        firmar2 = findViewById(R.id.firmar2);

        db = FirebaseDatabase.getInstance();

        firmar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("firmas").child("peticion1").push().setValue("F");

            }
        });

        firmar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("firmas").child("peticion2").push().setValue("F");

            }
        });

        db.getReference().child("firmas").child("peticion1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firmas = "FIRMAR ("+dataSnapshot.getChildrenCount()+")";
                firmar1.setText(firmas);

                for(DataSnapshot esnacha : dataSnapshot.getChildren()){
                    Log.e("Clave", ""+esnacha.getKey());
                    Log.e("valor", ""+esnacha.getValue());

                    String valor = esnacha.getValue(String.class);
                    Log.e("Valor trabsformado", ""+valor);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.getReference().child("firmas").child("peticion2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firmas = "FIRMAR ("+dataSnapshot.getChildrenCount()+")";
                firmar2.setText(firmas);

                for(DataSnapshot esnacha : dataSnapshot.getChildren()){
                    Log.e("Clave", ""+esnacha.getKey());
                    Log.e("valor", ""+esnacha.getValue());

                    String valor = esnacha.getValue(String.class);
                    Log.e("Valor trabsformado", ""+valor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}
