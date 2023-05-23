package com.example.smart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Cart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_cart);

        TextView input3 = findViewById(R.id.textView20);
        input3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input3.getText().toString());
                String x = input3.getText().toString();
                input3.setTextColor(R.color.teal_700);
            }
        });

        TextView order = findViewById(R.id.textView21);
        order.setOnClickListener(view -> {

            String email = mAuth.getCurrentUser().getEmail();
            HashMap<String, Object> data = new HashMap<>();
            data.put("Email" , email);
            data.put("Products", "[Women Dress, Pulses, Laptop, Solid formal Footwear for Men]");
            data.put("Price","$890");

            db.collection("smart_orders").document(email)
                            .set(data).addOnCompleteListener(
                                    tsk ->{
                                        Toast.makeText(this, "Successfully Ordered!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(this,Payment.class));
                                    }
                    );
        });
    }
}
