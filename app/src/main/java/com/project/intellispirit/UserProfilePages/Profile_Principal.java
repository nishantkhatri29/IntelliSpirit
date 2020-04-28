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
import com.project.intellispirit.UserHomePages.Principal_Activity;

public class Profile_Principal extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_profile;
    TextView textViewtool;
    LinearLayout classLayout, sectionLayout;
    View classView, sectionView;

    TextView name_principal;
    TextView school_principal;
    TextView class_principal;
    TextView section_principal;
    TextView DOB_principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__principal);

        classLayout = findViewById(R.id.layout_class);
        sectionLayout = findViewById(R.id.layout_section);

        classView = findViewById(R.id.divider4);
        sectionView = findViewById(R.id.divider5);

        classLayout.setVisibility(View.GONE);
        sectionLayout.setVisibility(View.GONE);

        classView.setVisibility(View.GONE);
        sectionView.setVisibility(View.GONE);

        toolbar = findViewById(R.id.mytool);

        textViewtool = findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");

        imageView_profile = findViewById(R.id.profile_image);
        imageView_profile.setVisibility(View.INVISIBLE);

        name_principal=findViewById(R.id.tv_name);
        school_principal=findViewById(R.id.tv_school);
        class_principal=findViewById(R.id.tv_class);
        section_principal=findViewById(R.id.tv_section);
        DOB_principal=findViewById(R.id.tv_DOB);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Principal_Activity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);


        String name=sharedPreferences.getString("name","");

        String school=sharedPreferences.getString("school","");
        String DOB=sharedPreferences.getString("DOB","");
        name_principal.setText(name);
        school_principal.setText(school);
        DOB_principal.setText(DOB);


    }
}
