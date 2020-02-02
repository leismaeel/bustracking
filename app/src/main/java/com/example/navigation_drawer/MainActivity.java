package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Database;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.formattedadress.Formattedadress;
import com.example.navigation_drawer.formattedadress.Result;
import com.example.navigation_drawer.formattedadress.api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private static final String url = "https://api.opencagedata.com/geocode/v1/";
    String TAG = "TAG";
     int i =0;
    public Uri filePath;

    String imageURL;

    private final int PICK_IMAGE_REQUEST = 71;
    private DrawerLayout drawerLayout;
    String Location;
    ProgressDialog progressDialog;
    NavigationView navigationView;
    Bitmap bitmap;
    String n;
    public ImageView imageviewfortheheader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comsats Tracking App");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        databaseReference = databaseReference.child("busno");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Driver3");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Driver4");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Driver5");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Driver1");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Driver2");

                 n = dataSnapshot.getValue().toString();
                 Log.w("studetndata",n);
                 if(n.equals("1"))
                 {
                     FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent1");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent2");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent3");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent4");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent5");
                 }
                 else if(n.equals("2"))
                 {
                     FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent2");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent1");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent3");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent4");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent5");
                 }
                 else if(n.equals("3"))
                 {
                     FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent3");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent2");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent1");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent4");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent5");
                 }
                 else if(n.equals("4"))
                 {
                     FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent4");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent2");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent3");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent1");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent5");
                 }
                 else if(n.equals("5"))
                 {
                     FirebaseMessaging.getInstance().subscribeToTopic("AdminNotificationforstudent5");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent2");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent3");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent4");
                     FirebaseMessaging.getInstance().unsubscribeFromTopic("AdminNotificationforstudent1");
                 }
                FirebaseMessaging.getInstance().subscribeToTopic("Driver"+n).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Driver"+n, Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


         navigationView = findViewById(R.id.nav_view);
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.routesofbuses);
        bitmap = drawable.getBitmap();
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =  headerView.findViewById(R.id.firsttext);
        TextView usermail = headerView.findViewById(R.id.secondtext);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);

        }


        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
       usermail.setText(user.getEmail().toString());

       navUsername.setText(user.getDisplayName());
       imageviewfortheheader = headerView.findViewById(R.id.imageviewfortheheader);


        Toast.makeText(this, user.getDisplayName().toString(), Toast.LENGTH_SHORT).show();
        Log.d("TAG",user.getEmail().toString());

        LocationTrack locationTrack = new LocationTrack(this);

        drawerLayout = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //drawerLayout.closeDrawer(GravityCompat.START);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container,new homefragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {

            case R.id.home:
                homefragment h = new homefragment();
                Bundle bundle = new Bundle();
                bundle.putString("value","location");
                h.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container,h).commit();
                break;
            case R.id.report:
                androidx.appcompat.app.AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Report Sending Module\n");
                alert.setMessage("Enter Your Issue here to Report");

// Set an EditText view to get user input
                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setMessage("wait while we fetch the data for u ");
                        //progressDialog.show();

                        if(value.equals(""))
                        {
                            Toast.makeText(MainActivity.this, "invalid input", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("StudentsNotifications");
                            databaseReference = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(MainActivity.this, "your report has been sent", Toast.LENGTH_SHORT).show();
                                       // progressDialog.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                        //progressDialog.dismiss();
                                    }
                                }
                            });

                        }

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.

                    }
                });

                alert.show();

                // Toast.makeText(this, "Report section under dev", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container,new notifcationfragment()).commit();
                break;
            case R.id.share:
                Toast.makeText(this, "share pressed", Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                api a = retrofit.create(api.class);
                LocationTrack locationTrack = new LocationTrack(getApplicationContext());
                Double lat = locationTrack.getLatitude();
                Double lon = locationTrack.getLongitude();

                Call<Formattedadress> call = a.getfulldata("json?q=" + Double.toString(lat)+ "+" + Double.toString(lon) + "&key=61d2f365245049c585906f0b11ead03e");
                call.enqueue(new Callback<Formattedadress>() {
                    @Override
                    public void onResponse(Call<Formattedadress> call, Response<Formattedadress> response) {
                        Formattedadress formattedadress = response.body();
                        List<Result> ans = formattedadress.getResults();
                        Result a1 = ans.get(0);
                        Toast.makeText(MainActivity.this, a1.getFormatted(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT,a1.getFormatted());
                        startActivity(intent.createChooser(intent,"Testing"));
                        //Toast.makeText(MainActivity.this, a1.getFormatted().toString(), Toast.LENGTH_SHORT).show();
                        //Log.w("RESULT", lan + " " + lat);
                        // Log.w("RESULT", a1.getFormatted().toString());
                    }

                    @Override
                    public void onFailure(Call<Formattedadress> call, Throwable t) {

                    }
                });

                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(MainActivity.this,login.class);
                MainActivity.this.startActivity(intent1);
                break;
            case R.id.setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container,new profileedit()).commit();
                break;

            case R.id.routes:
                AlertDialog.Builder alert1 = new AlertDialog.Builder(this);

                alert1.setTitle("Routes saving  Module");
                alert1.setMessage("routes will be saved in the folder comsat bus routes under the name of routes." +
                        "press ok to confirm");

// Set an EditText view to get user inpu

                alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        FileOutputStream outputStream = null;
                        File sdcard = Environment.getExternalStorageDirectory();
                        File directory= new File(sdcard.getAbsolutePath()+"/Comsats Bus Routes");
                        directory.mkdir();
                        //String filename= String.format("%d.jpg",System.currentTimeMillis());
                        String filename= String.format("Routes.jpg");
                        File outfile = new File(directory,filename);
                        Toast.makeText(MainActivity.this, "Image saved successfully", Toast.LENGTH_SHORT).show();
                        try {
                            outputStream= new FileOutputStream(outfile);
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                            outputStream.flush();
                            outputStream.close();

                            Intent intent2 = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            intent2.setData(Uri.fromFile(outfile));
                            sendBroadcast(intent2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                alert1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        Toast.makeText(MainActivity.this, "cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                alert1.show();



                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

//    @Override
//    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START))
//        {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else
//        {
//            if(i==0)
//            {
//                Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT).show();
//                i++;
//            }
//            else
//            {
//                super.onBackPressed();
//            }
//        }

}




