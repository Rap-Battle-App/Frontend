package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etEmail, etUsername, etPassword, etPasswordConfirm;
    TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);

        bRegister = (Button) findViewById(R.id.bRegister);
        tvBackToLogin = (TextView) findViewById(R.id.tvBackToLogin);

        bRegister.setOnClickListener(this);
        tvBackToLogin.setOnClickListener(this);
    }
    
    // Password Match
    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            Toast.makeText(Register.this, "Password does not match!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister:
                User testUser = null;
                if (isPasswordMatching(etPassword.getText().toString(), etPasswordConfirm.getText().toString())) {
                    try {
                        AuthentificationController.register(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                        if (testUser != null) {
                            Intent i = new Intent(this, MainActivity.class);
                            i.putExtra("User", testUser);
                            startActivity(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tvBackToLogin:
                super.onBackPressed();
                break;
        }
    }
}
