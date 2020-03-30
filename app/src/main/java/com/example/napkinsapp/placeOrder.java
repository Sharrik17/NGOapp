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

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    Integer tc1=0,tc2=0,tc3=0,tc4=0,tc5=0,tc6=0,tc7=0,tc8=0,tc9=0,tc10=0;
    Button m1,m2,m3,m4,m5,p1,p2,p3,p4,p5,order,m6,m7,m8,m9,m10,p6,p7,p8,p9,p10,page1,page2;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    DatabaseReference reff;
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
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        t10 = findViewById(R.id.t10);

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

        m6 = findViewById(R.id.m6);
        p6 =findViewById(R.id.p6);

        m7 = findViewById(R.id.m7);
        p7 =findViewById(R.id.p7);

        m8 = findViewById(R.id.m8);
        p8 =findViewById(R.id.p8);

        m9 = findViewById(R.id.m9);
        p9 =findViewById(R.id.p9);

        m10 = findViewById(R.id.m10);
        p10 = findViewById(R.id.p10);

        order=findViewById(R.id.order);
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page1.setEnabled(false);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);

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

        m6.setEnabled(false);
        m7.setEnabled(false);
        m8.setEnabled(false);
        m9.setEnabled(false);
        m10.setEnabled(false);

        p6.setEnabled(false);
        p7.setEnabled(false);
        p8.setEnabled(false);
        p9.setEnabled(false);
        p10.setEnabled(false);

        t6.setText(String.valueOf(tc6));
        t7.setText(String.valueOf(tc7));
        t8.setText(String.valueOf(tc8));
        t9.setText(String.valueOf(tc9));
        t10.setText(String.valueOf(tc10));

        m6.setVisibility(View.GONE);
        m7.setVisibility(View.GONE);
        m8.setVisibility(View.GONE);
        m9.setVisibility(View.GONE);
        m10.setVisibility(View.GONE);

        p6.setVisibility(View.GONE);
        p7.setVisibility(View.GONE);
        p8.setVisibility(View.GONE);
        p9.setVisibility(View.GONE);
        p10.setVisibility(View.GONE);

        t6.setVisibility(View.GONE);
        t7.setVisibility(View.GONE);
        t8.setVisibility(View.GONE);
        t9.setVisibility(View.GONE);
        t10.setVisibility(View.GONE);

        c6.setVisibility(View.GONE);
        c7.setVisibility(View.GONE);
        c8.setVisibility(View.GONE);
        c9.setVisibility(View.GONE);
        c10.setVisibility(View.GONE);

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

        c6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c6.isChecked())
                    p6.setEnabled(true);
                else
                    p6.setEnabled(false);
            }
        });

        c7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c7.isChecked())
                    p7.setEnabled(true);
                else
                    p7.setEnabled(false);
            }
        });

        c8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c8.isChecked())
                    p8.setEnabled(true);
                else
                    p8.setEnabled(false);
            }
        });

        c9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c9.isChecked())
                    p9.setEnabled(true);
                else
                    p9.setEnabled(false);
            }
        });

        c10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(c10.isChecked())
                    p10.setEnabled(true);
                else
                    p10.setEnabled(false);
            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p6.setEnabled(true);
                tc6--;
                if(tc6==0)
                {
                    m6.setEnabled(false);
                }
                t6.setText(String.valueOf(tc6));
            }
        });

        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m6.setEnabled(true);
                tc6++;
                if(tc6 == 3)
                {
                    p6.setEnabled(false);
                }
                t6.setText(String.valueOf(tc6));
            }
        });

        m7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p7.setEnabled(true);
                tc7--;
                if(tc7==0)
                {
                    m7.setEnabled(false);
                }
                t7.setText(String.valueOf(tc7));
            }
        });

        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m7.setEnabled(true);
                tc7++;
                if(tc7 == 3)
                {
                    p7.setEnabled(false);
                }
                t7.setText(String.valueOf(tc7));
            }
        });

        m8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p8.setEnabled(true);
                tc8--;
                if(tc8==0)
                {
                    m8.setEnabled(false);
                }
                t8.setText(String.valueOf(tc8));
            }
        });

        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m8.setEnabled(true);
                tc8++;
                if(tc8 == 3)
                {
                    p8.setEnabled(false);
                }
                t8.setText(String.valueOf(tc8));
            }
        });

        m9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p9.setEnabled(true);
                tc9--;
                if(tc9==0)
                {
                    m9.setEnabled(false);
                }
                t9.setText(String.valueOf(tc9));
            }
        });

        p9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m9.setEnabled(true);
                tc9++;
                if(tc9 == 3)
                {
                    p9.setEnabled(false);
                }
                t9.setText(String.valueOf(tc9));
            }
        });

        m10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p10.setEnabled(true);
                tc10--;
                if(tc10==0)
                {
                    m10.setEnabled(false);
                }
                t10.setText(String.valueOf(tc10));
            }
        });

        p10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m10.setEnabled(true);
                tc10++;
                if(tc10 == 3)
                {
                    p10.setEnabled(false);
                }
                t10.setText(String.valueOf(tc10));
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked()
                    || c6.isChecked() || c7.isChecked() || c8.isChecked() || c9.isChecked() || c10.isChecked())  &&
                        (Integer.parseInt(t1.getText().toString())>0 ||
                        Integer.parseInt(t2.getText().toString())>0 ||
                        Integer.parseInt(t3.getText().toString())>0 ||
                        Integer.parseInt(t4.getText().toString())>0 ||
                        Integer.parseInt(t5.getText().toString())>0 ||
                        Integer.parseInt(t6.getText().toString())>0 ||
                        Integer.parseInt(t7.getText().toString())>0 ||
                        Integer.parseInt(t8.getText().toString())>0 ||
                        Integer.parseInt(t9.getText().toString())>0 ||
                        Integer.parseInt(t10.getText().toString())>0))
                    display();
                else
                    Toast.makeText(placeOrder.this, "INVALID ORDER!", Toast.LENGTH_LONG).show();
            }
        });

        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page1.setEnabled(false);
                page2.setEnabled(true);
                newPage1();
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2.setEnabled(false);
                page1.setEnabled(true);
                newPage2();
            }
        });
    }

    public void newPage2()
    {
        m6.setVisibility(View.VISIBLE);
        m7.setVisibility(View.VISIBLE);
        m8.setVisibility(View.VISIBLE);
        m9.setVisibility(View.VISIBLE);
        m10.setVisibility(View.VISIBLE);

        p6.setVisibility(View.VISIBLE);
        p7.setVisibility(View.VISIBLE);
        p8.setVisibility(View.VISIBLE);
        p9.setVisibility(View.VISIBLE);
        p10.setVisibility(View.VISIBLE);

        t6.setVisibility(View.VISIBLE);
        t7.setVisibility(View.VISIBLE);
        t8.setVisibility(View.VISIBLE);
        t9.setVisibility(View.VISIBLE);
        t10.setVisibility(View.VISIBLE);

        c6.setVisibility(View.VISIBLE);
        c7.setVisibility(View.VISIBLE);
        c8.setVisibility(View.VISIBLE);
        c9.setVisibility(View.VISIBLE);
        c10.setVisibility(View.VISIBLE);

        m1.setVisibility(View.GONE);
        m2.setVisibility(View.GONE);
        m3.setVisibility(View.GONE);
        m4.setVisibility(View.GONE);
        m5.setVisibility(View.GONE);

        p1.setVisibility(View.GONE);
        p2.setVisibility(View.GONE);
        p3.setVisibility(View.GONE);
        p4.setVisibility(View.GONE);
        p5.setVisibility(View.GONE);

        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        t5.setVisibility(View.GONE);

        c1.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);
        c3.setVisibility(View.GONE);
        c4.setVisibility(View.GONE);
        c5.setVisibility(View.GONE);
    }

    public void newPage1()
    {
        m1.setVisibility(View.VISIBLE);
        m2.setVisibility(View.VISIBLE);
        m3.setVisibility(View.VISIBLE);
        m4.setVisibility(View.VISIBLE);
        m5.setVisibility(View.VISIBLE);

        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.VISIBLE);
        p4.setVisibility(View.VISIBLE);
        p5.setVisibility(View.VISIBLE);

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);
        t5.setVisibility(View.VISIBLE);

        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.VISIBLE);
        c3.setVisibility(View.VISIBLE);
        c4.setVisibility(View.VISIBLE);
        c5.setVisibility(View.VISIBLE);

        m6.setVisibility(View.GONE);
        m7.setVisibility(View.GONE);
        m8.setVisibility(View.GONE);
        m9.setVisibility(View.GONE);
        m10.setVisibility(View.GONE);

        p6.setVisibility(View.GONE);
        p7.setVisibility(View.GONE);
        p8.setVisibility(View.GONE);
        p9.setVisibility(View.GONE);
        p10.setVisibility(View.GONE);

        t6.setVisibility(View.GONE);
        t7.setVisibility(View.GONE);
        t8.setVisibility(View.GONE);
        t9.setVisibility(View.GONE);
        t10.setVisibility(View.GONE);

        c6.setVisibility(View.GONE);
        c7.setVisibility(View.GONE);
        c8.setVisibility(View.GONE);
        c9.setVisibility(View.GONE);
        c10.setVisibility(View.GONE);
    }

    public void display(){

        if(c1.isChecked() && Integer.parseInt(t1.getText().toString())>0)
        {
            str+=c1.getText().toString()+" : ";
            str+=String.valueOf(tc1);
            str+="\n";
        }
        if(c2.isChecked() && Integer.parseInt(t2.getText().toString())>0)
        {
            str+=c2.getText().toString()+" : ";
            str+=String.valueOf(tc2);
            str+="\n";
        }
        if(c3.isChecked() && Integer.parseInt(t3.getText().toString())>0)
        {
            str+=c3.getText().toString()+" : ";
            str+=String.valueOf(tc3);
            str+="\n";
        }
        if(c4.isChecked() && Integer.parseInt(t4.getText().toString())>0)
        {
            str+=c4.getText().toString()+" : ";
            str+=String.valueOf(tc4);
            str+="\n";
        }
        if(c5.isChecked() && Integer.parseInt(t5.getText().toString())>0)
        {
            str+=c5.getText().toString()+" : ";
            str+=String.valueOf(tc5);
            str+="\n";
        }
        if(c6.isChecked() && Integer.parseInt(t6.getText().toString())>0)
        {
            str+=c6.getText().toString()+" : ";
            str+=String.valueOf(tc6);
            str+="\n";
        }
        if(c7.isChecked() && Integer.parseInt(t7.getText().toString())>0)
        {
            str+=c7.getText().toString()+" : ";
            str+=String.valueOf(tc7);
            str+="\n";
        }
        if(c8.isChecked() && Integer.parseInt(t8.getText().toString())>0)
        {
            str+=c8.getText().toString()+" : ";
            str+=String.valueOf(tc8);
            str+="\n";
        }
        if(c9.isChecked() && Integer.parseInt(t9.getText().toString())>0)
        {
            str+=c9.getText().toString()+" : ";
            str+=String.valueOf(tc9);
            str+="\n";
        }
        if(c10.isChecked() && Integer.parseInt(t10.getText().toString())>0)
        {
            str+=c10.getText().toString()+" : ";
            str+=String.valueOf(tc10);
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