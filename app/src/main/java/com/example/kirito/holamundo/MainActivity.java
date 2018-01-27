package com.example.kirito.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myDatabaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRootChild1 = myDatabaseReference.child("valor1");
    DatabaseReference myRootChild2 = myDatabaseReference.child("valor2");

    private EditText valor1, valor2;
    private TextView tv3, mfbTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = (EditText)findViewById(R.id.valor1);
        valor2 = (EditText)findViewById(R.id.valor2);
        tv3 = (TextView)findViewById(R.id.tv3);
        mfbTextView = (TextView) findViewById(R.id.fbTextView);
    }

    public void sumar(View view) {
        String cad1 = valor1.getText().toString();
        String cad2 = valor2.getText().toString();
        int nro1 = Integer.parseInt(cad1);
        int nro2 = Integer.parseInt(cad2);
        int suma = nro1 + nro2;
        String resu = String.valueOf(suma);
        tv3.setText(resu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRootChild1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String cad1 = dataSnapshot.getValue().toString();
                valor1.setText(cad1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRootChild2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String cad2 = dataSnapshot.getValue().toString();
                valor2.setText(cad2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
