package com.batllerap.hsosna.rapbattle16bars;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etEmail, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister:
                try {
                    AuthentificationController.register(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                    startActivity(new Intent(this, MainActivity.class));
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
