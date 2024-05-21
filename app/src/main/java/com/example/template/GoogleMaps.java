package com.example.template;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    boolean isPermission;
    EditText inputLocation;
    ImageView searchImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        inputLocation = findViewById(R.id.input);
        searchImageButton = findViewById(R.id.searchImageButton);
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container, supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);
        searchImageButton.setOnClickListener(v -> performSearch());
    }
    private void performSearch() {
        String location = inputLocation.getText().toString();
        if (location.isEmpty()) {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return;

        }
        Geocoder geocoder = new Geocoder(GoogleMaps.this, Locale.getDefault());
        try {
            List<Address> list = geocoder.getFromLocationName(location, 1);
            List<Address> rev = geocoder.getFromLocation(list.get(0).getLatitude(),list.get(0).getLongitude() ,1);
            if (!list.isEmpty()) {
                Address address = rev.get(0);
                Toast.makeText(this, address.getLocality() + "\n" + address.getAdminArea() + "\n" +
                        address.getCountryCode() + "\n" + address.getCountryName(), Toast.LENGTH_SHORT).show();
                Address add = list.get(0);
                LatLng latLng = new LatLng(add.getLatitude(), add.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("Search Position");
                markerOptions.position(latLng);
                gMap.addMarker(markerOptions);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                gMap.animateCamera(cameraUpdate);
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred while searching", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng location = new LatLng(9.882354052822542, 78.0814992513374);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Namma TCE")
        );
        LatLng location1 = new LatLng(9.93547259123022, 78.07933133536784);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Infinity Arena")
        );
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }
}
