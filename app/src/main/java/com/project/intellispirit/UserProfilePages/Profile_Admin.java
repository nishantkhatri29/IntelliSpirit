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
    LinearLayout classLayout, sectionLayout,DOBLayout,schoolLayout;
    View classView, sectionView;
TextView tv_adminzoneid,tv_admindistricid;
TextView zone_id,district_id;
String zoneid,districtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__admin);
        zone_id=findViewById(R.id.tvname);
        DOBLayout=findViewById(R.id.layout_DOB);
        district_id=findViewById(R.id.tvsection);
        classLayout = findViewById(R.id.layout_class);
        sectionLayout = findViewById(R.id.layout_section);
        schoolLayout=findViewById(R.id.layout_school);
        zone_id.setText("Zone ID");
        district_id.setText("District ID");
        tv_adminzoneid=findViewById(R.id.tv_name);
        tv_admindistricid=findViewById(R.id.tv_section);

        classView = findViewById(R.id.divider4);
        sectionView = findViewById(R.id.divider5);
        DOBLayout.setVisibility(View.GONE);
        classLayout.setVisibility(View.GONE);
        sectionLayout.setVisibility(View.GONE);
        schoolLayout.setVisibility(View.GONE);

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
        zoneid=sharedPreferences.getString("zoneid","");
       // districtid=sharedPreferences.getString("districtid","");
        tv_adminzoneid.setText(zoneid);
        //tv_admindistricid.setText(districtid);



    }
}
