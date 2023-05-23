package com.example.smart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Profile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView btn = findViewById(R.id.textView17);
        btn.setOnClickListener(view -> {
            change_edit();
        });

    }

    private void change_edit() {
        Intent intent = new Intent(this, Editprofile.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView uname = findViewById(R.id.textView32);
        TextView uemail = findViewById(R.id.textView36);
        TextView unum = findViewById(R.id.textView37);

        String user_email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
//        assert user_email != null;
        db.collection("smart_users").document(user_email)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String Name = document.getString("Name");
                            uname.setText(Name);
                            String Email = document.getString("Email");
                            uemail.setText(Email);
                            String Address = document.getString("Mobile Number");
                            unum.setText(Address);
                        } else {

                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });

    }

}

