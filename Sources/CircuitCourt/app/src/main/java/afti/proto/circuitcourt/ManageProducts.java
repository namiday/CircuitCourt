package afti.proto.circuitcourt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.List;

public class ManageProducts extends AppCompatActivity {

    Globals globalVariable;
    private ListView listViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);

        globalVariable = (Globals) getApplicationContext();
        listViewProducts = findViewById(R.id.listViewProducts);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, globalVariable.getApiUrl() + "/getProducts/" + globalVariable.getShopNum(), null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            List<Product> products = new ArrayList<>();

                            for(int i = 0; i < response.length(); i++){
                                JSONObject productObject = response.getJSONObject(i);

                                products.add(
                                        new Product(
                                                productObject.getInt("_id"),
                                                productObject.getString("name"),
                                                productObject.getDouble("price"),
                                                productObject.getString("tags"),
                                                productObject.getString("type")
                                        )
                                );
                            }

                            ManageProductsAdapter adapter = new ManageProductsAdapter(ManageProducts.this, products);
                            listViewProducts.setAdapter(adapter);
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

    public void newProduct(View view){
        Intent intent = new Intent(this, EditProduct.class);
        intent.putExtra("mode", "create");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
