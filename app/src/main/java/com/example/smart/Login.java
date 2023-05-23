package com.example.smart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView login = findViewById(R.id.textView2);
        EditText email = findViewById(R.id.editText2);
        EditText password = findViewById(R.id.editText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText() != null ) {
                    changemain(email.getText().toString(), password.getText().toString());
                }
                else{
                    Toast.makeText(Login.this, "Please input Valid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        TextView signup = findViewById(R.id.textView);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changesign();
            }
        });

    }
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void changesign() {
        Intent i1 = new Intent(this,Signup.class);
        startActivity(i1);
    }

    private void changemain(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                                   });

    }

}
