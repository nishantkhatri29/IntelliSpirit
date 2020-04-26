package com.project.intellispirit.UserHomePages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.intellispirit.LoginPage.LoginActivity;
import com.project.intellispirit.R;
import com.project.intellispirit.UserProfilePages.Profile_Teacher;

public class Teacher_Activity extends AppCompatActivity {
    private Button teacherLogoutButton;
    Toolbar toolbar;
    ImageView imageView_profile;
    String teacher_id;
    WebView webView_teacher;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_);
        toolbar = findViewById(R.id.mytool);
        webView_teacher=findViewById(R.id.webview_teacher);
        imageView_profile=findViewById(R.id.profile_image);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile_Teacher.class));
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("LogIn", MODE_PRIVATE);
        teacher_id=sharedPreferences.getString("Username","");

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        webView_teacher.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }

        });
        webView_teacher.getSettings().setJavaScriptEnabled(true);
        webView_teacher.getSettings().setLoadWithOverviewMode(true);
        webView_teacher.getSettings().setUseWideViewPort(true);

        webView_teacher.getSettings().setSupportZoom(true);
        webView_teacher.getSettings().setBuiltInZoomControls(true);
        webView_teacher.getSettings().setDisplayZoomControls(false);

        webView_teacher.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView_teacher.setScrollbarFadingEnabled(false);
        webView_teacher.loadUrl("https://intellispirit0.herokuapp.com/"+teacher_id);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView_teacher.canGoBack()) {
                        webView_teacher.goBack();
                    } else {
                        finish();

                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}
