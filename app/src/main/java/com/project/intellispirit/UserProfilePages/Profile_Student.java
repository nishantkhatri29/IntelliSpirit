package com.project.intellispirit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile_Student extends AppCompatActivity {
    Toolbar toolbar;
ImageView imageView_profile;
TextView textViewtool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__student);
        imageView_profile=findViewById(R.id.profile_image);
        textViewtool=findViewById(R.id.tv_tool);
        textViewtool.setText("Profile");
         imageView_profile.setVisibility(View.INVISIBLE);
        toolbar=findViewById(R.id.mytool);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile_Student.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
