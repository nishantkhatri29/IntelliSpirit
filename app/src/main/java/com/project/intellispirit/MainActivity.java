package com.project.intellispirit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private Button buttonlogout;
//    public Button updateButton;
//    public EditText newPassword;

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

        toolbar = findViewById(R.id.mytool);
        setSupportActionBar(toolbar);
//        buttonlogout = findViewById(R.id.btnlogout);
//        updateButton= findViewById(R.id.updateBtn);
//
//        newPassword=findViewById(R.id.newPass);
        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Profile_Student.class));
            }
        });

        mRecyclerView = findViewById(R.id.student_result_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StudentTermAdapter(itemList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new StudentTermAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, itemList.get(position).getmText(), Toast.LENGTH_SHORT).show();

                if(position==0){
                    Intent intent = new Intent(getApplicationContext(),TermSubjectActivity.class);
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


//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String password = newPassword.getText().toString().trim();
//                Toast.makeText(getApplicationContext(),password,Toast.LENGTH_LONG).show();
//
//
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATESTUDENTPASSWORD,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//
//                                try {
//                                    //converting response to json object
//                                    JSONObject obj = new JSONObject(response);
//
//                                    //if no error in response
//                                    if (!obj.getBoolean("error")) {
//                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                                        //getting the user from the response
//                                        JSONObject userJson = obj.getJSONObject("user");
//                                    } else {
//                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("student_id", username);
//                        params.put("password",password);
//                        return params;
//                    }
//                };
//
//                VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
//                editor.putString("Password","Hello");
//                editor.commit();
//                editor.apply();
//
//
//            }
//        });




;


//
//        final TestDialog testDialog = new TestDialog();
//        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//        builder.setTitle("Do you want to set your password?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        testDialog.show(getSupportFragmentManager(), "test dialog");
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
//                        builder.setCancelable(true);
//
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();


//        buttonlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logout();
//            }
//        });


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
