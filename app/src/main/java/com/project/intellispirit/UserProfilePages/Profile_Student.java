package com.project.intellispirit.UserProfilePages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserHomePages.MainActivity;

public class Profile_Student extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_profile;
    TextView textViewtool;

    TextView name_student;
    TextView school_student;
    TextView class_student;
    TextView section_student;
    TextView DOB_student;
    private Button studentLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__student);
        imageView_profile = findViewById(R.id.profile_image);
        textViewtool = findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");
        imageView_profile.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.mytool);

        name_student = findViewById(R.id.tv_name);
        school_student = findViewById(R.id.tv_school);
        class_student = findViewById(R.id.tv_class);
        section_student = findViewById(R.id.tv_section);
        DOB_student = findViewById(R.id.tv_DOB);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Student.this, MainActivity.class);
                startActivity(intent);
            }
        });

        studentLogout = findViewById(R.id.account_logout);
        studentLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);


        String name = sharedPreferences.getString("name", "");
        String Class = sharedPreferences.getString("class", "");
        String section = sharedPreferences.getString("section", "");
        String school = sharedPreferences.getString("school", "");
        String DOB = sharedPreferences.getString("DOB", "");
        name_student.setText(name);
        school_student.setText(school);
        class_student.setText(Class);
        section_student.setText(section);
        DOB_student.setText(DOB);

    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}
