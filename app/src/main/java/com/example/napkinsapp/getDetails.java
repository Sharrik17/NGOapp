package com.example.napkinsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class getDetails extends AppCompatActivity {

    EditText name,phone,address;
    Button next,viewOrders;
    TextView disp;
    Integer flag,x;
    DatabaseReference reff;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    userMail userMail;
    String ph;

    Spinner spinner;
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        name = findViewById(R.id.nameText);
        phone = findViewById(R.id.phoneText);
        phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        address=findViewById(R.id.addText);
        next = findViewById(R.id.btnNext);
        next.setEnabled(false);
        viewOrders = findViewById(R.id.viewOrders);
        viewOrders.setEnabled(false);
        disp = findViewById(R.id.dispText);

        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.fcount, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        reff = FirebaseDatabase.getInstance().getReference().child("Users");
        userMail = new userMail();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            flag=0;
                            userMail us = snapshot.getValue(userMail.class);
                            if(user.getEmail().equals(us.getMail()))
                            {
                                flag=1;
                                if(us.getStatus().equals("Delivered") || us.getStatus()==null || us.getStatus().equals("Declined") || us.getStatus().equals("None"))
                                    next.setEnabled(true);
                                if(us.getStatus().equals("Pending") || us.getStatus().equals("Accepted") || us.getStatus().equals("Declined"))
                                    viewOrders.setEnabled(true);
                                disp.setText("Your Details");
                                name.setText(us.getName());
                                name.setEnabled(false);
                                phone.setText(us.getPhone());
                                phone.setEnabled(false);
                                spinner.setSelection(Integer.parseInt(us.getFc())-1);
                                spinner.setEnabled(false);
                                address.setText(us.getAddress());
                                address.setEnabled(false);
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            disp.setText("Enter Your Details");
                            next.setEnabled(true);
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    x=0;
                                    String nm = name.getText().toString().trim();
                                    ph = phone.getText().toString().trim();
                                    if(ph.length()!=10 && !ph.isEmpty()) {
                                        phone.setText("");
                                        x=1;
                                        Toast.makeText(getDetails.this, "Invalid phone number!", Toast.LENGTH_LONG).show();
                                    }
                                    String fcount = spinner.getSelectedItem().toString().trim();
                                    String email=user.getEmail().trim();
                                    String addr = address.getText().toString().trim();

                                    if(nm.isEmpty() || ph.isEmpty() || fcount.isEmpty() || addr.isEmpty()) {
                                        x=1;
                                        Toast.makeText(getDetails.this, "Please fill all details!", Toast.LENGTH_LONG).show();
                                    }

                                    if(x==0)
                                    {
                                        userMail.setName(nm);
                                        userMail.setPhone(ph);
                                        userMail.setFc(fcount);
                                        userMail.setMail(email);
                                        userMail.setAddress(addr);
                                        userMail.setStatus("None");

                                        reff.child(ph).setValue(userMail);
                                        Toast.makeText(getDetails.this, "Details Recorded!", Toast.LENGTH_SHORT).show();
                                        nextAct();
                                    }
                                }
                            });
                        }
                        else
                        {
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view)
                                {
                                    nextAct();
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getDetails.this,disp.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "BACK is not allowed", Toast.LENGTH_SHORT).show();
    }

    public void nextAct()
    {
        Intent x = new Intent(getDetails.this,placeOrder.class);
        startActivity(x);
    }
}
