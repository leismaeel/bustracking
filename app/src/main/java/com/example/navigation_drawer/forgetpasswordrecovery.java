package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpasswordrecovery extends AppCompatActivity { ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpasswordrecovery);
        final EditText editText = findViewById(R.id.edittextforrecoveryofthepassword);
        String email = editText.getText().toString();
        Button button = findViewById(R.id.recoverpassswordbutton);
        editText.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(editText.getText().toString().equals(""))
            {
                Toast.makeText(forgetpasswordrecovery.this, "text not valid", Toast.LENGTH_SHORT).show();

            }
            else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(editText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.w("RECOVERYEMAIL", "Email sent.");

                                    Toast.makeText(getApplicationContext(), "Sent. Please Check Email to Reset Your Email", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Log.w("RECOVERYEMAIL", "Email NOT SENT.");

                                    Toast.makeText(forgetpasswordrecovery.this, "Error the mail is not present in the database record", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
            }
        });
    }
}
