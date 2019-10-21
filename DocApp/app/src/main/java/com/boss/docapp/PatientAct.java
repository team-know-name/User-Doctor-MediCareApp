package com.boss.docapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PatientAct extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_act);
        TextView textView = findViewById(R.id.tv);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Antonio-Regular.ttf");
        textView.setText("You are viewing  " + getIntent().getStringExtra("name") + " report.");


    }
}
