package com.example.navigation_drawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;

public class reportlayout extends Fragment {
    String text;
    EditText editText;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.reportlayout,container,false);
        editText = view.findViewById(R.id.edittextforreportstudent);
        ImageView imageView=  view.findViewById(R.id.micforreportstudent);
        final String data = editText.getText().toString();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if(intent.resolveActivity(getActivity().getPackageManager())!=null)
                {
                    startActivityForResult(intent,10);
                }
                else
                    Toast.makeText(view.getContext(), "Device doesnot support SPPECH TO TEXT ", Toast.LENGTH_SHORT).show();
            }

        });
        final EditText editText= view.findViewById(R.id.edittextforreportstudent);
        Button button= view.findViewById(R.id.submitstudent);
        String reporttext = editText.getText().toString() ;
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentsNotifications");
        databaseReference = databaseReference.child(FirebaseAuth.getInstance().getUid());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setMessage("Sending to Database");
                progressDialog.show();
                databaseReference.setValue(editText.getText().toString());
                Toast.makeText(view.getContext(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();;
            }
        });

        return  view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 10:
                if(resultCode==-1 && data!=null)
                {
                    ArrayList<String> result=  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editText.setText(result.get(0));

                }
                break;

        }


    }
}
