package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logout extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText etName, etAge, etUsername, etPassword;
    TestUserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new TestUserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bLogout:
                userLocalStore.clearTestUserData();
                userLocalStore.setTestUserLoggedIn(false);

                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
