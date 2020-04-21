package pe.edu.upc.faraway.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import pe.edu.upc.faraway.R;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> listItems;


    public Adaptador(Context context, ArrayList<Producto> listItems){
        this.context = context;
        this.listItems = listItems;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto item = (Producto) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_product, null);
        ImageView imgFoto = (ImageView)convertView.findViewById(R.id.imgFoto);
        TextView tvTitulo = (TextView)convertView.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = (TextView)convertView.findViewById(R.id.tvDescripcion);

        imgFoto.setImageResource(item.getFoto());
        tvTitulo.setText(item.getNombre());
        tvDescripcion.setText(item.getDescripcion());

        return convertView;
    }
}
