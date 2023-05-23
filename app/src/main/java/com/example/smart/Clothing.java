package com.example.smart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Clothing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        TextView input1 = findViewById(R.id.textView6);
        input1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input1.getText().toString());
                String x = input1.getText().toString();
                input1.setTextColor(R.color.teal_700);
            }
        });
        TextView input4 = findViewById(R.id.textView7);
        input4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input4.getText().toString());
                String x = input4.getText().toString();
                input4.setTextColor(R.color.teal_700);
            }
        });
        TextView input2 = findViewById(R.id.textView9);
        input2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input2.getText().toString());
                String x = input2.getText().toString();
                input2.setTextColor(R.color.teal_700);
            }
        });
        TextView input3 = findViewById(R.id.textView6);
        input3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input3.getText().toString());
                String x = input3.getText().toString();
                input3.setTextColor(R.color.teal_700);
            }
        });
        ImageView user = findViewById(R.id.imageView35);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changecart();
            }
        });
    }

    private void changecart() {
        Intent i = new Intent(this,Cart.class);
        startActivity(i);
    }

}
