package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;
import pe.edu.upc.faraway.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class SearchProductActivity extends AppCompatActivity {

    private ListView lvItems;
    ArrayList<Producto> listaProductos;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);



    }


    public void buscar(View v) {
        EditText txtCriterio = (EditText)findViewById(R.id.txtCriterio);
        String  criterio = txtCriterio.getText().toString();
        String url = "http://faraway.atwebpages.com/index.php/productos/"+criterio;
        final List<Producto> items = new ArrayList<>();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Producto producto = null;
                    listaProductos = new ArrayList<Producto>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject filas = jsonArray.getJSONObject(i);
                        //items.add(object.getString("nombre") + object.getString("descripcion") + " (S/. "+object.getString("precio")+") ");
                        Integer idProducto = filas.getInt("idProducto");
                        String descripcion = filas.getString("descripcion") ;
                        String nombre = filas.getString("nombre");
                        producto = new Producto();

                        producto.setNombre(nombre);
                        producto.setDescripcion(descripcion);
                        listaProductos.add(producto);
                        items.add(producto);
                    }

                    //lvItems = (ListView) findViewById(R.id.lvItems);
                    //lstProductos = findViewById(R.id.lvItems)
                    ListView lstProductos = findViewById(R.id.lvItems);

                    ArrayAdapter<Producto> adaptador = new ArrayAdapter<>(
                            SearchProductActivity.this,
                            android.R.layout.simple_list_item_1,
                            items);
                    //ASignamos el Adapter al ListView
                    lstProductos.setAdapter(adaptador);
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
    }//Fin del Evento Click







}
