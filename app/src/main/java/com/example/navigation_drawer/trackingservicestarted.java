package com.example.navigation_drawer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class trackingservicestarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackingservicestarted);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(trackingservicestarted.this);
        builder1.setMessage("Tracking has started if u wish to stop please clock the stop bitton");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.show();

    }
}
