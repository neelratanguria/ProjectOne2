package com.estacy.neel.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void signupint(View view) {
        startActivity(new Intent(Launch.this ,Signup.class));
    }

    public void signinint(View view) {
        startActivity(new Intent(Launch.this, MainActivity.class));
    }
}
