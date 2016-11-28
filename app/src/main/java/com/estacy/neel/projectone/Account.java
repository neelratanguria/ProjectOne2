package com.estacy.neel.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account extends AppCompatActivity {

    private static Button button_sbm;

    private static Button button_aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        OnclickButtonListener();
    }

    public void OnclickButtonListener(){
        button_sbm = (Button) findViewById(R.id.button_brs_imgup);
        button_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentaaa = new Intent("com.estacy.neel.projectone.ImageUpload");
                startActivity(intentaaa);
            }
        });

        button_aa = (Button) findViewById(R.id.button_aa);
        button_aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_aa =new Intent("com.estacy.neel.projectone.Blog");
                startActivity(intent_aa);
            }
        });
    }
}
