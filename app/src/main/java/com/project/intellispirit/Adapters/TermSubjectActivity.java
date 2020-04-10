package com.project.intellispirit.Adapters;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.project.intellispirit.R;

public class TermSubjectActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Term1Subject1ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_subject);
        int pos = Integer.parseInt(getIntent().getStringExtra("position"));


if(pos==0){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Term1SubjectFragment()).commit();}
    }
}
