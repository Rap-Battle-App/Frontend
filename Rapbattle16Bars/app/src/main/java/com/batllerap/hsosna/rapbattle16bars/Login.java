package com.batllerap.hsosna.rapbattle16bars;

import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

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

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (testUser != null) {
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("User", testUser);
                    startActivity(i);
                    break;
                } else {
                    break;
                }
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}