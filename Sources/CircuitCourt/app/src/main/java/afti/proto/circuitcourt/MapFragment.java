package afti.proto.circuitcourt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MapFragment
        extends Fragment
        implements OnMapReadyCallback, LocationListener, GoogleMap.OnInfoWindowClickListener {

    private Context context;
    Bundle bundle;
    Globals globalVariable;
    private int radius;
    private HashMap markerHash;

    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        context = getActivity().getApplicationContext();
        globalVariable = (Globals) getActivity().getApplicationContext();
        bundle = getArguments();
        radius = bundle.getInt("radius");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        return view;
    }

    //Call the API to fetch shops in the given radius
    public void getJSON() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = globalVariable.getApiUrl() + "/getShops/" + globalVariable.getCurrentLat() + "/" + globalVariable.getCurrentLong() + "/" + radius;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            ArrayList<Shop> shops = new ArrayList<>();

                            for(int i = 0; i < response.length(); i++){
                                JSONObject currentObject = response.getJSONObject(i);

                                //Create a new Shop object for each shop fetched
                                if(currentObject.getInt("response") == 1){
                                    shops.add(
                                            new Shop(
                                                    currentObject.getString("shopName"),
                                                    currentObject.getString("shopCity"),
                                                    currentObject.getString("shopStreet"),
                                                    currentObject.getString("shopPostalCode"),
                                                    currentObject.getString("shopDescription"),
                                                    currentObject.getString("contacts"),
                                                    currentObject.getString("phone"),
                                                    currentObject.getDouble("shopLongitude"),
                                                    currentObject.getDouble("shopLatitude"),
                                                    currentObject.getInt("shopNum")
                                            )
                                    );
                                }
                            }

                            setMapMarkers(shops);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError e) {
                        e.printStackTrace();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    //Set a marker by shop on the map
    private void setMapMarkers(ArrayList<Shop> shops) {
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mymarker)).position(new LatLng(globalVariable.getCurrentLat(), globalVariable.getCurrentLong())));

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        LatLng currentPos = new LatLng(globalVariable.getCurrentLat(), globalVariable.getCurrentLong());

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 12));
        //drawCircle(currentPos, radius);

        markerHash = new HashMap<Marker, Integer>();

        for (int i = 0; i < shops.size(); i++) {
            Marker marker = map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .position(new LatLng(shops.get(i).getLongitude(), shops.get(i).getLatitude()))
                    .title(shops.get(i).getName())
            );

            markerHash.put(marker, shops.get(i).getNum());

            map.setOnInfoWindowClickListener(this);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(context, DetailExploitant.class);
        intent.putExtra("shop_num", (int) markerHash.get(marker));
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap gmap) {
        map = gmap;
        globalVariable.getLocation(getActivity());
        getJSON();
    }

    /*
    private void drawCircle(LatLng point, int radius){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(0x30ff0000);
        circleOptions.strokeWidth(2);
        map.addCircle(circleOptions);
    }
    */

    @Override
    public void onLocationChanged(Location location) {
        //Get new current Location when the phone moves
        globalVariable.getLocation(getActivity());
        LatLng currentLocation = new LatLng(globalVariable.getCurrentLat(), globalVariable.getCurrentLong());
        map.addMarker(new MarkerOptions().position(currentLocation).title(R.string.currentLocation+""));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        globalVariable.displayToast("Enabled new provider " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        globalVariable.displayToast("Disabled provider " + provider);
    }
}