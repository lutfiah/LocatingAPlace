package sg.edu.rp.c347.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    LatLng north,east, central, sg;
    Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sg = new LatLng(1.3521, 103.8198);
        north = new LatLng(1.437952, 103.786478);
        central = new LatLng(1.300473, 103.838356);
        east = new LatLng(1.352721, 103.944696);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sg, 11));

                Marker n = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));

                Marker c = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \nOperating hours: 11am-8pm\nTel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Marker e = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \nOperating hours: 9am-5pm\nTel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
                        marker.showInfoWindow();
                        return true;
                    }
                });

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                ui.setMyLocationButtonEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("Maps", "Access has not been granted");
                }

                if (permissionCheck != PermissionChecker.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                    return;
                }

            }
        });

        /*map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener)this){
            Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
        };*/

        btnNorth = findViewById(R.id.btn1);
        btnCentral = findViewById(R.id.btn2);
        btnEast = findViewById(R.id.btn3);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng north = new LatLng(1.437952, 103.786478);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(north, 20));
            }
            }
        });
        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng central = new LatLng(1.300473, 103.838356);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(central, 20));
                }
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng east = new LatLng(1.352721, 103.944696);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(east, 20));
                }
            }
        });
    }
}
