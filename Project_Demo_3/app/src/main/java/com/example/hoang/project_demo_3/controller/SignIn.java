package com.example.hoang.project_demo_3.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import com.example.hoang.project_demo_3.common.Common;
import com.example.hoang.project_demo_3.entity.Account;
import com.example.hoang.project_demo_3.utilities.hash.PasswordUtility;
import com.example.hoang.project_demo_3.utilities.network.ApiServices;
import com.example.hoang.project_demo_3.utilities.retrofit.ApiUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.hoang.project_demo_3.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {


    MaterialEditText editPhone, editPassword;
    Button btnSignIn;
    private ApiServices mService;

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
                final PasswordUtility passwordUtility = new PasswordUtility();
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);

                final Editable phone = editPhone.getText();
                final Editable password = editPassword.getText();

                mService = ApiUtils.getApiervice();
                mService.resForAccount(phone.toString()).enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if (response.isSuccessful()) {
                            Account getAccount = response.body();
                            Account inputAccount = new Account(phone.toString(), password.toString());

                            String salt = getAccount.getSalt();
                            String passBeforEncrypt = inputAccount.getPassword() + salt;
                            String passEncrypt = passwordUtility.md5(passBeforEncrypt);

                            if (!inputAccount.getPhone().equals(getAccount.getPhone()) || !passEncrypt.equals(getAccount.getPassword())) {
                                mDialog.setMessage("In correct information");
                                mDialog.show();
                                finish();
                            } else if (inputAccount.getPhone().equals(getAccount.getPhone()) && passEncrypt.equals(getAccount.getPassword())) {
                                mDialog.setMessage("Please waiting....");
                                mDialog.show();
                                Intent homeIntent = new Intent(SignIn.this, Home.class);
                                //set Common.currentUser = user;
                                Common.currentAccount = getAccount;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        mDialog.setMessage("Failure Connect to Server.");
                        mDialog.show();
                        finish();
                    }
                });
            }
        });
    }
}