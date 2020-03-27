package com.example.napkinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class acceptOrder extends AppCompatActivity {

    Intent g;
    TextView nmt,pht,fct,odt,adt;
    Button accept,decline,goback;
    DatabaseReference reff;
    userMail um;
    String display,nm,phone,fc,od,ad;
    String stat="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_order);

        g = getIntent();
        display = g.getStringExtra("details");
        nm = g.getStringExtra("name");
        phone = g.getStringExtra("ph");
        fc = g.getStringExtra("fc");
        od = g.getStringExtra("od");
        ad = g.getStringExtra("address");

        nmt = findViewById(R.id.nmtext);
        nmt.setText(nm);
        pht = findViewById(R.id.phtext);
        pht.setText(phone);
        fct = findViewById(R.id.fctext);
        fct.setText(fc);
        adt = findViewById(R.id.adtext);
        adt.setText(ad);
        odt = findViewById(R.id.ordtext);
        odt.setText(od);

        accept = findViewById(R.id.accept);
        decline = findViewById(R.id.decline);
        goback = findViewById(R.id.goback);

        goback.setVisibility(View.GONE);

        um = new userMail();

        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stat="Accepted";
                decline.setEnabled(false);
                accept.setEnabled(false);
                goback.setVisibility(View.VISIBLE);
                reff.child(pht.getText().toString()).child("status").setValue(stat);
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stat = "Declined";
                accept.setEnabled(false);
                decline.setEnabled(false);
                goback.setVisibility(View.VISIBLE);
                reff.child(pht.getText().toString()).child("status").setValue(stat);
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(acceptOrder.this,adminOrders.class);
                startActivity(ik);
            }
        });
    }
}
