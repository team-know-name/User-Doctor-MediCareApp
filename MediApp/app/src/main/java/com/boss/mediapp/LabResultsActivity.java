package com.boss.mediapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LabResultsActivity extends AppCompatActivity {
    private static final String URL = "";
    EditText et1, et2, et3, et4, et5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labresults);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        //get the spinner from the xml.
        final Spinner dropdown = findViewById(R.id.spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"PATIENT INFORMATIONDOCTOR INFORMATION\n" +
                "BLOOD GLUCOSE R + CHOLESTEROL TOTAL", "Swasthfit Heart Check - Advance Health Checkup",
                "ANTI SOLUBLE LIVER ANTIGEN; SLA",
                "ENHANCED LIVER FIBROSIS (ELF) PANEL",
                "DIABETES PANEL 1"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("", dropdown.getSelectedItem().toString())
                        .addFormDataPart("feat1", et1.getText().toString())
                        .addFormDataPart("feat2", et2.getText().toString())
                        .addFormDataPart("feat3", et3.getText().toString())
                        .addFormDataPart("feat4", et4.getText().toString())
                        .addFormDataPart("feat5", et5.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url(URL)
                        .post(requestBody)
                        .build();

                Toast.makeText(LabResultsActivity.this, "Analyzing", Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String mMessage = e.getMessage().toString();
                        Log.e("Failure Response", mMessage);
                        //call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String mMessage = response.body().string();
                        Log.e("Output", mMessage);
                    }
                });
            }
        });
    }
}
