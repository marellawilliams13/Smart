package com.example.smart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public DocumentSnapshot data;

    public TextView loc;
    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Convert location into address
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses;
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String address = addresses.get(0).getAddressLine(0);
                loc = findViewById(R.id.textView38);
                loc.setText(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView input1 = findViewById(R.id.textView6);
        input1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input1.getText().toString());
                String x = input1.getText().toString();
                input1.setTextColor(R.color.teal_700);
                changeclothes();
            }
        });
        ImageView logout = findViewById(R.id.imageView52);
        logout.setOnClickListener(
                ta ->{
                    mAuth.signOut();
                    startActivity(new Intent(this, Login.class));
                }
        );


        TextView input4 = findViewById(R.id.textView7);
        input4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input4.getText().toString());
                String x = input4.getText().toString();
                input4.setTextColor(R.color.teal_700);
                changegrocery();
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
                changeelec();
            }
        });
        TextView input3 = findViewById(R.id.textView8);
        input3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Log.d("TAG", input3.getText().toString());
                String x = input3.getText().toString();
                input3.setTextColor(R.color.teal_700);
                changefoot();
            }
        });
        ImageView user = findViewById(R.id.imageView34);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeprofile();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        loc = findViewById(R.id.textView38);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please enable the location permision!", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    private void changeprofile() {
        Intent i5 = new Intent(this,Profile.class);
        startActivity(i5);
    }

    private void changeclothes() {
        Intent i = new Intent(this,Clothing.class);
        startActivity(i);
    }

    private void changegrocery() {
        Intent i1 = new Intent(this,Grocery.class);
        startActivity(i1);
    }

    private void changeelec() {
        Intent i2 = new Intent(this,Electronics.class);
        startActivity(i2);
    }

    private void changefoot() {
        Intent i3 = new Intent(this,Footwear.class);
        startActivity(i3);
    }
}