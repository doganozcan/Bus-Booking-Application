package com.example.finalproje.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.example.finalproje.Maps.FetchData;
import com.example.finalproje.Maps.GetNearbyPlacesData;
import com.example.finalproje.R;
import com.example.finalproje.databinding.FragmentKesfetBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class KesfetFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mMap;
    FragmentKesfetBinding binding;
    Button btnUydu;
    ZoomControls zoom;

    static final String TAG = "MapActivity";
    static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    static final float DEFAULT_ZOOM = 15f;
    Boolean mLocationPermissionsGranted = false;
    FusedLocationProviderClient mFusedLocationProviderClient;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kesfet, container, false);

        btnUydu=view.findViewById(R.id.btn_Uydu);
        zoom=view.findViewById(R.id.zoom);

        getLocationPermission();

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        btnUydu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    btnUydu.setText("Normal");
                }
                else{
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    btnUydu.setText("Uydu");
                }
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
       /* mMap=googleMap;
        LatLng pp=new LatLng(11.5448729,104.8921668);
        MarkerOptions options=new MarkerOptions();
        options.position(pp).title("DoganOzcans");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pp));*/

        Toast.makeText(getContext(), "Harita Açıldı", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: Harita Hazır");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: Cihazların Geçerli Konumunu Alma");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        try{
            if(mLocationPermissionsGranted){
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: Yer Buldu!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);

                        }else{
                            Log.d(TAG, "onComplete: Geçerli Konum Boş");
                            Toast.makeText(getContext(), "Geçerli Konum Alınamıyor", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap(){
        Log.d(TAG, "initMap: Harita Başlatılıyor");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: Konum İzinlerini Almak");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(getActivity(),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(getActivity(),
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: İzin Başarısız Oldu");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: İzin Verildi");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }
}