package com.example.napkinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class onlyAccept extends AppCompatActivity {

    ListView aclist;
    String dls;
    int itr,numOfOrder;
    String[] narr = new String[500];
    String[] parr = new String[500];
    String[] fcarr = new String[500];
    String[] odarr = new String[500];
    String[] adarr = new String[500];
    ArrayList<String> arrList;
    ArrayAdapter<String> arrAdapter;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_accept);

        aclist = findViewById(R.id.aclist);

        arrList = new ArrayList<String>();
        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        arrAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrList);
        aclist.setAdapter(arrAdapter);

        itr=0;
        numOfOrder=0;
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userMail us = snapshot.getValue(userMail.class);
                    if(us.getStatus().equals("Accepted")){
                        numOfOrder++;
                        dls = "NAME: "+us.getName()+"\n\n"+
                                "PHONE: "+us.getPhone()+"\n\n"+
                                "FEMALE COUNT: "+us.getFc()+"\n\n"+
                                "ADDRESS: "+us.getAddress()+"\n\n"+
                                "ORDER DETAILS: "+us.getOrder();
                        narr[itr] = us.getName();
                        parr[itr] = us.getPhone();
                        fcarr[itr] = us.getFc();
                        odarr[itr] = us.getOrder();
                        adarr[itr] = us.getAddress();
                        itr++;
                        arrList.add(dls);
                        arrAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        aclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(onlyAccept.this,acceptDisp.class);
                in.putExtra("details",arrList.get(i));
                in.putExtra("phone",parr[i]);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent c = new Intent(onlyAccept.this,adminOrders.class);
        startActivity(c);
    }
}
