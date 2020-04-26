package com.project.intellispirit.UserProfilePages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.intellispirit.R;
import com.project.intellispirit.UserHomePages.Admin_Activity;
import com.project.intellispirit.UserHomePages.Teacher_Activity;

public class Profile_Admin extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_profile;
    TextView textViewtool;
    LinearLayout classLayout, sectionLayout;
    View classView, sectionView;

    TextView name_admin;
    TextView school_admin;
    TextView class_admin;
    TextView section_admin;
    TextView DOB_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__admin);

        classLayout = findViewById(R.id.layout_class);
        sectionLayout = findViewById(R.id.layout_section);

        classView = findViewById(R.id.divider4);
        sectionView = findViewById(R.id.divider5);

        classLayout.setVisibility(View.GONE);
        sectionLayout.setVisibility(View.GONE);

        classView.setVisibility(View.GONE);
        sectionView.setVisibility(View.GONE);

        toolbar = findViewById(R.id.mytool);

        textViewtool=findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");

        imageView_profile=findViewById(R.id.profile_image);
        imageView_profile.setVisibility(View.INVISIBLE);



        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Admin_Activity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);


        String name=sharedPreferences.getString("name","");
        String Class=sharedPreferences.getString("class","");
        String section=sharedPreferences.getString("section","");
        String school=sharedPreferences.getString("school","");
        String DOB=sharedPreferences.getString("DOB","");
        name_admin.setText(name);
        school_admin.setText(school);
        class_admin.setText(Class);
        section_admin.setText(section);
        DOB_admin.setText(DOB);
    }
}
