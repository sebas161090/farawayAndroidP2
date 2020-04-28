package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.faraway.R;

public class PedidoActivity extends AppCompatActivity {

    ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        String url = "http://faraway.atwebpages.com/index.php/productos";
        final List<String> items = new ArrayList<>();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Producto producto = null;
                    listaProductos = new ArrayList<Producto>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject filas = jsonArray.getJSONObject(i);
                        items.add(filas.getString("idProducto")  + "|" +   filas.getString("nombre"));
                        Integer idProducto = filas.getInt("idProducto");
                        String descripcion = filas.getString("descripcion") ;
                        String nombre = filas.getString("nombre");
                        Double precio = filas.getDouble("precio") ;
                        Integer stock = filas.getInt("stock");
                        producto = new Producto();
                        producto.setId(idProducto);
                        producto.setNombre(nombre);
                        producto.setDescripcion(descripcion);
                        producto.setPrecio(precio);
                        producto.setStock(stock);
                        listaProductos.add(producto);
                        //items.add(nombre);

                    }

                    Spinner spinner = (Spinner) findViewById(R.id.products_spinner);
                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter(PedidoActivity.this, android.R.layout.simple_spinner_item, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(parent.getContext(),
                                    "Seleccionado: " + parent.getItemAtPosition(position).toString().substring(parent.getItemAtPosition(position).toString().indexOf("|") - 1), Toast.LENGTH_LONG).show();

                            //String substr=mysourcestring.substring(mysourcestring.indexOf("characterValue"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



                    //spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());




                } catch (JSONException e) {
                    Log.i("======>", e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                    }
                }
        );

        //Enviar solicitud
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }


    public void salir(View v) {
        startActivity(new Intent(this, ProductActivity.class));
    }


}
