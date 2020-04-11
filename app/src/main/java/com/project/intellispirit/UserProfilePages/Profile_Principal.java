package com.project.intellispirit.UserProfilePages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Principal_Activity.class);
                startActivity(intent);
            }
        });
    }
}
