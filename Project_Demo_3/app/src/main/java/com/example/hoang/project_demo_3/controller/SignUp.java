package com.example.hoang.project_demo_3.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hoang.project_demo_3.entity.Account;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;

import com.example.hoang.project_demo_3.R;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtName, edtPhone, edtEmail, edtPassword, edtPassword2;
    Button btnSignUp;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPassword2 = (MaterialEditText) findViewById(R.id.edtPassword2);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        // Code get SQL or call API in here

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable fullname = edtName.getText();
                Editable password = edtPassword.getText();
                Editable password2 = edtPassword2.getText();
                Editable phone = edtPhone.getText();
                Editable email = edtEmail.getText();
                // vailidate du lieu ng dung nhap vao.
//                if (!password.equals(password2)) {
//                    // in ra loi 2 mk ko trung nhau.
//                    return;
//                }
                // code in here.
                try {
//                    URL url = new URL("https://daokhanh-201004.appspot.com/_api/v1/users/signup/");
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestMethod("POST");
//                    con.addRequestProperty("User-Agent", "Mozilla/5.0");
//                    con.addRequestProperty("Content-Type", "application/json");
//
//                    con.setDoOutput(true);
//                    OutputStream os = con.getOutputStream();
//                    os.write(new Gson().toJson(account, Account.class).getBytes());
//                    os.flush();
//                    os.close();
//
//                    int responseCode = con.getResponseCode();
//                    // cho nay sua tren api la tra ve code 201 nhe.
//                    if (responseCode == 200) {
//                        // show dang ki  thanh cong
//                    } else {
//                        // show dang ki ko thanh cong
//                    }
//                    // done.
                    Account ac= new Account(phone.toString(),password.toString(), fullname.toString(),email.toString());
                    Jsoup.connect("https://daokhanh-201004.appspot.com/_api/v1/account/signup/")
                            .ignoreContentType(true)
                            .header("Content-Type","application/json")
                            .method(Connection.Method.POST)
                            .requestBody(new Gson().toJson(ac))
                            .execute();

                    final ProgressDialog mDialog1 = new ProgressDialog(SignUp.this);
                    mDialog1.setMessage("Please waiting....");
                    mDialog1.show();

                    Intent mainIntent = new Intent(SignUp.this, SignIn.class);
                    startActivity(mainIntent);
                    finish();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
