package com.project.intellispirit.UserProfilePages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.intellispirit.R;
import com.project.intellispirit.UserHomePages.MainActivity;

public class Profile_Student extends AppCompatActivity {
    Toolbar toolbar;
ImageView imageView_profile;
TextView textViewtool;

    TextView Name;
    TextView School;
    TextView ClassTV;
    TextView Section;
    TextView DOBTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__student);
        imageView_profile=findViewById(R.id.profile_image);
        textViewtool=findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");
         imageView_profile.setVisibility(View.INVISIBLE);
        toolbar=findViewById(R.id.mytool);

        Name=findViewById(R.id.tv_studentname);
        School=findViewById(R.id.tv_studentschool);
        ClassTV=findViewById(R.id.tv_studentclass);
        Section=findViewById(R.id.tv_studentsection);
        DOBTV=findViewById(R.id.tv_studentDOB);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile_Student.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String name=sharedPreferences.getString("Name","");
        String Class=sharedPreferences.getString("Class","");
        String section=sharedPreferences.getString("Section","");
        String school=sharedPreferences.getString("School","");
        String DOB=sharedPreferences.getString("DOB","");
        Name.setText(name);
        School.setText(school);
        ClassTV.setText(Class);
        Section.setText(section);
        DOBTV.setText(DOB);

    }
}
