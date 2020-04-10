package com.project.intellispirit.Adapters;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.project.intellispirit.R;

public class TermSubjectActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Term1Subject1ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar toolbar;
    ImageView imageView_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_subject);

        imageView_profile=findViewById(R.id.profile_image);

        toolbar = findViewById(R.id.mytool);
        setSupportActionBar(toolbar);

        imageView_profile.setVisibility(View.GONE);

        int pos = Integer.parseInt(getIntent().getStringExtra("position"));


if(pos==0){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Term1SubjectFragment()).commit();}
    }
}
