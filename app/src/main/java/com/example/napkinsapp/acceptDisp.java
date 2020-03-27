package com.example.napkinsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class acceptDisp extends AppCompatActivity {

    Intent h;
    String pho,dets;
    TextView dt;
    Button del;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_disp);

        h = getIntent();
        pho = h.getStringExtra("phone");
        dets = h.getStringExtra("details");
        ref = FirebaseDatabase.getInstance().getReference().child("Users");

        dt = findViewById(R.id.datext);
        dt.setText(dets);

        del = findViewById(R.id.delivered);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child(pho).child("status").setValue("Delivered");
                Intent ik = new Intent(acceptDisp.this,onlyAccept.class);
                startActivity(ik);
            }
        });
    }
}
