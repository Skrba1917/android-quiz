package com.example.androidquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText etEmail, etPassword;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.email1);
        etPassword = (EditText) findViewById(R.id.password1);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.register) {
            startActivity(new Intent(this, RegisterActivity.class));
        } else if (id == R.id.signIn) {
        }
    }

}