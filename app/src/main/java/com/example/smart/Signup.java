package com.example.smart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView txt = findViewById(R.id.textView3);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_home();
            }
        });

        TextView txt1 = findViewById(R.id.textView4);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changelogin();
            }
        });
    }

    private void changelogin() {
        Intent i1 = new Intent(this,MainActivity.class);
        startActivity(i1);
    }

    private void change_home() {
        final String Name;
        String Email;
        String Mobile_Number;


        EditText name = findViewById(R.id.editText6);
        EditText input1 = findViewById(R.id.editText5);
        EditText input2 = findViewById(R.id.editText4);
        EditText mob = findViewById(R.id.editText3);

        Name = name.getText().toString();
        Email = input1.getText().toString();
        Mobile_Number = mob.getText().toString();

        Map<String, Object> data = new HashMap<>();
        data.put("Name", Name);
        data.put("Email", Email);
        data.put("Mobile Number", Mobile_Number);

        mAuth.createUserWithEmailAndPassword(Email, input2.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "SUccessfully created.",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });


        db.collection("smart_users").document(Email)
                .set(data).addOnCompleteListener(
                        task ->
                        {
                            //
                        }
                ).addOnFailureListener(
                        task ->{

                        }
                );

    }
}


