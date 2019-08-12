package afti.proto.circuitcourt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendationFragment extends Fragment {

    private ListView listRecommendation;
    private Context context;
    Globals globalVariable;
    private int RADIUS = 15;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);

        listRecommendation = view.findViewById(R.id.listRecommendation);
        context = getActivity().getApplicationContext();
        globalVariable = (Globals) getActivity().getApplicationContext();

        getNearestShops();

        return view;
    }

    private void getNearestShops(){
        globalVariable.getLocation(getActivity());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = globalVariable.getApiUrl() + "/getShops/" + globalVariable.getCurrentLat() + "/" + globalVariable.getCurrentLong() + "/" + RADIUS;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            ArrayList<Shop> shops = new ArrayList<>();

                            for(int i = 0; i < response.length(); i++){
                                JSONObject currentObject = response.getJSONObject(i);

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

                            ShopAdapter adapter = new ShopAdapter(context, shops, getActivity());
                            listRecommendation.setAdapter(adapter);
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
}
