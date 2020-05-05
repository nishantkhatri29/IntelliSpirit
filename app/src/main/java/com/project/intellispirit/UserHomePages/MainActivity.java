package com.project.intellispirit.UserHomePages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.UserProfilePages.Profile_Student;
import com.project.intellispirit.R;
import com.project.intellispirit.Adapters.StudentTerm;
import com.project.intellispirit.Adapters.StudentTermAdapter;
import com.project.intellispirit.Adapters.TermSubjectActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageView_profile;

    private RecyclerView mRecyclerView;
    private StudentTermAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_profile=findViewById(R.id.profile_image);

        final ArrayList<StudentTerm> itemList = new ArrayList<>();
        itemList.add(new StudentTerm(R.drawable.term_one, "Term 1"));
        itemList.add(new StudentTerm(R.drawable.term_two, "Term 2"));
        itemList.add(new StudentTerm(R.drawable.term_three, "Term 3"));
        itemList.add(new StudentTerm(R.drawable.term_four, "Term 4"));



        toolbar = findViewById(R.id.mytool);
        setSupportActionBar(toolbar);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile_Student.class));
            }
        });

        mRecyclerView = findViewById(R.id.student_result_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StudentTermAdapter(itemList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new StudentTermAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, itemList.get(position).getmText(), Toast.LENGTH_SHORT).show();

                if(position==0){
                    Intent intent = new Intent(getApplicationContext(), TermSubjectActivity.class);
                    intent.putExtra("position",String.valueOf(position));
                    startActivity(intent);
                }
                else{
//                    Intent intent = new Intent(getApplicationContext(),TermSubjectActivity.class);
//                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), itemList.get(position).getmText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        final String username=sharedPreferences.getString("Username","");

        


    }

    public void logout() {
        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
