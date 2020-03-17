package com.project.intellispirit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonlogout;
    private static Context mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonlogout=findViewById(R.id.btnlogout);

        final TestDialog testDialog = new TestDialog();
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Do you want to set your password?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        testDialog.show(getSupportFragmentManager(),"test dialog");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                        builder.setCancelable(true);

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();



        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });



    }

    public void logout(){
        SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
