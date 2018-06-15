package com.example.hoang.project_demo_3.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.example.hoang.project_demo_3.entity.Account;
import com.example.hoang.project_demo_3.utilities.hash.PasswordUtility;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.hoang.project_demo_3.R;

import org.jsoup.Jsoup;

import java.io.IOException;


public class SignIn extends AppCompatActivity {


    MaterialEditText editPhone, editPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        editPhone = (MaterialEditText) findViewById(R.id.edtPhone);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //get API in here. Or get DB in here.

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordUtility passwordUtility = new PasswordUtility();
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);

                Editable phone = editPhone.getText();
                Editable password = editPassword.getText();
                try {
                    String url = "https://daokhanh-201004.appspot.com/_api/v1/account/" + phone.toString();
                    String account = Jsoup.connect(url).ignoreContentType(true).get().body().text();
                    Account getAccount = new Gson().fromJson(account, Account.class);
                    Account inputAccount = new Account(phone.toString(),password.toString());

                    String salt = getAccount.getSalt();
                    String passBeforEncrypt = inputAccount.getPassword() + salt;
                    String passEncrypt = passwordUtility.md5(passBeforEncrypt);

                    if (!inputAccount.getPhone().equals(getAccount.getPhone()) || !passEncrypt.equals(getAccount.getPassword())){
                        mDialog.setMessage("In correct information");
                        mDialog.show();
                        finish();
                    }else if (inputAccount.getPhone().equals(getAccount.getPhone()) && passEncrypt.equals(getAccount.getPassword())) {
                        mDialog.setMessage("Please waiting....");
                        mDialog.show();
                        Intent homeIntent = new Intent(SignIn.this, Home.class);
                        //set Common.currentUser = user;
                        startActivity(homeIntent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
