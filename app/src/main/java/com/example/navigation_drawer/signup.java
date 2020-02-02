package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class signup extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    TextInputLayout buslayout,emailayout,passwordlayout,namelayout,reglayout,contactlayout;
     EditText name,password,email,busno,regno,contact;
    ProgressDialog progressDialog;
    String number,jj;
    Integer busstrength,n;
    static int varcreateuser,varadddataofuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog= new ProgressDialog(this);
        name=  findViewById(R.id.name);
        password = findViewById(R.id.password);
        email =findViewById(R.id.email);
        contactlayout = findViewById(R.id.contactlayout);
        contact =  findViewById(R.id.contact);
        regno = findViewById(R.id.registrationnumber);
        reglayout = findViewById(R.id.Reglayout);
        busno = findViewById(R.id.busno);
        namelayout =findViewById(R.id.namelayout);
        emailayout = findViewById(R.id.emmaillayout);
        passwordlayout = findViewById(R.id.passswordlayout);
        buslayout=findViewById(R.id.busnolayout);
        Button button= findViewById(R.id.signupbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    progressDialog.setMessage("Signing Up ");
                    progressDialog.setTitle("Please Wait");
                    progressDialog.show();
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("busdata").child("busno"+busno.getText().toString());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            number = dataSnapshot.getValue().toString();
                            if(dataSnapshot.getValue().toString().equals("full"))
                            {
                                Toast.makeText(signup.this, "Bus no"+busno.getText().toString()+" is full", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();;
                            }
                            else
                            {
                               // databaseReference.setValue(String.valueOf(a));
                                createuser(email.getText().toString(),password.getText().toString(),name.getText().toString(),regno.getText().toString(),busno.getText().toString(),contact.getText().toString());

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(signup.this, "Error in Validation", Toast.LENGTH_SHORT).show();
                }







            }
        });
    }


    void adddataoftheuser(final String name, String email, String password, String regno, final String busno, String con )
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid().toString());
        userdata ud=new userdata(name,email,busno,regno,con);
        databaseReference.setValue(ud).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name).build();

                    Toast.makeText(signup.this, "created user successfully and data added", Toast.LENGTH_SHORT).show();
                    user.updateProfile(profileUpdates);
                    progressDialog.dismiss();
                    Intent intent = new Intent(signup.this,MainActivity.class);
                    signup.this.startActivity(intent);

                }
                else
                {
                    Toast.makeText(signup.this, "error in adding data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });


    }


    void createuser(final String emaail, final String pasword, final String naame, final String reggno, final String bussno, final String contact)
    {

         firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emaail,pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    varcreateuser=1;
                    adddataoftheuser(naame,emaail,pasword,reggno,bussno,contact);

                }
                else
                {
                    Toast.makeText(signup.this, "error in authentication", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

        });


    }

Boolean validation(){

        if(name.getText().toString().equals(""))
        {
            namelayout.setError("Invalid Name");
            return false;
        }
        else if(password.getText().toString().length()<7)
        {
            namelayout.setError(null);
            if(password.getText().toString().equals(""))
            passwordlayout.setError("Invalid password");
            else
                passwordlayout.setError("Password should be minimum 7 characters");
            return false;
        }
        else if(email.getText().toString().equals(""))
        {
            namelayout.setError(null);
            passwordlayout.setError(null);
            emailayout.setError("Invalid Email");
            return false;
        }

        else if(busno.getText().toString().equals("")||((!(busno.getText().toString().equals("1")))&&(!(busno.getText().toString().equals("2")))&&(!(busno.getText().toString().equals("3")))&&(!(busno.getText().toString().equals("4")))&&(!(busno.getText().toString().equals("5")))))
        {
            namelayout.setError(null);
            passwordlayout.setError(null);
            emailayout.setError(null);
            buslayout.setError("Invalid BusNO");
            return false;
        }
        else if(contact.getText().toString().equals(""))
        {
            namelayout.setError(null);
            passwordlayout.setError(null);
            emailayout.setError(null);
            buslayout.setError(null);
            contactlayout.setError("Invalid Number");
            return false;
        }
        else if(regno.getText().toString().equals(""))
        {
            namelayout.setError(null);
            passwordlayout.setError(null);
            emailayout.setError(null);
            buslayout.setError(null);
            contactlayout.setError(null);
            reglayout.setError("Invalid Registration Number");
            return false;
        }
        else
        {

            namelayout.setError(null);
            passwordlayout.setError(null);
            emailayout.setError(null);
            buslayout.setError(null);
            reglayout.setError(null);
            contactlayout.setError(null);
            return true;
        }

}


}
