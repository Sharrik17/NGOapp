package com.example.napkinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminOrders extends AppCompatActivity {

    ListView list; int iter,numOfOrders;
    TextView text1;
    Button viewAccept;
    String details;
    String[] na = new String[500];
    String[] pa = new String[500];
    String[] fca = new String[500];
    String[] oda = new String[500];
    String[] addra = new String[500];
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        list = findViewById(R.id.list);
        viewAccept = findViewById(R.id.viewAccept);
        text1 = findViewById(R.id.text1);
        arrayList = new ArrayList<String>();
        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(adapter);

        iter=0;
        numOfOrders=0;
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userMail us = snapshot.getValue(userMail.class);
                    if(us.getStatus().equals("Pending")){
                        numOfOrders++;
                        details = "NAME: "+us.getName()+"\n"+
                                "PHONE: "+us.getPhone()+"\n"+
                                "FEMALE COUNT: "+us.getFc()+"\n"+
                                "ADDRESS: "+us.getAddress()+"\n"+
                                "ORDER DETAILS: "+us.getOrder();
                        na[iter] = us.getName();
                        pa[iter] = us.getPhone();
                        fca[iter] = us.getFc();
                        oda[iter] = us.getOrder();
                        addra[iter] = us.getAddress();
                        iter++;
                        arrayList.add(details);
                        adapter.notifyDataSetChanged();
                    }
                }

                if(numOfOrders==0)
                {
                    text1.setText("NO PENDING ORDERS");
                }
                else
                {
                    text1.setText("PENDING ORDERS");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(adminOrders.this,acceptOrder.class);
                in.putExtra("details",arrayList.get(i));
                in.putExtra("name",na[i]);
                in.putExtra("ph",pa[i]);
                in.putExtra("fc",fca[i]);
                in.putExtra("od",oda[i]);
                in.putExtra("address",addra[i]);
                startActivity(in);
            }
        });

        viewAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(adminOrders.this,onlyAccept.class);
                startActivity(ii);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "BACK is not allowed", Toast.LENGTH_SHORT).show();
    }
}
