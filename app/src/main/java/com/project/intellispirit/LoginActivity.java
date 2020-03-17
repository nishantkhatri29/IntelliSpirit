package com.project.intellispirit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginActivity extends AppCompatActivity {
    EditText etName, etPassword;

    private Button buttonlogin;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etUserPassword);
        buttonlogin = findViewById(R.id.btnLogin);





        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }


        });
    }

    private void userLogin() {
        final String username = etName.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            etName.setError("Please enter your username");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
//                                    User user = new User(
//                                            userJson.getInt("id"),
//                                            userJson.getString("username"),
//                                            userJson.getString("email"),
//                                            userJson.getString("gender")
//                                    );

                                //storing the user in shared preferences
//                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the profile activity
                                SaveSharedPreference.setLoggedIn(getApplicationContext(), true);

                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("student_id", username);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}



//            RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
//            final String u_id=ed_uname.getText().toString();
//            final String u_pass=ed_password.getText().toString();
//
//            StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://intellispirit.000webhostapp.com/login.php", new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
////                        logged=session.isLoggedIn();
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//
//
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(LoginActivity.this, ""+error, Toast.LENGTH_SHORT).show();
//
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    super.getParams();
//
//                    Map<String,String> params=new HashMap<>();
//                    params.put("user_id",u_id);
//                    params.put("user_name",u_pass);
//                    return params;
//                }
//            };
//                requestQueue.add(stringRequest);
//        }
//
//    });
//}