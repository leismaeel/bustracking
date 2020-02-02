package com.example.navigation_drawer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class profileedit extends Fragment {

    ProgressDialog progressDialog;
    ImageView editname, editpasssword, editemail, editbusno, editcontact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profileedit, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final TextView name = view.findViewById(R.id.name);
        final TextView email = view.findViewById(R.id.email);
        final TextView bus = view.findViewById(R.id.Busno);
        final TextView contact = view.findViewById(R.id.contact);
        editname = view.findViewById(R.id.editname);
        editpasssword = view.findViewById(R.id.editpassword);
        editbusno = view.findViewById(R.id.editbusno);
        editcontact = view.findViewById(R.id.editcontact);
        final TextView password = view.findViewById(R.id.passssssssssword);
        editcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactchange(view);
            }
        });
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("wait while we fetch the data for u ");
        progressDialog.show();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference = databaseReference.child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.w("values", dataSnapshot.getValue().toString());

                String n = dataSnapshot.child("name").getValue().toString();
                String e = dataSnapshot.child("email").getValue().toString();
                name.setText("" + n);
                bus.setText("BusNo:"+dataSnapshot.child("busno").getValue().toString());
                email.setText("" + e);
                password.setText("************");
                contact.setText(dataSnapshot.child("contact").getValue().toString());

//                password.setText("Password: "+dataSnapshot.child("password").getValue().toString());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namechanger(view);
            }
        });
        editbusno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // busnochange(view);
            }
        });

        editpasssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // passwordchanger(view);
            }
        });
        return view;
    }

    void namechanger(final View view) {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Change name", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid().toString());
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(userInputDialogEditText.getText().toString()).build();
                        user.updateProfile(profileUpdates);
                        databaseReference.child("name").setValue(userInputDialogEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful())
                                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }

    void busnochange(final View view) {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Change Busno", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here


                         TextView busi =view.findViewById(R.id.Busno);
                         final String a = busi.getText().toString();
                        Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid().toString());
                        databaseReference.child("busno").setValue(userInputDialogEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful())
                                {

                                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    void passwordchanger(final View view) {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("enter your Mailing adress", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid().toString());
                        databaseReference.child("password").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.w("password", dataSnapshot.getValue().toString());
                                if (userInputDialogEditText.getText().toString().equals(dataSnapshot.getValue().toString())) {
                                    FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.w("RECOVERYEMAIL", "Email sent.");

                                                        Toast.makeText(getContext(), "email sent", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(getContext(), "wrong password", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }

    public void contactchange(View view) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Change Contact", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid().toString());
                        databaseReference.child("contact").setValue(userInputDialogEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful())
                                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }
}

