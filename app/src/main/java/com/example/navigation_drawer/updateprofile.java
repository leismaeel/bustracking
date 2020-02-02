package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateprofile extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final TextView name = findViewById(R.id.name);
        final TextView email = findViewById(R.id.email);
        final TextView bus = findViewById(R.id.Busno);
        final TextView password = findViewById(R.id.passssssssssword);
         progressDialog = new ProgressDialog(updateprofile.this);
         progressDialog.setMessage("wait while we fetch the data for u ");
         progressDialog.show();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference = databaseReference.child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.w("values",dataSnapshot.getValue().toString());

                 String n=    dataSnapshot.child("name").getValue().toString();
                 String e = dataSnapshot.child("email").getValue().toString();
                 name.setText("Name: "+n);
                 bus.setText("Busno: "+dataSnapshot.child("busno").getValue().toString());
                 email.setText("Email: " +e);
                 password.setText("Password: "+dataSnapshot.child("password").getValue().toString());
                 progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
