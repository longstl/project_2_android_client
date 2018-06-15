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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hoang.project_demo_3.entity.Account;
import com.example.hoang.project_demo_3.utilities.hash.PasswordUtility;
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
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    private Account account;
    PasswordUtility passwordUtility = new PasswordUtility();

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
        radioGenderGroup = (RadioGroup) findViewById(R.id.groupGender);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        // Code get SQL or call API in here

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog1 = new ProgressDialog(SignUp.this);
                Editable fullname = edtName.getText();
                Editable password = edtPassword.getText();
                Editable password2 = edtPassword2.getText();
                Editable phone = edtPhone.getText();
                Editable email = edtEmail.getText();
                // vailidate password trung nhau ng dung nhap vao.
//                if (!password.equals(password2)) {
////                    mDialog1.setMessage("Password do not match");
////                    mDialog1.show();
////                    finish();
////                    return;
////                }

                int selectedId = radioGenderGroup.getCheckedRadioButtonId();
                String radioGenderButton = ((RadioButton) findViewById(selectedId)).getText().toString();

                String salt = passwordUtility.getSalt(10);
                String passBeforeEncrypt = password + salt;
                String mySecurePassword = passwordUtility.md5(passBeforeEncrypt);

                try {
                    Account account = new Account(phone.toString(), mySecurePassword, salt.toString(), fullname.toString(), email.toString(), radioGenderButton.toString());
                    Jsoup.connect("https://daokhanh-201004.appspot.com/_api/v1/account/signup/")
                            .ignoreContentType(true)
                            .header("Content-Type", "application/json")
                            .method(Connection.Method.POST)
                            .requestBody(new Gson().toJson(account))
                            .execute();
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
