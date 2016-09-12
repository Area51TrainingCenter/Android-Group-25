package com.jcodee.mod2class3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    //https://karanbalkar.com/2013/11/implement-a-draggable-marker-using-google-maps-android-api-v2/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
        //Para cambiar las opciones del mapa
        //googleMap.getUiSettings()

        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        //Agregamos un pin en el mapa, posición y cmabiamos la imagen a mostrar
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-71.34875, -12.23434))
                .title("Titulo 1")
                .snippet("Descripción")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        //Movemos la camara o el enfoque hacia el punto que asignamos
        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(-71.34875, -12.23434), 13));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Realizar acciones al seleccionar un marker o un pin
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //btnLocalizar

    }
}
