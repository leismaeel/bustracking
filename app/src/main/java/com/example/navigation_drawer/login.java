package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class login extends AppCompatActivity {

    EditText editText,pass;
    ImageView imageView;
    String sesion,degree;
    int  d=0;
    int i=0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         editText = findViewById(R.id.edittextemail);
        pass = findViewById(R.id.edittextpassword);
        final TextView forgetpassword = findViewById(R.id.forgaypassword);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetpassword();
                //here is the code for the recovery of the password by the help of the email of the user
//                Intent intent = new Intent(login.this,forgetpasswordrecovery.class);
//                startActivity(intent);
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent intent= new Intent(login.this,MainActivity.class);
            login.this.startActivity(intent);
            finish();
        }
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(login.this);
                progressDialog.setMessage("Signing in.....");
                progressDialog.setTitle("Please Wait");
                progressDialog.show();
                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(editText.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "success", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(login.this,MainActivity.class);
                            login.this.startActivity(intent);
                            finish();
                        }
                        else
                        {
                            progressDialog.dismiss();;
                            Toast.makeText(login.this, "failure", Toast.LENGTH_SHORT).show();
                        }

                    }
                });





            }
        });
        TextView Signup = findViewById(R.id.aaaaaaaaaaaaaaaaaaaaaaaa);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent =new Intent(login.this,signup.class);
                        login.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
       System.exit(0);
    }
   public void forgetpassword()
   {
       AlertDialog.Builder alert = new AlertDialog.Builder(this);

       alert.setTitle("Recovery Module");
       alert.setMessage("Enter Your Email to recover your password");

// Set an EditText view to get user input
       final EditText input = new EditText(this);
       alert.setView(input);

       alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int whichButton) {
               String value = input.getText().toString();
               if(value.equals(""))
               {
                   Toast.makeText(login.this, "invalid input", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(login.this, value, Toast.LENGTH_SHORT).show();
                   sendrecoveryemail(value);
               }

           }
       });

       alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int whichButton) {
               // Canceled.
           }
       });

       alert.show();
   }
   public void sendrecoveryemail(String email)
   {
       FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {
                   Toast.makeText(login.this, "successfully sent recovery email", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(login.this, "failed to send message", Toast.LENGTH_SHORT).show();
               }
           }
       });
   }

}

