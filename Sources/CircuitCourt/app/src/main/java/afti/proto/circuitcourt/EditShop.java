package afti.proto.circuitcourt;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class EditShop extends AppCompatActivity {

    Globals globalVariable;

    private TextView title;
    private EditText shopName, shopCity, shopStreet, shopPostalCode, shopKind, shopDescription, contacts, phone;
    private Button btnConfirm;
    private int id, shopNum;
    private String password;
    private Double shopLongitude, shopLatitude;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);

        globalVariable = (Globals) getApplicationContext();

        title = findViewById(R.id.title);
        shopName = findViewById(R.id.shopName);
        shopCity = findViewById(R.id.shopCity);
        shopStreet = findViewById(R.id.shopStreet);
        shopPostalCode = findViewById(R.id.shopPostalCode);
        shopKind = findViewById(R.id.shopKind);
        shopDescription = findViewById(R.id.shopDescription);
        contacts = findViewById(R.id.contacts);
        phone = findViewById(R.id.phone);
        btnConfirm = findViewById(R.id.btnConfirm);

        mode = getIntent().getExtras().getString("mode");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = globalVariable.getApiUrl() + "/getUser/" + globalVariable.getMail();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            id = response.getInt("id");
                            password = response.getString("password");

                            if(response.has("shopNum")){
                                shopNum = response.getInt("shopNum");
                            }
                            else{
                                shopNum = new Random().nextInt(1000000000) + 1;
                            }

                            if(mode.equals("create")){
                                title.setText(getResources().getString(R.string.titleShopCreate));
                                btnConfirm.setText(getResources().getString(R.string.create));
                            }
                            else if(mode.equals("update")){
                                title.setText(getResources().getString(R.string.titleShopUpdate));
                                shopName.setText(response.getString("shopName"));
                                shopCity.setText(response.getString("shopCity"));
                                shopStreet.setText(response.getString("shopStreet"));
                                shopPostalCode.setText(response.getString("shopPostalCode"));
                                shopKind.setText(response.getString("shopKind"));
                                shopDescription.setText(response.getString("shopDescription"));
                                contacts.setText(response.getString("contacts"));
                                phone.setText(response.getString("phone"));
                                btnConfirm.setText(getResources().getString(R.string.update));
                            }
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

        requestQueue.add(jsonObjectRequest);
    }

    public void confirm(View view){
        if(
                shopName.getText().toString().equals("")
                || shopCity.getText().toString().equals("")
                || shopStreet.getText().toString().equals("")
                || shopPostalCode.getText().toString().equals("")
                || shopKind.getText().toString().equals("")
                || shopDescription.getText().toString().equals("")
                || contacts.getText().toString().equals("")
                || phone.getText().toString().equals("")
                ){
            globalVariable.displayToast(getResources().getString(R.string.emptyFields));
        }
        else{
            getCoordinates(
                    shopStreet.getText().toString() + " "
                            + shopPostalCode.getText().toString()+ " "
                            + shopCity.getText().toString()
            );

            String url = globalVariable.getApiUrl()
                    + "/updateUser/"
                    + id
                    + "/" + globalVariable.getMail()
                    + "/" + password
                    + "/" + globalVariable.getFirstName()
                    + "/" + globalVariable.getLastName()
                    + "/" + globalVariable.getNickName()
                    + "/" + shopName.getText().toString()
                    + "/" + shopCity.getText().toString()
                    + "/" + shopStreet.getText().toString()
                    + "/" + shopNum
                    + "/" + shopPostalCode.getText().toString()
                    + "/" + shopKind.getText().toString()
                    + "/" + shopDescription.getText().toString()
                    + "/" + contacts.getText().toString()
                    + "/" + phone.getText().toString()
                    + "/" + shopLongitude
                    + "/" + shopLatitude
                    ;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            globalVariable.setSeller(true);
                            globalVariable.setShopName(shopName.getText().toString());
                            globalVariable.setShopCity(shopCity.getText().toString());
                            globalVariable.setShopStreet(shopStreet.getText().toString());
                            globalVariable.setShopNum(shopNum);
                            globalVariable.setShopPostalCode(shopPostalCode.getText().toString());
                            globalVariable.setShopKind(shopKind.getText().toString());
                            globalVariable.setShopDescription(shopDescription.getText().toString());

                            globalVariable.displayToast(R.string.userCreated);
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue.add(stringRequest);
        }
    }

    private void getCoordinates(String address){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        try{
            addresses = geocoder.getFromLocationName(address, 1);
            if(addresses.size() > 0) {
                shopLongitude = addresses.get(0).getLatitude();
                shopLatitude = addresses.get(0).getLongitude();
                Log.e("DEBUG", "Long=" + shopLongitude + ", Lat=" + shopLatitude);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
