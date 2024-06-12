package com.project.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    TextInputEditText input_nama;
    Button btn_submit;
    TextView text_view_nama;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputLayout = findViewById(R.id.textInputLayout);
        input_nama = findViewById(R.id.inputNama);
        btn_submit = findViewById(R.id.button);
        text_view_nama = findViewById(R.id.textViewPlaceholder);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String nama = input_nama.getText().toString().trim();
                if (!nama.isEmpty()) {
                    text_view_nama.setText("Halo " + nama + "!");
                    text_view_nama.setVisibility(View.VISIBLE);
                }
                else {
                    input_nama.setError("Masukkan nama terlebih dahulu");
                }
            }
        });
    }
}