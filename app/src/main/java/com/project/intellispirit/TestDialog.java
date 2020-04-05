package com.project.intellispirit;

import android.app.ActivityOptions;
import android.app.Dialog;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//////////////////////////////////////////////////
        Bundle bundle = getArguments();
        final String status =bundle.getString("status","");

        /////////////////////////////////////////


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle("Set Password");

//        builder.setCancelable(false);

        setPassword = view.findViewById(R.id.set_pass);
        //oldPassword = view.findViewById(R.id.old_pass);
        newPassword = view.findViewById(R.id.new_pass);
        //   showPassword = view.findViewById(R.id.show_pass);
        setError = view.findViewById(R.id.set_error);
        //  setOldError = view.findViewById(R.id.set_error1);
        setNewError = view.findViewById(R.id.set_error2);
        showPassword = view.findViewById(R.id.show_pass);
        // showOldPassword = view.findViewById(R.id.show_old_pass);
        showNewPassword = view.findViewById(R.id.show_new_pass);


//        showOldPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.getId()==R.id.show_old_pass){
//
//                    if(oldPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                        (( ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_visible24dp);
//
//                        //Show Password
//                        oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    }
//                    else{
//                        ((ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_24dp);
//
//                        //Hide Password
//                        oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//
//                    }
//                }
//            }
//        });


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

//        oldPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().equals("")) {
//                    setOldError.setVisibility(View.VISIBLE);
//                    setPassButton.setEnabled(false);
//                } else {
//                    setOldError.setVisibility(View.GONE);
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

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
//                    setPassButton.setEnabled(true);
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
//                    setPassButton.setEnabled(true);
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
//                SharedPreferences preferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putBoolean("firstTime", false);
//                editor.apply();
//                getDialog().dismiss();
//                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                ///////////////////////////////////////////////////////////////////////////////
                if (status == "student") {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();

                    final String username = sharedPreferences.getString("Username", "");
                    final String dob = sharedPreferences.getString("DOB", "");

                    final String password = setPassword.getText().toString().trim();
                    Toast.makeText(getActivity(), password, Toast.LENGTH_LONG).show();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATESTUDENTPASSWORD,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        //converting response to json object
                                        JSONObject obj = new JSONObject(response);

                                        //if no error in response
                                        if (!obj.getBoolean("error")) {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                            //getting the user from the response
                                            JSONObject userJson = obj.getJSONObject("user");
                                        } else {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("student_id", username);
                            params.put("DOB",dob);
                            params.put("password", password);
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    editor.putString("Password", password);
                    editor.commit();
                    editor.apply();

//              ///////////////////////////////////////////////////////////////////////////////

//                try{}
                    dismiss();

//                flag=true;
////                setCancelable(true);
//                listener.finishActivityAlert(flag);

                    Intent intent = new Intent((LoginActivity) getActivity(), MainActivity.class);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.zoomin, R.anim.zoomout).toBundle();

                    startActivity(intent,bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

//                dismiss();

//                catch (Exception e){
//
//                   Log.e("ERROR",String.valueOf(e)) ;
//                }

//                getDialog().dismiss();
//                getActivity().finish();


                }

                else if(status=="admin"){

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();

                    final String username = sharedPreferences.getString("Username", "");
                    final String dob = sharedPreferences.getString("DOB", "");

                    final String password = setPassword.getText().toString().trim();
                    Toast.makeText(getActivity(), password, Toast.LENGTH_LONG).show();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATEADMINPASSWORD,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        //converting response to json object
                                        JSONObject obj = new JSONObject(response);

                                        //if no error in response
                                        if (!obj.getBoolean("error")) {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                            //getting the user from the response
                                            JSONObject userJson = obj.getJSONObject("user");
                                        } else {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("admin_id", username);
                            params.put("DOB",dob);
                            params.put("password", password);
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    editor.putString("Password", password);
                    editor.commit();
                    editor.apply();

//              ///////////////////////////////////////////////////////////////////////////////

//                try{}
                    dismiss();

//                flag=true;
////                setCancelable(true);
//                listener.finishActivityAlert(flag);

                    Intent intent = new Intent((LoginActivity) getActivity(), Admin_Activity.class);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.zoomin, R.anim.zoomout).toBundle();

                    startActivity(intent,bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                }
                else if(status=="teacher"){

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();

                    final String username = sharedPreferences.getString("Username", "");
                    final String dob = sharedPreferences.getString("DOB", "");

                    final String password = setPassword.getText().toString().trim();
                    Toast.makeText(getActivity(), password, Toast.LENGTH_LONG).show();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATETEACHERPASSWORD,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        //converting response to json object
                                        JSONObject obj = new JSONObject(response);

                                        //if no error in response
                                        if (!obj.getBoolean("error")) {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                            //getting the user from the response
                                            JSONObject userJson = obj.getJSONObject("user");
                                        } else {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("teacher_id", username);
                            params.put("DOB",dob);
                            params.put("password", password);
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    editor.putString("Password", password);
                    editor.commit();
                    editor.apply();

//              ///////////////////////////////////////////////////////////////////////////////

//                try{}
                    dismiss();

                    Intent intent = new Intent((LoginActivity) getActivity(), Teacher_Activity.class);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.zoomin, R.anim.zoomout).toBundle();

                    startActivity(intent,bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

                }
                else if(status=="principal"){

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LogIn", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();

                    final String username = sharedPreferences.getString("Username", "");
                    final String dob = sharedPreferences.getString("DOB", "");

                    final String password = setPassword.getText().toString().trim();
                    Toast.makeText(getActivity(), password, Toast.LENGTH_LONG).show();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATEPRINCIPALPASSWORD,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        //converting response to json object
                                        JSONObject obj = new JSONObject(response);

                                        //if no error in response
                                        if (!obj.getBoolean("error")) {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                            //getting the user from the response
                                            JSONObject userJson = obj.getJSONObject("user");
                                        } else {
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("principal_id", username);
                            params.put("DOB",dob);
                            params.put("password", password);
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    editor.putString("Password", password);
                    editor.commit();
                    editor.apply();

//              ///////////////////////////////////////////////////////////////////////////////

//                try{}
                    dismiss();

                    Intent intent = new Intent((LoginActivity) getActivity(), Principal_Activity.class);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.zoomin, R.anim.zoomout).toBundle();

                    startActivity(intent,bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

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

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (TestDialogListener) context;
//        } catch (ClassCastException e) {
//            throw  new ClassCastException(context.toString() + "must implement TestDialogListener");
//        }
//    }
}
