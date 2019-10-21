package com.boss.mediapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

public class SymActivity extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sym);
        final EditText e1 = findViewById(R.id.e1);
        final EditText e2 = findViewById(R.id.e2);
        final EditText e3 = findViewById(R.id.e3);
        final EditText e4 = findViewById(R.id.e4);
        final EditText e5 = findViewById(R.id.e5);
        final EditText e6 = findViewById(R.id.e6);

        final String url = "https://cakeapi.trinitytuts.com/api/listuser";


        final Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("feat1", e1.getText().toString())
                        .addFormDataPart("feat2", e2.getText().toString())
                        .addFormDataPart("feat3", e3.getText().toString())
                        .addFormDataPart("feat4", e4.getText().toString())
                        .addFormDataPart("feat5", e5.getText().toString())
                        .addFormDataPart("feat6", e6.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url(url)
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


                View dialogView = getLayoutInflater().inflate(R.layout.dialog_layout, null, false);
                final Button btn1 = dialogView.findViewById(R.id.text1);
                final Button btn2 = dialogView.findViewById(R.id.text2);
                btn1.setText("Liver Test");
                btn2.setText("Diabetes Test");
                final AlertDialog alertDialog = new AlertDialog.Builder(SymActivity.this)
                        .setTitle("We suggest you")
                        .setCancelable(false)
                        .setView(dialogView)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SymActivity.this, LiverActivity.class);
                        startActivity(intent);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SymActivity.this, DiabetesActivity.class);
                        startActivity(intent);
                    }
                });


                alertDialog.show();

            }
        });

    }
}
