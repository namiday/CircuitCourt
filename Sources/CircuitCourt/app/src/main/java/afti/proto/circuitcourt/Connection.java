package afti.proto.circuitcourt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Connection extends AppCompatActivity {

    private EditText email, password;
    Globals globalVariable;

    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        saveLoginCheckBox = findViewById(R.id.rememberMe);

        globalVariable = (Globals) getApplicationContext();
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        //Does the user want to logout?
        Boolean logout = false;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            logout = extras.containsKey("logout") && extras.getBoolean("logout");
        }

        //If not, fill the inputs with the remembered credentials
        if(!logout){
            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin) {
                email.setText(loginPreferences.getString("username", ""));
                password.setText(loginPreferences.getString("password", ""));
                saveLoginCheckBox.setChecked(true);
            }
        }
    }

    public void connection(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Call the API to fetch the user depending on the mail
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, globalVariable.getApiUrl() + "/getUser/" + email.getText(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getInt("response") == 1){
                                String password = response.getString("password");

                                globalVariable.setMail(response.getString("mail"));
                                globalVariable.setFirstName(response.getString("firstName"));
                                globalVariable.setLastName(response.getString("lastName"));
                                globalVariable.setNickName(response.getString("nickName"));
                                globalVariable.setSeller(response.getBoolean("isSeller"));

                                if(globalVariable.getSeller()){
                                    globalVariable.setShopName(response.getString("shopName"));
                                    globalVariable.setShopCity(response.getString("shopCity"));
                                    globalVariable.setShopStreet(response.getString("shopStreet"));
                                    globalVariable.setShopNum(response.getInt("shopNum"));
                                    globalVariable.setShopPostalCode(response.getString("shopPostalCode"));
                                    globalVariable.setShopKind(response.getString("shopKind"));
                                    globalVariable.setShopDescription(response.getString("shopDescription"));
                                }

                                confirmConnection(password);
                            }
                            else{
                                globalVariable.displayToast(R.string.errorConnectionEmail);
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

    //Check if the password is correct
    private void confirmConnection(String api_password){
        if(password.getText().toString().equals(api_password)){
            //Remember credentials
            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", email.getText().toString());
                loginPrefsEditor.putString("password", password.getText().toString());
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        else{
            globalVariable.displayToast(R.string.errorConnectionPassword);
        }
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, EditUserProfile.class);
        intent.putExtra("mode", "create");
        startActivity(intent);
    }

}
