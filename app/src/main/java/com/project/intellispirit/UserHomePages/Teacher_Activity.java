package com.project.intellispirit.UserHomePages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserProfilePages.Profile_Teacher;

public class Teacher_Activity extends AppCompatActivity {
    private Button teacherLogoutButton;
    Toolbar toolbar;
    ImageView imageView_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_);
        toolbar = findViewById(R.id.mytool);

        imageView_profile=findViewById(R.id.profile_image);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile_Teacher.class));
            }
        });


        teacherLogoutButton=findViewById(R.id.teacher_logout);

        teacherLogoutButton.setOnClickListener(new View.OnClickListener() {
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
