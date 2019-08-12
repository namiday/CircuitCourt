package afti.proto.circuitcourt;

import android.content.Intent;
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

public class EditUserProfile extends AppCompatActivity {

    Globals globalVariable;

    private TextView title;
    private EditText email, password, firstName, lastName, nickName, currentPassword, confirmPassword;
    private CheckBox changePassword;
    private Button btnConfirm;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        globalVariable = (Globals) getApplicationContext();

        title = findViewById(R.id.title);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        nickName = findViewById(R.id.nickName);
        currentPassword = findViewById(R.id.currentPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        changePassword = findViewById(R.id.changePassword);
        btnConfirm = findViewById(R.id.btnConfirm);

        mode = getIntent().getExtras().getString("mode");

        changePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    confirmPassword.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    currentPassword.setVisibility(View.VISIBLE);
                }
                else{
                    confirmPassword.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    currentPassword.setVisibility(View.GONE);
                }
            }
        });

        if(mode.equals("create")){
            title.setText(getResources().getString(R.string.titleCreate));
            btnConfirm.setText(getResources().getString(R.string.create));
            confirmPassword.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
        }
        else if(mode.equals("update")){
            title.setText(getResources().getString(R.string.titleUpdate));
            btnConfirm.setText(getResources().getString(R.string.update));
            changePassword.setVisibility(View.VISIBLE);

            email.setText(globalVariable.getMail());
            firstName.setText(globalVariable.getFirstName());
            lastName.setText(globalVariable.getLastName());
            nickName.setText(globalVariable.getNickName());

            password.setHint(getResources().getString(R.string.newPassword));
        }

    }

    public void confirm(View view){
        if(mode.equals("create")){
            create();
        }
        else if(mode.equals("update")){
            update();
        }
    }

    private void create(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = globalVariable.getApiUrl() + "/getUser/" + email.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //Ensure the mail does not already exist
                            if(response.getInt("response") != 1){
                                //Ensure the passwords are matching
                                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                                    //If everything is ok, create the user via the API
                                    String url = globalVariable.getApiUrl()
                                                    + "/createUser/" + email.getText().toString()
                                                    + "/" + password.getText().toString()
                                                    + "/" + firstName.getText().toString()
                                                    + "/" + lastName.getText().toString()
                                                    + "/" + nickName.getText().toString();

                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    globalVariable.displayToast(R.string.userCreated);
                                                    Intent intent = new Intent(getApplicationContext(), Connection.class);
                                                    intent.putExtra("logout", true);
                                                    startActivity(intent);
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    });
                                    queue.add(stringRequest);
                                }
                                else{
                                    Log.e("DEBUG", "password=" + password.getText().toString());
                                    Log.e("DEBUG", "confirmPassword=" + confirmPassword.getText().toString());
                                    globalVariable.displayToast(R.string.passwordsNotSame);
                                }
                            }
                            else{
                                globalVariable.displayToast(R.string.emailAlreadyExists);
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

    private void update(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = globalVariable.getApiUrl() + "/getUser/" + email.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("DEBUG", response.toString());
                        try{
                            if(changePassword.isChecked()){
                                //Ensure the new passwords are matching
                                if(currentPassword.getText().toString().equals(response.getString("password"))){
                                    if(password.getText().toString().equals(confirmPassword.getText().toString()) && !password.getText().toString().equals("")){
                                        //If everything is ok, update the user via the API
                                        String url = globalVariable.getApiUrl()+ "/updateUser/"
                                                + response.getInt("id")
                                                + "/" + email.getText().toString()
                                                + "/" + confirmPassword.getText().toString()
                                                + "/" + firstName.getText().toString()
                                                + "/" + lastName.getText().toString()
                                                + "/" + nickName.getText().toString();

                                        Log.e("DEBUG", url);

                                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        globalVariable.displayToast(R.string.userUpdated);
                                                        Intent intent = new Intent(getApplicationContext(), Connection.class);
                                                        intent.putExtra("logout", true);
                                                        startActivity(intent);
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                            }
                                        });
                                        queue.add(stringRequest);
                                    }
                                    else{
                                        globalVariable.displayToast(R.string.passwordsNotSame);
                                    }
                                }
                                else{
                                    globalVariable.displayToast(R.string.errorConnectionPassword);
                                }
                            }
                            else{
                                //If everything is ok, update the user via the API
                                String url = globalVariable.getApiUrl() + "/updateUser/"
                                        + response.getInt("id")
                                        + "/" + email.getText().toString()
                                        + "/" + response.getString("password")
                                        + "/" + firstName.getText().toString()
                                        + "/" + lastName.getText().toString()
                                        + "/" + nickName.getText().toString();

                                Log.e("DEBUG", url);

                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                globalVariable.displayToast(R.string.userUpdated);
                                                Intent intent = new Intent(getApplicationContext(), Connection.class);
                                                intent.putExtra("logout", true);
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
}
