package com.android.SmartParking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    EditText txt;
    Button btn;
    private GoogleMap mMap;
    Marker marker,marker1;
    Polyline lin;
    Circle shape;
    String a;

    String Id,Region1,distance1,Region2,distance2;
    Double Latitude1,Longitude1,Latitude2,Longitude2;
    ImageView img;
    double mylat;
    double mylng;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent i=getIntent();
        Id = i.getStringExtra("ID");
        mylat=Double.parseDouble(i.getStringExtra("mylat"));
        mylng=Double.parseDouble(i.getStringExtra("mylng"));
        count=i.getIntExtra("count", 0);
        for(int j=0;j<count;j++)
        {
            String temploc=i.getStringExtra("Region"+j);
            String tempdist="a";
            double lat=Double.parseDouble(i.getStringExtra("Lat"+j));
            double lng=Double.parseDouble(i.getStringExtra("Lng"+j));
            setmarker(temploc,tempdist,lat,lng);
        }
        LatLng ll=new LatLng(mylat, mylng);
        MarkerOptions mark=new MarkerOptions();
        mark.title("ME");
        mark.snippet("");
        mark.position(ll);
        mark.draggable(false);
        mark.icon(BitmapDescriptorFactory.fromResource(R.drawable.mymark));
        mMap.addMarker(mark);
        CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(ll, (float) 12);
        mMap.animateCamera(cu);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                String txt=arg0.getTitle();
                if(txt.compareTo("ME")==0)
                {
                    Toast.makeText(MapActivity.this, "My Current Locaton", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    txt=txt.substring(6, txt.length());
                    Intent i=new Intent(MapActivity.this,Slot.class);
                    i.putExtra("Region",txt);
                    i.putExtra("ID", Id);
                    startActivity(i);
                    finish();
                }
                return true;
            }
        });
    }


    public void setmarker(String loc,String dist,double lat,double lng)
    {
        LatLng ll=new LatLng(lat, lng);
        MarkerOptions mark=new MarkerOptions();
        mark.title("Area: "+loc);
        mark.snippet("Distance: "+dist);
        mark.position(ll);
        mark.draggable(false);
        mark.icon(BitmapDescriptorFactory.fromResource(R.drawable.parkmark));
        mMap.addMarker(mark);
    }


}
