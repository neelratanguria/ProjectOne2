package com.estacy.neel.projectone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private Button mbuttonRegister;
    private TextView mEditTextEmail;
    private TextView mEditTextPassword;
    private TextView mEditTextName;
    private ProgressDialog mProgress;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();
        mbuttonRegister = (Button) findViewById(R.id.btnSignupSubmit);
        mEditTextEmail = (TextView) findViewById(R.id.editEmailSignup);
        mEditTextPassword = (TextView) findViewById(R.id.editPassSignup);
        mEditTextName = (TextView) findViewById(R.id.editName);






        mbuttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        final String email = mEditTextEmail.getText().toString().trim();
        final String pass = mEditTextPassword.getText().toString().trim();
        final String username = mEditTextName.getText().toString().trim();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(username)){
            Toast.makeText(Signup.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }
        else
        {
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "Registeration Successful", Toast.LENGTH_LONG).show();
                        registerUserName();
                    }
                    else {
                        Toast.makeText(Signup.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    private void registerUserName(){



        String email = mEditTextEmail.getText().toString().trim();
        String pass = mEditTextPassword.getText().toString().trim();
        final String username = mEditTextName.getText().toString().trim();
        mProgress = new ProgressDialog(this);


        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                mCurrentUser = firebaseAuth.getCurrentUser();


                mProgress.setMessage("Wait...");

                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());


                mDatabase = FirebaseDatabase.getInstance().getReference().child("Username").child(mCurrentUser.getUid());


                DatabaseReference newPost = mUserDatabase.push();

                mProgress.show();


                newPost.child("Username").setValue(username).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firebaseAuth.signOut();
                        mProgress.dismiss();
                        startActivity(new Intent(Signup.this, Launch.class));
                    }
                });

            }

        });
    }
}
