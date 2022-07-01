package com.example.gmaps_10119271;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class SecondFragment extends Fragment {
    //    NIM : 10119271
    //    Nama : Ikra Esa A'raaf Mahardika
    //    Kelas : IF 7
    private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;
    private final LatLng SAUNG_KURING = new LatLng(-6.887502279667126, 107.62365483238983);
    private final LatLng LOTEK_SALOPA = new LatLng(-6.888296502356878, 107.62499945192724);
    private final LatLng WARUNG_CELUP = new LatLng(-6.885928141690694, 107.62319692290953);
    private final LatLng AYAM_GEPREK = new LatLng(-6.888542586231891, 107.6245020234544);
    private final LatLng SEA_FOOD = new LatLng(-6.889792765189953, 107.62680478725193);

    private Location location;
    private Marker markerSK;
    private Marker markerLS;
    private Marker markerWC;
    private Marker markerAG;
    private Marker markerSF;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Saat Ini");
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
            googleMap.addMarker(options);
            markerSK = showMarkerResto(SAUNG_KURING,googleMap,"Resto Saung Kuring");
            markerLS = showMarkerResto(LOTEK_SALOPA,googleMap,"Lotek Salopa");
            markerWC = showMarkerResto(WARUNG_CELUP,googleMap,"Warung Celup Seafood Bandung");
            markerAG = showMarkerResto(AYAM_GEPREK,googleMap,"Ayam geprek banjoer piyo piyo");
            markerSF = showMarkerResto(SEA_FOOD,googleMap,"Seafood lautan merah 2");

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            getCurrentLocation();
//            mapFragment.getMapAsync(callback);
        }

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location2) {
                if(location2 != null){
                    location = location2;
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

    private Marker showMarkerResto(LatLng position2, GoogleMap map, String title2){
        return map.addMarker(new MarkerOptions()
                .position(position2)
                .title(title2));
    }


}