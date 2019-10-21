package com.boss.mediapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiabetesActivity extends AppCompatActivity {
    private static final String URL = "https://vast-temple-07588.herokuapp.com/";
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        et9 = findViewById(R.id.et9);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("feat1", et1.getText().toString())
                        .addFormDataPart("feat2", et2.getText().toString())
                        .addFormDataPart("feat3", et3.getText().toString())
                        .addFormDataPart("feat4", et4.getText().toString())
                        .addFormDataPart("feat5", et5.getText().toString())
                        .addFormDataPart("feat6", et6.getText().toString())
                        .addFormDataPart("feat7", et7.getText().toString())
                        .addFormDataPart("feat8", et8.getText().toString())
                        .addFormDataPart("feat9", et9.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url(URL + "disease/diabetes")
                        .post(requestBody)
                        .build();

                // Toast.makeText(SymActivity.this, "Analyzing", Toast.LENGTH_LONG).show();

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
