package afti.proto.circuitcourt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Math.round;

public class ShopAdapter extends ArrayAdapter<Shop> {

    Globals globalVariable;

    public ShopAdapter(Context context, List<Shop> shops, Activity activity){
    super(context,0, shops);

    globalVariable = (Globals) activity.getApplicationContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop, parent, false);
        }

        ShopAdapter.ShopViewHolder viewHolder = (ShopAdapter.ShopViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ShopAdapter.ShopViewHolder();
            viewHolder.shopView = convertView.findViewById(R.id.shopView);
            viewHolder.shopName = convertView.findViewById(R.id.shopName);
            viewHolder.shopAddress = convertView.findViewById(R.id.shopAddress);
            viewHolder.shopCity = convertView.findViewById(R.id.shopCity);
            viewHolder.distance = convertView.findViewById(R.id.distance);
            viewHolder.btnInfo = convertView.findViewById(R.id.btnInfo);
            convertView.setTag(viewHolder);
        }

        Shop shop = getItem(position);

        viewHolder.btnInfo.setTag(shop.getNum());

        if(position%2 == 0){
            viewHolder.shopView.setBackgroundColor(getContext().getResources().getColor(R.color.green));
        }
        else{
            viewHolder.shopView.setBackgroundColor(getContext().getResources().getColor(R.color.darkGreen));
        }

        viewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailExploitant.class);
                intent.putExtra("shop_num", (int) view.getTag());
                getContext().startActivity(intent);
            }
        });

        viewHolder.shopName.setText(shop.getName());
        viewHolder.shopAddress.setText(shop.getStreet());
        viewHolder.shopCity.setText(shop.getPostal_code() + " " + shop.getCity());
        viewHolder.distance.setText(
            String.format(
                getContext().getResources().getString(R.string.distanceShop),
                new DecimalFormat("#.##").format(globalVariable.calcDistance(shop.getLatitude(), shop.getLongitude()))
            )
        );

        return convertView;
    }



    private class ShopViewHolder{
        public RelativeLayout shopView;
        public TextView shopName;
        public TextView shopAddress;
        public TextView shopCity;
        public TextView distance;
        public ImageButton btnInfo;
    }
}
