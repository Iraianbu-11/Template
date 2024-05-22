package com.example.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;

public class location extends AppCompatActivity implements LocationListener {
    Button locationClass , gmap;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationClass = findViewById(R.id.button_location);
        textView = findViewById(R.id.text_location);
        locationClass.setOnClickListener(v->{
            getUserLocation();
        });
        gmap = findViewById(R.id.button_gmap);
        gmap.setOnClickListener(v->{
            startActivity(new Intent(this,GoogleMaps.class));
        });
    }

    private void getUserLocation() {
        try {
            LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                },100)  ;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
       // Toast.makeText(this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
        double latitude = location.getLatitude() ,longtitude = location.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> address = geocoder.getFromLocation(latitude,longtitude,1);
            String add = address.get(0).getAddressLine(0);
            textView.setText("User's Current Location: " + add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}