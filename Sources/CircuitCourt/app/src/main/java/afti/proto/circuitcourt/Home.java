package afti.proto.circuitcourt;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView seekBarValue;
    private int radius;
    Globals globalVariable;

    ListFragment listfragment = new ListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listfragment).commit();

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBarValue = findViewById(R.id.seekBarValue);
        radius = seekBar.getProgress();
        globalVariable = (Globals) this.getApplicationContext();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                radius = seekBar.getProgress();
                seekBarValue.setText(radius + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void goToMaps(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("radius", radius );

        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment).commit();
    }

    public void showMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());

        if(globalVariable.getSeller()){
            popup.getMenu().findItem(R.id.createShop).setVisible(false);
        }
        else{
            popup.getMenu().findItem(R.id.manageShop).setVisible(false);
            popup.getMenu().findItem(R.id.manageProducts).setVisible(false);
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.createShop:
                        createShop();
                        break;
                    case R.id.manageShop:
                        manageShop();
                        break;
                    case R.id.manageProducts:
                        manageProducts();
                        break;
                    case R.id.editProfile:
                        editProfile();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    public void createShop() {
        Intent intent = new Intent(this, EditShop.class);
        intent.putExtra("mode", "create");
        startActivity(intent);
    }

    public void manageShop() {
        Intent intent = new Intent(this, EditShop.class);
        intent.putExtra("mode", "update");
        startActivity(intent);
    }

    public void manageProducts() {
        Intent intent = new Intent(this, ManageProducts.class);
        startActivity(intent);
    }

    public void editProfile() {
        Intent intent = new Intent(this, EditUserProfile.class);
        intent.putExtra("mode", "update");
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, Connection.class);
        intent.putExtra("logout", true);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listfragment).commit();
    }
}
