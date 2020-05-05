package com.project.intellispirit.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.intellispirit.Apis.URLs;
import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserHomePages.Admin_Activity;
import com.project.intellispirit.UserHomePages.MainActivity;
import com.project.intellispirit.UserHomePages.Principal_Activity;
import com.project.intellispirit.UserHomePages.Teacher_Activity;
import com.project.intellispirit.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class TestDialog extends AppCompatDialogFragment {
    private EditText setPassword;
    //private EditText oldPassword;
    private EditText newPassword;
    //  private CheckBox showPassword;
    private Button setPassButton;
    private TextView setError;
    //private TextView setOldError;
    private TextView setNewError;
    private ImageView showPassword;
    //private ImageView showOldPassword;
    private ImageView showNewPassword;
    //private TestDialogListener listener;
    private boolean flag =false;
    private String username,password,dob,jwt_token;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
       final String  status =bundle.getString("status","");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle("Set Password");


        setPassword = view.findViewById(R.id.set_pass);
        newPassword = view.findViewById(R.id.new_pass);
        setError = view.findViewById(R.id.set_error);
        setNewError = view.findViewById(R.id.set_error2);
        showPassword = view.findViewById(R.id.show_pass);
        showNewPassword = view.findViewById(R.id.show_new_pass);



        showNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.show_new_pass){

                    if(newPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        (( ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_visible24dp);

                        //Show Password
                        newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_24dp);

                        //Hide Password
                        newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.show_pass){

                    if(setPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        (( ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_visible24dp);

                        //Show Password
                        setPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_24dp);

                        //Hide Password
                        setPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });



        setPassButton = view.findViewById(R.id.set_pass_button);


        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    setNewError.setVisibility(View.VISIBLE);
                    setPassButton.setEnabled(false);
                } else {
                    setNewError.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    setError.setVisibility(View.VISIBLE);
                    setPassButton.setEnabled(false);
                }

                else if(!s.toString().equals(newPassword.getText().toString())){
                    setError.setText("New Password and Confirm Password doesn't match!");
                    setPassButton.setEnabled(false);
                }
                else {
                    setError.setVisibility(View.GONE);

                    enableButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status == "student") {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);

                     username = sharedPreferences.getString("Username", "");
                     dob = sharedPreferences.getString("DOB", "");
                     jwt_token=sharedPreferences.getString("Token","");

                     password = setPassword.getText().toString().trim();



                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_UPDATESTUDENTPASSWORD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){
                                    Intent intent=new Intent(getActivity(),MainActivity.class);
startActivity(intent);
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            super.getParams();
                            Map<String,String> params=new HashMap<>();
                            params.put("student_id",username);
                            params.put("password",password);
                            params.put("DOB",dob);
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            super.getHeaders();
                            Map<String,String> headers=new HashMap<>();
                            headers.put("Content-Type","application/x-www-form-urlencoded");
                            headers.put("Authorization","Bearer "+jwt_token);
                            return headers;
                        }
                    };
                    requestQueue.add(stringRequest);
                }

                else if(status=="admin"){

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);

                    username = sharedPreferences.getString("Username", "");
                    dob = sharedPreferences.getString("DOB", "");
                    jwt_token=sharedPreferences.getString("Token","");

                    password = setPassword.getText().toString().trim();
                    Toast.makeText(getActivity(), ""+jwt_token, Toast.LENGTH_SHORT).show();


                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_UPDATEADMINPASSWORD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){
                                    Intent intent=new Intent(getActivity(),Admin_Activity.class);
startActivity(intent);


                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            super.getParams();
                            Map<String,String> params=new HashMap<>();
                            params.put("admin_id",username);
                            params.put("password",password);
                            params.put("DOB",dob);
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            super.getHeaders();
                            Map<String,String> headers=new HashMap<>();
                            headers.put("Content-Type","application/x-www-form-urlencoded");
                            headers.put("Authorization","Bearer "+jwt_token);
                            return headers;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else if(status=="teacher"){

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);

                    username = sharedPreferences.getString("Username", "");
                    dob = sharedPreferences.getString("DOB", "");
                    jwt_token=sharedPreferences.getString("Token","");

                    password = setPassword.getText().toString().trim();



                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_UPDATETEACHERPASSWORD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){
                                    Intent intent=new Intent(getActivity(),Teacher_Activity.class);
startActivity(intent);
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            super.getParams();
                            Map<String,String> params=new HashMap<>();
                            params.put("teacher_id",username);
                            params.put("password",password);
                            params.put("DOB",dob);
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            super.getHeaders();
                            Map<String,String> headers=new HashMap<>();
                            headers.put("Content-Type","application/x-www-form-urlencoded");
                            headers.put("Authorization","Bearer "+jwt_token);
                            return headers;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
                else if(status=="principal"){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);

                    username = sharedPreferences.getString("Username", "");
                    dob = sharedPreferences.getString("DOB", "");
                    jwt_token=sharedPreferences.getString("Token","");

                    password = setPassword.getText().toString().trim();


                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_UPDATEPRINCIPALPASSWORD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){
                                    Intent intent=new Intent(getActivity(),Principal_Activity.class);
startActivity(intent);
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            super.getParams();
                            Map<String,String> params=new HashMap<>();
                            params.put("principal_id",username);
                            params.put("password",password);
                            params.put("DOB",dob);
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            super.getHeaders();
                            Map<String,String> headers=new HashMap<>();
                            headers.put("Content-Type","application/x-www-form-urlencoded");
                            headers.put("Authorization","Bearer "+jwt_token);
                            return headers;
                        }
                    };
                    requestQueue.add(stringRequest);
                }

            }
        });

        return builder.create();
    }

    public void enableButton(){
        if(!(setPassword.getText().toString().equals("") && newPassword.getText().toString().equals("") )){

            if(newPassword.getText().toString().equals(setPassword.getText().toString())){
                setPassButton.setEnabled(true);
            }


        }
    }

    public interface TestDialogListener{

        public void finishActivityAlert(boolean flag);

    }

    public interface DataPass {
        public void DataPass(int flag);
    }


}
