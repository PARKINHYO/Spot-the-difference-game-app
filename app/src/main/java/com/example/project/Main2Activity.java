package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView=(TextView)findViewById(R.id.textView);
        TextView textView2=(TextView)findViewById(R.id.textView2);

        Intent main_intent = getIntent();

        int cntCrt = main_intent.getIntExtra("cntCrt",-1);
        textView.setText("맞은개수: " + cntCrt);

        int cntWrg = main_intent.getIntExtra("cntWrg",-1);
        textView2.setText("틀린개수: " + cntWrg);
    }
    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
