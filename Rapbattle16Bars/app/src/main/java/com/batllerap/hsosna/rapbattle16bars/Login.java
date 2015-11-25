package com.batllerap.hsosna.rapbattle16bars;

import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
import com.batllerap.hsosna.rapbattle16bars.Exceptions.AuthentificationException;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUserName, etPassword;
    TextView tvRegisterLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                User testUser = null;
                try {
                    testUser = AuthentificationController.login(etUserName.getText().toString(), etPassword.getText().toString());
                } catch (AuthentificationException e) {
                    e.printStackTrace();
                }

                if (testUser != null) {
                    startActivity(new Intent(this, MainActivity.class));
                    break;
                }
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}