package com.example.navigation_drawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class notifcationfragment extends Fragment {
    ArrayList<String> names =new ArrayList<>();
    ArrayList<String>  dates =new ArrayList<>();
    String d1,d2,d3,d4,d5;
    BottomNavigationView bottomNavigationView;
    String adminnotification;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.notificationlayout, container, false);





         bottomNavigationView = view.findViewById(R.id.bootmnav);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Grabbing data from firebase");
        progressDialog.show();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DriverNotifications");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.w("got it",dataSnapshot.getValue().toString());
                d1=dataSnapshot.child("Driver1").getValue().toString();
                d2=dataSnapshot.child("Driver2").getValue().toString();
                d3=dataSnapshot.child("Driver3").getValue().toString();
                d4=dataSnapshot.child("Driver4").getValue().toString();
                d5 = dataSnapshot.child("Driver5").getValue().toString();
                getadmindata(view);
                progressDialog.dismiss();
                //addvalue( view);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;

    }
    void addvalue(final View view)
    {

        dates.clear();
        names.clear();
        dates.add("By: Driverofbusno1");
        dates.add("By: Driverofbusno2");
        dates.add("By: Driverofbusno3");
        dates.add("By: Driverofbusno4");
        dates.add("By: Driverofbusno5");
        names.add(d1);
        names.add(d2);
        names.add(d3);
        names.add(d4);
        names.add(d5);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        Recyclerviewadapter recyclerviewadapter = new Recyclerviewadapter(dates,view.getContext(),names);
        recyclerView.setAdapter(recyclerviewadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Toast.makeText(getContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                switch (menuItem.getTitle().toString()) {
                    case "Drivers Notifications":
                        TextView b = view.findViewById(R.id.textttttt);
                        b.setText("Drivers Notifications\n");
                        dates.clear();
                        names.clear();
                        dates.add("By: Driverofbusno1");
                        dates.add("By: Driverofbusno2");
                        dates.add("By: Driverofbusno3");
                        dates.add("By: Driverofbusno4");
                        dates.add("By: Driverofbusno5");
                        names.add(d1);
                        names.add(d2);
                        names.add(d3);
                        names.add(d4);
                        names.add(d5);
                         RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                        Recyclerviewadapter recyclerviewadapter = new Recyclerviewadapter(dates,view.getContext(),names);
                        recyclerView.setAdapter(recyclerviewadapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        return true;
                    case "Admin Notifications":
                        TextView a = view.findViewById(R.id.textttttt);
                        a.setText("Admin Notifications\n");
                        dates.clear();
                        names.clear();
                        //dates.add("");
                        dates.add("\n\n\nAdmin Notifications");
                        //dates.add("");
                        //dates.add("");
                        //names.add("");
                        names.add("\n\n"+adminnotification);
                        //names.add("");
                        //names.add("");
                        RecyclerView recyclerView1= view.findViewById(R.id.recycler_view);
                        Recyclerviewadapter recyclerviewadapter1 = new Recyclerviewadapter(dates,view.getContext(),names);
                        recyclerView1.setAdapter(recyclerviewadapter1);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        return true;


                }

                return true;
            }
        });
    }
    public void getadmindata(final View view){
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("busno");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String busno = dataSnapshot.getValue().toString();
                if(busno.equals("1"))
                {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminNotification").child("student");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            adminnotification =dataSnapshot.getValue().toString();
                            Log.w("tag",dataSnapshot.getValue().toString());
                            addvalue(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                else  if(busno.equals("2"))
                {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminNotification").child("student2");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            adminnotification =dataSnapshot.getValue().toString();
                            Log.w("tag",dataSnapshot.getValue().toString());
                            addvalue(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                else  if(busno.equals("3"))
                {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminNotification").child("student3");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            adminnotification =dataSnapshot.getValue().toString();
                            Log.w("tag",dataSnapshot.getValue().toString());
                            addvalue(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                else  if(busno.equals("4"))
                {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminNotification").child("student4");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            adminnotification =dataSnapshot.getValue().toString();
                            Log.w("tag",dataSnapshot.getValue().toString());
                            addvalue(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                else  if(busno.equals("5"))
                {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminNotification").child("student5");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            adminnotification =dataSnapshot.getValue().toString();
                            Log.w("tag",dataSnapshot.getValue().toString());
                            addvalue(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

