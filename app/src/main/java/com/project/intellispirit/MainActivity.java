package com.project.intellispirit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button buttonlogout;

    UserSessionManager session;
    private static Context mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonlogout=findViewById(R.id.btnlogout);

        session = new UserSessionManager(getApplicationContext());
        TextView labelName = (TextView) findViewById(R.id.labelname);

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();
        if(session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(UserSessionManager.KEY_USERNAME);
        labelName.setText(username);


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
        session.logoutUser();
    }
}
