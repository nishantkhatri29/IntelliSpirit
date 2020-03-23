package com.project.intellispirit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button buttonlogout;
    public Button updateButton;
    public EditText newPassword;




    private static Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonlogout = findViewById(R.id.btnlogout);
        updateButton= findViewById(R.id.updateBtn);

        newPassword=findViewById(R.id.newPass);

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


        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


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
