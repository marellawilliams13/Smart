package com.example.smart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView back = findViewById(R.id.textView35);
        back.setOnClickListener(view -> {
            finishAffinity();
            Toast.makeText(Payment.this, "Thank You", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Login.class));
        });

    }
}
