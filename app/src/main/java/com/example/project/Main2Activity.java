package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
