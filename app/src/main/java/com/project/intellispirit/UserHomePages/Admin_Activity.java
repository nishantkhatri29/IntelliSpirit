package com.project.intellispirit.UserHomePages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserProfilePages.Profile_Admin;
import com.project.intellispirit.UserProfilePages.Profile_Teacher;

public class Admin_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        toolbar = findViewById(R.id.mytool);

        imageView_profile=findViewById(R.id.profile_image);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile_Admin.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


}
