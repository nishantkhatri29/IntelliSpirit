package com.project.intellispirit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TermSubjectActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Term1Subject1ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_subject);
        int pos = Integer.parseInt(getIntent().getStringExtra("position"));


//
//        final ArrayList<Term1Subject1Item> itemList = new ArrayList<>();
//        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 1"));
//        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 2"));
//        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 3"));
//        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 4"));
//
//
//        mRecyclerView = findViewById(R.id.student_subject_recyclerview);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mAdapter = new Term1Subject1ItemAdapter(itemList);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new Term1Subject1ItemAdapter.OnSubjectItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(getApplicationContext(), itemList.get(position).getmText(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
if(pos==0){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Term1SubjectFragment()).commit();}
    }
}
