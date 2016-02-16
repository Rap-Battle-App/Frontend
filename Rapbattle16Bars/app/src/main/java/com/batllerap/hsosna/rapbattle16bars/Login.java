package com.batllerap.hsosna.rapbattle16bars;

import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUserName, etPassword;
    TextView tvRegisterLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);

        try {
            AuthentificationController.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                User testUser = null;
                try {
                    AuthentificationController.logout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if((etUserName.getText().toString().trim().equals("") || etUserName.getText().toString().trim().equals(null)) && (etPassword.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals(null))){
                        etUserName.setError("Bitte Benutzername eingeben.");
                        etPassword.setError("Bitte Passwort eingeben.");
                        return;
                    }

                    if((etUserName.getText().toString().trim().equals("") || etUserName.getText().toString().trim().equals(null))) {
                        etUserName.setError("Bitte Benutzername eingeben.");
                       // return;
                    } else if (etPassword.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals(null)) {
                        etPassword.setError("Bitte Passwort eingeben.");
                        return;
                    } else {
                        testUser = AuthentificationController.login(etUserName.getText().toString(), etPassword.getText().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (testUser != null) {
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("User", testUser);
                    startActivity(i);
                    return;
                } else {
                    Toast.makeText(Login.this, "Keinen Nutzer gefunden. Logindaten überprüfen!", Toast.LENGTH_SHORT).show();
                    return;
                }
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                return;
        }
    }
}