package com.example.napkinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class disp extends AppCompatActivity {

    TextView detailsText,statusText;
    DatabaseReference reff;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp);

        reff = FirebaseDatabase.getInstance().getReference().child("Users");
        detailsText = findViewById(R.id.detailsText);
        statusText = findViewById(R.id.statusText);

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userMail us = snapshot.getValue(userMail.class);
                    if(user.getEmail().equals(us.getMail()))
                    {
                        detailsText.setText(us.getOrder());
                        statusText.setText(us.getStatus());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent n = new Intent(disp.this,getDetails.class);
        startActivity(n);
    }

}
