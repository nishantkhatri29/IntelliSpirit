package com.project.intellispirit.UserProfilePages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserHomePages.Teacher_Activity;

public class Profile_Teacher extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_profile;
    TextView textViewtool;
    LinearLayout classLayout, sectionLayout;
    View classView, sectionView;

    TextView name_teacher;
    TextView school_teacher;
    TextView class_teacher;
    TextView section_teacher;
    TextView DOB_teacher;
    private Button student_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__teacher);

        classLayout = findViewById(R.id.layout_class);
        sectionLayout = findViewById(R.id.layout_section);

        classView = findViewById(R.id.divider4);
        sectionView = findViewById(R.id.divider5);


        toolbar = findViewById(R.id.mytool);

        textViewtool = findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");

        imageView_profile = findViewById(R.id.profile_image);
        imageView_profile.setVisibility(View.INVISIBLE);

        name_teacher = findViewById(R.id.tv_name);
        school_teacher = findViewById(R.id.tv_school);
        class_teacher = findViewById(R.id.tv_class);
        section_teacher = findViewById(R.id.tv_section);
        DOB_teacher = findViewById(R.id.tv_DOB);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Teacher_Activity.class);
                startActivity(intent);
            }
        });

        student_logout = findViewById(R.id.account_logout);
        student_logout.setOnClickListener(new View.OnClickListener() {
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
        name_teacher.setText(name);
        school_teacher.setText(school);
        class_teacher.setText(Class);
        section_teacher.setText(section);
        DOB_teacher.setText(DOB);

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
