package com.project.intellispirit.LoginPage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.project.intellispirit.Apis.URLs;
import com.project.intellispirit.R;
import com.project.intellispirit.Adapters.TestDialog;
import com.project.intellispirit.UserHomePages.Admin_Activity;
import com.project.intellispirit.UserHomePages.MainActivity;
import com.project.intellispirit.UserHomePages.Principal_Activity;
import com.project.intellispirit.UserHomePages.Teacher_Activity;
import com.project.intellispirit.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etName, etPassword, etDOB;

    public static final String SHARED_PREFS = "alertDialogPrefs1";
    public static final String TEXT= "dialogStatus1";
    private ImageView show_hide_password;
    final TestDialog testDialog = new TestDialog();


    Spinner spinner;
    String usertype;
    private SwipeButton buttonlogin;
    private ProgressDialog pDialog;
    private String username;
    private String password;
    private String DOB;
    private String StudentName,StudentClass,StudentSection,StudentSchool;
    private String TeacherName,TeacherClass,TeacherSection,TeacherSchool,TeacherSubject;
    private String PrincipalName,PrincipalSchool;
    private String Adminzoneid,Admindistrictid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //   getSupportActionBar().hide();


        SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
        boolean student_logged = sharedPreferences.getBoolean("isStudentLogIn", false);//true
        boolean teacher_logged = sharedPreferences.getBoolean("isTeacherLogIn", false);//true
        boolean principal_logged = sharedPreferences.getBoolean("isPrincipalLogIn", false);//true
        boolean admin_logged = sharedPreferences.getBoolean("isAdminLogIn", false);//true
        if (student_logged) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        if (teacher_logged) {
            startActivity(new Intent(LoginActivity.this, Teacher_Activity.class));
        }
        if (principal_logged) {
            startActivity(new Intent(LoginActivity.this, Principal_Activity.class));
        }
        if (admin_logged) {
            startActivity(new Intent(LoginActivity.this, Admin_Activity.class));
        }

        show_hide_password = findViewById(R.id.show_hide_pass);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        etName = findViewById(R.id.etUserName);
        etDOB =findViewById(R.id.etUserDOB);
        etPassword = findViewById(R.id.etUserPassword);
        buttonlogin = findViewById(R.id.btnLogin);
        spinner = findViewById(R.id.spinner_users);
        spinner.setOnItemSelectedListener(this);




        show_hide_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.show_hide_pass) {

                    if (etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((ImageView) (v)).setImageResource(R.drawable.ic_remove_red_eye_black_visible24dp);

                        //Show Password
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (v)).setImageResource(R.drawable.ic_remove_red_eye_black_24dp);

                        //Hide Password
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });


        buttonlogin.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                username = etName.getText().toString().trim();
                DOB=etDOB.getText().toString().trim();
                password = etPassword.getText().toString().trim();


                if (TextUtils.isEmpty(username)) {
                    etName.setError("Please enter your username");
                    etName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(DOB)){
                    etDOB.setError("Please enter DOB");
                    etDOB.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Please enter your password");
                    etPassword.requestFocus();
                    return;
                }
                switch (usertype){
                    case "Who are you?":
                        Toast.makeText(LoginActivity.this, "Select your role!!", Toast.LENGTH_SHORT).show();
                        break;
                    case "A Student":
                        studentLogin();
                        break;
                    case "A Teacher":
                        teacherlogin();
                        break;
                    case "A Principal":
                        principallogin();
                        break;
                    case "Admin":
                        adminlogin();
                        break;
                    case "Super Admin":
                        Toast.makeText(LoginActivity.this, "Check back later", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    private void adminlogin() {

        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ADMINLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
JSONObject obj = new JSONObject(response);

if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

JSONObject userJson = obj.getJSONObject("user");

    SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean("isAdminLogIn", true);
    editor.putString("Username",username);
    editor.putString("DOB",DOB);
    editor.putString("Password",password);
    Adminzoneid=userJson.getString("zone_id");
    Admindistrictid=userJson.getString("district_id");
    editor.putString("zoneid",Adminzoneid);
    editor.putString("districtid",Admindistrictid);

    editor.commit();
    editor.apply();

                                testDialog.setCancelable(false);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                SharedPreferences dialogPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                boolean firstStart = dialogPreferences1.getBoolean(TEXT, true);

                                if(firstStart) {
                                    builder.setTitle("Do you want to set your password?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("status","admin");
                                                    testDialog.setArguments(bundle);
                                                    testDialog.show(getSupportFragmentManager(), "test dialog");



                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(LoginActivity.this, "No", Toast.LENGTH_SHORT).show();
                                                    builder.setCancelable(true);
                                                    Intent intent = new Intent(LoginActivity.this, Admin_Activity.class);
                                                    startActivity(intent);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);


                                                    finish();
                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                    SharedPreferences.Editor dialogEditor1 = dialogPreferences1.edit();
                                    dialogEditor1.putBoolean(TEXT, false);
                                    dialogEditor1.apply();


                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, Admin_Activity.class);
                                    startActivity(intent);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                    finish();
                                }


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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("admin_id", username);
                params.put("DOB",DOB);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void principallogin() {

        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_PRINCIPALLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
JSONObject obj = new JSONObject(response);

if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

JSONObject userJson = obj.getJSONObject("user");
    SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean("isPrincipalLogIn", true);
    editor.putString("Username",username);
    editor.putString("DOB",DOB);
    editor.putString("Password",password);
    PrincipalName=userJson.getString("name");
    PrincipalSchool=userJson.getString("school");
    editor.putString("name",PrincipalName);
    editor.putString("school",PrincipalSchool);

    editor.commit();
    editor.apply();



    testDialog.setCancelable(false);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                SharedPreferences dialogPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                boolean firstStart = dialogPreferences1.getBoolean(TEXT, true);

                                if(firstStart) {
                                    builder.setTitle("Do you want to set your password?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("status","principal");
                                                    testDialog.setArguments(bundle);
                                                    testDialog.show(getSupportFragmentManager(), "test dialog");
}
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(LoginActivity.this, "No", Toast.LENGTH_SHORT).show();
                                                    builder.setCancelable(true);
                                                    Intent intent = new Intent(LoginActivity.this, Principal_Activity.class);
                                                    startActivity(intent);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                                    finish();
                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                    SharedPreferences.Editor dialogEditor1 = dialogPreferences1.edit();
                                    dialogEditor1.putBoolean(TEXT, false);
                                    dialogEditor1.apply();


                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, Principal_Activity.class);
                                    startActivity(intent);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                    finish();
                                }


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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("principal_id", username);
                params.put("DOB",DOB);
                params.put("password", password);
                return params;

            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



    private void teacherlogin() {

        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_TEACHERLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
JSONObject obj = new JSONObject(response);

if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

JSONObject userJson = obj.getJSONObject("user");

    SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean("isTeacherLogIn", true);
    editor.putString("Username",username);
    editor.putString("DOB",DOB);
    editor.putString("Password",password);

    TeacherName=userJson.getString("name");
    TeacherClass = userJson.getString("class");
    TeacherSection=userJson.getString("section");
    TeacherSchool=userJson.getString("school");
    TeacherSubject=userJson.getString("subject");
                              editor.putString("name",TeacherName);
                                editor.putString("class",TeacherClass);
                                editor.putString("section",TeacherSection);
                                editor.putString("school",TeacherSchool);
                                editor.putString("subject",TeacherSubject);

    editor.commit();
    editor.apply();


                                testDialog.setCancelable(false);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                SharedPreferences dialogPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                boolean firstStart = dialogPreferences1.getBoolean(TEXT, true);

                                if(firstStart) {
                                    builder.setTitle("Do you want to set your password?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("status","teacher");
                                                    testDialog.setArguments(bundle);
                                                    testDialog.show(getSupportFragmentManager(), "test dialog");
}
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(LoginActivity.this, "No", Toast.LENGTH_SHORT).show();
                                                    builder.setCancelable(true);
                                                    Intent intent = new Intent(LoginActivity.this, Teacher_Activity.class);
                                                    startActivity(intent);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                                    finish();

                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                    SharedPreferences.Editor dialogEditor1 = dialogPreferences1.edit();
                                    dialogEditor1.putBoolean(TEXT, false);
                                    dialogEditor1.apply();


                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, Teacher_Activity.class);
                                    startActivity(intent);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                    finish();

                                }


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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("teacher_id", username);
                params.put("DOB",DOB);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void studentLogin() {



        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_STUDENTLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            JSONObject obj = new JSONObject(response);

                                if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                JSONObject userJson = obj.getJSONObject("user");



                                SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isStudentLogIn", true);
                                editor.putString("Username",username);
                                editor.putString("DOB",DOB);
                                editor.putString("Password",password);

                                 StudentName=userJson.getString("name");
                                 StudentClass = userJson.getString("class");
                                 StudentSection=userJson.getString("section");
                                 StudentSchool=userJson.getString("school");
                                 saveDataStudent();
//                                editor.putString("name",StudentName);
//                                editor.putString("class",StudentClass);
//                                editor.putString("section",StudentSection);
//                                editor.putString("school",StudentSchool);

                                editor.commit();
                                editor.apply();

                                testDialog.setCancelable(false);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                SharedPreferences dialogPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                boolean firstStart = dialogPreferences1.getBoolean(TEXT, true);

                                if(firstStart) {
                                    builder.setTitle("Do you want to set your password?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("status","student");
                                                    testDialog.setArguments(bundle);
                                                    testDialog.show(getSupportFragmentManager(), "test dialog");


                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(LoginActivity.this, "No", Toast.LENGTH_SHORT).show();
                                                    builder.setCancelable(true);
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                                    finish();
                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                    SharedPreferences.Editor dialogEditor1 = dialogPreferences1.edit();
                                    dialogEditor1.putBoolean(TEXT, false);
                                    dialogEditor1.apply();


                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                                    finish();
                                }


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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("student_id", username);
                params.put("DOB",DOB);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        usertype=spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveDataStudent(){

        SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",StudentName);
        editor.putString("class",StudentClass);
        editor.putString("section",StudentSection);
        editor.putString("school",StudentSchool);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}


