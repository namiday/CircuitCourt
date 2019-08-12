package afti.proto.circuitcourt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products){
        super(context,0,products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product,parent, false);
        }

        ProductViewHolder viewHolder = (ProductViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProductViewHolder();
            viewHolder.nom = convertView.findViewById(R.id.nomProduct);
            viewHolder.prix = convertView.findViewById(R.id.prixProduct);
            viewHolder.tags = convertView.findViewById(R.id.tagsProduct);
            viewHolder.type = convertView.findViewById(R.id.typeProduct);
            convertView.setTag(viewHolder);
        }

        Product product = getItem(position);

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

        return convertView;
    }

    private class ProductViewHolder{
        public TextView nom;
        public TextView prix;
        public ImageView type;
        public TextView tags;
    }
}

