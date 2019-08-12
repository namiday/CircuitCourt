package afti.proto.circuitcourt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;

import static java.security.AccessController.getContext;

public class EditProduct extends AppCompatActivity {

    Globals globalVariable;
    private EditText name, price, tags;
    private RadioGroup type;
    private String mode, product_name, product_type;
    private int product_id;
    private double product_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        globalVariable = (Globals) getApplicationContext();
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        tags = findViewById(R.id.tags);
        type = findViewById(R.id.type);

        Bundle extras = getIntent().getExtras();

        mode = getIntent().getExtras().getString("mode");

        if(mode.equals("update")){
            TextView title = findViewById(R.id.title);
            title.setText(getResources().getString(R.string.updateProduct));

            Button btnConfirm = findViewById(R.id.btnConfirm);
            btnConfirm.setText(getResources().getString(R.string.update));

            product_name = extras.getString("product_name");
            product_id = extras.getInt("product_id");
            product_price = extras.getDouble("product_price");
            product_type = extras.getString("product_type");

            name.setText(product_name);
            price.setText(new DecimalFormat("#.##").format(product_price));

            RadioButton rb;

            switch (product_type){
                case "legume":
                    rb = findViewById(R.id.legume);
                    rb.setChecked(true);
                    break;
                case "fruit":
                    rb = findViewById(R.id.fruit);
                    rb.setChecked(true);
                    break;
                case "viande":
                    rb = findViewById(R.id.fruit);
                    rb.setChecked(true);
                    break;
                case "autre":
                    rb = findViewById(R.id.fruit);
                    rb.setChecked(true);
                    break;
            }
        }
    }

    public void confirm(View view){
        if(
                name.getText().toString().equals("")
                || price.getText().toString().equals("")
                //|| tags.getText().toString().equals("")
                ){
            globalVariable.displayToast(getResources().getString(R.string.emptyFields));
        }
        else{
            RadioButton selectedRadioButton = findViewById(type.getCheckedRadioButtonId());

            String url;
            if(mode.equals("update")){
                url = globalVariable.getApiUrl()
                        + "/updateProduct/"
                        + product_id
                        + "/" + globalVariable.getShopNum()
                        + "/" + name.getText().toString()
                        + "/" + price.getText().toString().replace(",", ".")
                        + "/" + tags.getText().toString()
                        + "/" + selectedRadioButton.getTag().toString()
                        ;
            }
            else{
                url = globalVariable.getApiUrl()
                        + "/createProduct/"
                        + globalVariable.getShopNum()
                        + "/" + name.getText().toString()
                        + "/" + price.getText().toString().replace(",", ".")
                        + "/" + tags.getText().toString()
                        + "/" + selectedRadioButton.getTag().toString()
                        ;
            }

            Log.e("DEBUG", url);

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            globalVariable.displayToast(R.string.productAdded);
                            Intent intent = new Intent(getApplicationContext(), ManageProducts.class);
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
}
