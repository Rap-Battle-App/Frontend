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
    EditText etEmail, etUsername, etPassword, etPasswordConfirm;
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

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Toast.makeText(Register.this, "Email-Format nicht korrekt (abzABZ@xyz.de).", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    
    // Password Match
    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            Toast.makeText(Register.this, "Passwörter stimmen nicht überein.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean eingabenKorrekt() {

        if ((etUsername.getText().toString().trim().equals("") || etUsername.getText().toString().trim().equals(null))) {
            etUsername.setError("Bitte Benutzername eingeben.");
            return false;
        }

        if ((etEmail.getText().toString().trim().equals("") || etEmail.getText().toString().trim().equals(null))) {
            etEmail.setError("Bitte Email eingeben.");
            return false;
        }

        if ((etPassword.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals(null))) {
            etPassword.setError("Bitte Passwort eingeben.");
            return false;
        }

        if ((etPasswordConfirm.getText().toString().trim().equals("") || etPasswordConfirm.getText().toString().trim().equals(null))) {
            etPasswordConfirm.setError("Bitte Passwort-Bestätigung eingeben.");
            return false;
        }

        return true;
    }

    public boolean passwortLaengeOkay() {
        if (etPassword.length() < 6 ) {
            Toast.makeText(Register.this, "Bitte ein Passwort von mindestens 6 Zeichen eingeben.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister:
                User testUser = null;

                try {
                    if(eingabenKorrekt() && passwortLaengeOkay() && isValidEmail(etEmail.getText().toString())) {
                        if (isPasswordMatching(etPassword.getText().toString(), etPasswordConfirm.getText().toString())) {
                            testUser = AuthentificationController.register(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                            if (testUser != null) {
                                Intent i = new Intent(this, MainActivity.class);
                                i.putExtra("User", testUser);
                                startActivity(i);
                            } else {
                                Toast.makeText(Register.this, "Nutzername schon vorhanden, bitte anderen Namen wählen!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvBackToLogin:
                super.onBackPressed();
                break;
        }
    }
}
