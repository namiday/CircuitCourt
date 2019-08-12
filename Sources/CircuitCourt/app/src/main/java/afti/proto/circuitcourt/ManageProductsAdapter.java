package afti.proto.circuitcourt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.List;

public class ManageProductsAdapter extends ArrayAdapter<Product> {
    Globals globalVariable;

    public ManageProductsAdapter(Context context, List<Product> products){
        super(context,0,products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        globalVariable = (Globals) getContext().getApplicationContext();

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_management,parent, false);
        }

        ProductManagementViewHolder viewHolder = (ProductManagementViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProductManagementViewHolder();
            viewHolder.nom = convertView.findViewById(R.id.nomProduct);
            viewHolder.prix = convertView.findViewById(R.id.prixProduct);
            viewHolder.tags = convertView.findViewById(R.id.tagsProduct);
            viewHolder.type = convertView.findViewById(R.id.typeProduct);
            viewHolder.btnEdit = convertView.findViewById(R.id.btnEdit);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(viewHolder);
        }

        final Product product = getItem(position);

        viewHolder.nom.setText(product.getNom());
        viewHolder.prix.setText(
            String.format(
                getContext().getResources().getString(R.string.productPrice),
                new DecimalFormat("#.##").format(product.getPrix())
            )
            );
        viewHolder.tags.setText(product.getTags());

        String productType = product.getType();
        if (productType.equals("legume"))
            viewHolder.type.setImageResource(R.drawable.legume);
        if (productType.equals("fruit"))
            viewHolder.type.setImageResource(R.drawable.fruit);
        if (productType.equals("viande"))
            viewHolder.type.setImageResource(R.drawable.viande);
        if (productType.equals("autre"))
            viewHolder.type.setImageResource(R.drawable.autre);

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditProduct.class);
                intent.putExtra("mode", "update");
                intent.putExtra("product_id", product.getId());
                intent.putExtra("product_name", product.getNom());
                intent.putExtra("product_type", product.getType());
                intent.putExtra("product_price", product.getPrix());
                intent.putExtra("product_type", product.getType());
                getContext().startActivity(intent);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String url = globalVariable.getApiUrl() + "/deleteProduct/" + product.getId();

                RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                globalVariable.displayToast(R.string.productDeleted);
                                Intent intent = new Intent(getContext(), ManageProducts.class);
                                getContext().startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                queue.add(stringRequest);
            }
        });

        return convertView;
    }

    private class ProductManagementViewHolder{
        public TextView nom;
        public TextView prix;
        public ImageView type;
        public TextView tags;
        public ImageButton btnEdit;
        public ImageButton btnDelete;
    }
}