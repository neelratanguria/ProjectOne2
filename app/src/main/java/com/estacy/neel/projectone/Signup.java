package com.estacy.neel.projectone;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private Button mbuttonRegister;
    private TextView mEditTextEmail;
    private TextView mEditTextPassword;
    private ProgressDialog mProgress;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        mbuttonRegister = (Button) findViewById(R.id.btnSignupSubmit);
        mEditTextEmail = (TextView) findViewById(R.id.editEmailSignup);
        mEditTextPassword = (TextView) findViewById(R.id.editPassSignup);

        mProgress = new ProgressDialog(this);

        mbuttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        String email = mEditTextEmail.getText().toString().trim();
        String pass = mEditTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            Toast.makeText(Signup.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }
        else
        {
            mProgress.setMessage("Please wait....");
            mProgress.show();

            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "Registeration Successful", Toast.LENGTH_LONG).show();
                    }
                }
            });
            mProgress.dismiss();
        }
    }
}
