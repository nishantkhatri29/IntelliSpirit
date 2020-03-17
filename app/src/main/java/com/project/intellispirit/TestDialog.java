package com.project.intellispirit;

import android.app.Dialog;
import android.content.Context;
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

public class TestDialog extends AppCompatDialogFragment {
    private EditText setPassword;
    private EditText oldPassword;
    private EditText newPassword;
    //  private CheckBox showPassword;
    private Button setPassButton;
    private TextView setError;
    private TextView setOldError;
    private TextView setNewError;
    private ImageView showPassword;
    private ImageView showOldPassword;
    private ImageView showNewPassword;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle("Set Password");


        setPassword = view.findViewById(R.id.set_pass);
        oldPassword = view.findViewById(R.id.old_pass);
        newPassword = view.findViewById(R.id.new_pass);
        //   showPassword = view.findViewById(R.id.show_pass);
        setError = view.findViewById(R.id.set_error);
        setOldError = view.findViewById(R.id.set_error1);
        setNewError = view.findViewById(R.id.set_error2);
        showPassword = view.findViewById(R.id.show_pass);
        showOldPassword = view.findViewById(R.id.show_old_pass);
        showNewPassword = view.findViewById(R.id.show_new_pass);


        showOldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.show_old_pass){

                    if(oldPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        (( ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_visible24dp);

                        //Show Password
                        oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(v)).setImageResource(R.drawable.ic_remove_red_eye_black_24dp);

                        //Hide Password
                        oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });


        showNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.show_new_pass){

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
                if(v.getId()==R.id.show_pass){

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

        oldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    setOldError.setVisibility(View.VISIBLE);
                    setPassButton.setEnabled(false);
                } else {
                    setOldError.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                } else {
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
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();

            }
        });

//        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    setPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                } else {
//                    setPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });

        return builder.create();
    }

    public void enableButton(){
        if(!(setPassword.getText().toString().equals("") && newPassword.getText().toString().equals("")  && oldPassword.getText().toString().equals(""))){
            setPassButton.setEnabled(true);
        }
    }
}
