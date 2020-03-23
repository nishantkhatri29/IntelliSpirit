package com.project.intellispirit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Activity extends AppCompatActivity {

    private Button adminLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);

        adminLogoutButton=findViewById(R.id.admin_logout);

        adminLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void logout() {
        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();

    }
}
