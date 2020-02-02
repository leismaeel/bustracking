package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Handler mhandler = new Handler();
    double lat , lon;
    int i=1;
    Bitmap smallMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        int height = 200;
        int width = 150;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.busicon);
        Bitmap b=bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button stop = findViewById(R.id.stoptrackingbutton);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhandler.removeCallbacks(run);
                i=0;
                Toast.makeText(MapsActivity.this, "stopped nigger", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(12f);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location").child("BUS1");
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                directions d = dataSnapshot.getValue(directions.class);

                lat = d.getLat();
                lon = d.getLon();
                mMap.clear();


                LatLng currlocation = new LatLng(lat, lon);
                Toast.makeText(getApplicationContext(), Double.toString(lat), Toast.LENGTH_SHORT).show();

                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lon)).title("Bus is here right now nigger");
                marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                mMap.addMarker(marker);
              //  mMap.addMarker(new MarkerOptions().position(sydney).title("your postion"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currlocation));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Add a marker in Sydney and move the camera
        //run.run();
    }
    public Runnable run = new Runnable() {
        @Override
        public void run() {
            mMap.clear();
             i=1;




            // mMap.setMyLocationEnabled(true);

            mhandler.postDelayed(this,3000);
        }
    };

    @Override
    public void onBackPressed() {
        if(i==0)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(MapsActivity.this, "cant quit now bruv", Toast.LENGTH_SHORT).show();
        }
    }
}
