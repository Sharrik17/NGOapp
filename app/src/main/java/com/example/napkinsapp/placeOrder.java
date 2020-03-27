package com.example.napkinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class placeOrder extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;
    Integer tc1=0,tc2=0,tc3=0,tc4=0,tc5=0;
    Button m1,m2,m3,m4,m5,p1,p2,p3,p4,p5,order;
    CheckBox c1,c2,c3,c4,c5;
//    Integer ret;
//    ArrayList<Integer> stocks = new ArrayList<>();
    DatabaseReference ref,reff;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String str="",phnum="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);

        m1 = findViewById(R.id.m1);
        p1 =findViewById(R.id.p1);

        m2 = findViewById(R.id.m2);
        p2 =findViewById(R.id.p2);

        m3 = findViewById(R.id.m3);
        p3 =findViewById(R.id.p3);

        m4 = findViewById(R.id.m4);
        p4 =findViewById(R.id.p4);

        m5 = findViewById(R.id.m5);
        p5 =findViewById(R.id.p5);

        order=findViewById(R.id.order);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);

//        ref = FirebaseDatabase.getInstance().getReference().child("Stocks");
        reff= FirebaseDatabase.getInstance().getReference().child("Users");

        m1.setEnabled(false);
        m2.setEnabled(false);
        m3.setEnabled(false);
        m4.setEnabled(false);
        m5.setEnabled(false);

        p1.setEnabled(false);
        p2.setEnabled(false);
        p3.setEnabled(false);
        p4.setEnabled(false);
        p5.setEnabled(false);

        t1.setText(String.valueOf(tc1));
        t2.setText(String.valueOf(tc2));
        t3.setText(String.valueOf(tc3));
        t4.setText(String.valueOf(tc4));
        t5.setText(String.valueOf(tc5));


//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    ret = snapshot.getValue(Integer.class);
//                    stocks.add(ret);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    userMail us = snapshot.getValue(userMail.class);
                    if(user.getEmail().equals(us.getMail()))
                    {
                        phnum = us.getPhone();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c1.isChecked())
                    p1.setEnabled(true);
                else
                    p1.setEnabled(false);
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c2.isChecked())
                    p2.setEnabled(true);
                else
                    p2.setEnabled(false);
            }
        });

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c3.isChecked())
                    p3.setEnabled(true);
                else
                    p3.setEnabled(false);
            }
        });

        c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c4.isChecked())
                    p4.setEnabled(true);
                else
                    p4.setEnabled(false);
            }
        });

        c5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c5.isChecked())
                    p5.setEnabled(true);
                else
                    p5.setEnabled(false);
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setEnabled(true);
                tc1--;
                if(tc1==0)
                {
                    m1.setEnabled(false);
                }
                t1.setText(String.valueOf(tc1));
            }
        });

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m1.setEnabled(true);
                tc1++;
                if(tc1 == 3)
                {
                    p1.setEnabled(false);
                }
                t1.setText(String.valueOf(tc1));
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.setEnabled(true);
                tc2--;
                if(tc2==0)
                {
                    m2.setEnabled(false);
                }
                t2.setText(String.valueOf(tc2));
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m2.setEnabled(true);
                tc2++;
                if(tc2 == 3)
                {
                    p2.setEnabled(false);
                }
                t2.setText(String.valueOf(tc2));
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.setEnabled(true);
                tc3--;
                if(tc3==0)
                {
                    m3.setEnabled(false);
                }
                t3.setText(String.valueOf(tc3));
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m3.setEnabled(true);
                tc3++;
                if(tc3 == 3)
                {
                    p3.setEnabled(false);
                }
                t3.setText(String.valueOf(tc3));
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p4.setEnabled(true);
                tc4--;
                if(tc4==0)
                {
                    m4.setEnabled(false);
                }
                t4.setText(String.valueOf(tc4));
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m4.setEnabled(true);
                tc4++;
                if(tc4 == 3)
                {
                    p4.setEnabled(false);
                }
                t4.setText(String.valueOf(tc4));
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p5.setEnabled(true);
                tc5--;
                if(tc5==0)
                {
                    m5.setEnabled(false);
                }
                t5.setText(String.valueOf(tc5));
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m5.setEnabled(true);
                tc5++;
                if(tc5 == 3)
                {
                    p5.setEnabled(false);
                }
                t5.setText(String.valueOf(tc5));
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked()) &&
                        (Integer.parseInt(t1.getText().toString())>0 ||
                        Integer.parseInt(t2.getText().toString())>0 ||
                        Integer.parseInt(t3.getText().toString())>0 ||
                        Integer.parseInt(t4.getText().toString())>0 ||
                        Integer.parseInt(t5.getText().toString())>0))
                    display();
                else
                    Toast.makeText(placeOrder.this, "INVALID ORDER!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void display(){

        if(c1.isChecked() && Integer.parseInt(t1.getText().toString())>0)
        {
            str+="Type 1 : ";
            str+=String.valueOf(tc1);
            str+="\n";
        }
        if(c2.isChecked() && Integer.parseInt(t2.getText().toString())>0)
        {
            str+="Type 2 : ";
            str+=String.valueOf(tc2);
            str+="\n";
        }
        if(c3.isChecked() && Integer.parseInt(t3.getText().toString())>0)
        {
            str+="Type 3 : ";
            str+=String.valueOf(tc3);
            str+="\n";
        }
        if(c4.isChecked() && Integer.parseInt(t4.getText().toString())>0)
        {
            str+="Type 4 : ";
            str+=String.valueOf(tc4);
            str+="\n";
        }
        if(c5.isChecked() && Integer.parseInt(t5.getText().toString())>0)
        {
            str+="Type 5 : ";
            str+=String.valueOf(tc5);
            str+="\n";
        }

        reff.child(phnum).child("order").setValue(str);
        reff.child(phnum).child("status").setValue("Pending");
        Intent i=new Intent(placeOrder.this,disp.class);
        i.putExtra("details",str);
        str="";
        startActivity(i);

    }
}