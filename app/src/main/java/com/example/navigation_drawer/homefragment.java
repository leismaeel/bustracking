package com.example.navigation_drawer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class homefragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lat,lon;
    String stringbus,fullbusno;
    int i;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.homelayout, container, false);
        i=1;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BusStatus");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("false"))
                {
                    TextView textView = view.findViewById(R.id.textinsteadofmaps);
                    textView.setVisibility(View.VISIBLE);
                    RelativeLayout mapslayout = view.findViewById(R.id.mapslayout);
                    mapslayout.setVisibility(View.GONE);
                }
                else if(dataSnapshot.getValue().toString().equals("true"))
                {
                    i=0;
                    TextView textView = view.findViewById(R.id.textinsteadofmaps);
                    textView.setVisibility(View.GONE);
                    getready(view);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;


    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.3092293,-122.1136845))
                .title("Apple"));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 stringbus = dataSnapshot.child("busno").getValue().toString();
                fullbusno = "BUS"+stringbus;
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference1 = database.getReference("Location").child(fullbusno);
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        googleMap.clear();
                        String lat = dataSnapshot.child("lat").getValue().toString();
                        String lon = dataSnapshot.child("lon").getValue().toString();
                        Double dlat = Double.parseDouble(lat);
                        Double dlon = Double.parseDouble(lon);

                        Log.w("locations",lat+" "+lon);
                        MarkerOptions marker = new MarkerOptions().position(new LatLng(dlat, dlon)).title("Bus is here right now ");
                        googleMap.addMarker(marker);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dlat,dlon),16));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private  void getready(final View view)
    {
        RelativeLayout mapslayout = view.findViewById(R.id.mapslayout);
        mapslayout.setVisibility(View.VISIBLE);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if(mapFragment==null)
        {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
        Button stop = view.findViewById(R.id.stoptrackingbutton);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(view.getContext(),MapsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
