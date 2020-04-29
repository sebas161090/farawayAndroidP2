package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.faraway.R;

public class ProductActivity extends AppCompatActivity {

    private ListView lvtItems;
    private Adaptador adaptador;
    ArrayList<Producto> listaProductos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        lvtItems = (ListView) findViewById(R.id.lvItems);

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
                        //items.add(object.getString("nombre") + object.getString("descripcion") + " (S/. "+object.getString("precio")+") ");
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
                        items.add(nombre);

                    }

                    String mensaje = listaProductos.get(0).getNombre();
                    Toast.makeText(ProductActivity.this,mensaje,Toast.LENGTH_SHORT).show();

                    //lvItems = (ListView) findViewById(R.id.lvItems);
                    //lstProductos = findViewById(R.id.lvItems)
                    ListView lstProductos = findViewById(R.id.lvItems);
                    adaptador = new Adaptador(ProductActivity.this, listaProductos);
                    lvtItems.setAdapter(adaptador);


                    lstProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                            String userName = prefs.getString("nombres", "");

                            if (!userName.contains("admin"))  {
                                Toast.makeText(ProductActivity.this, "No tiene permisos de administrador.", Toast.LENGTH_LONG).show();
                            } else {
                                Producto producto  = listaProductos.get(position);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("codigo", (Serializable) producto);
                                Intent intent = new Intent(ProductActivity.this,DetalleProductoActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }



                        }
                    });


/*
                    ArrayAdapter<Producto> adaptador = new ArrayAdapter<Producto>(
                            ProductActivity.this,
                            android.R.layout.simple_list_item_1,
                            items);*/
/*
                    ArrayAdapter<Producto> adaptador = new ArrayAdapter<>(
                            SearchProductActivity.this,
                            android.R.layout.simple_list_item_1,
                            items);
                    //ASignamos el Adapter al ListView
                    lstProductos.setAdapter(adaptador);*/
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

    public void buscar(View v) {
        EditText txtCriterio = (EditText)findViewById(R.id.txtCriterio);
        String  criterio = txtCriterio.getText().toString();
        String url = "http://faraway.atwebpages.com/index.php/productos/"+criterio;

        lvtItems = (ListView) findViewById(R.id.lvItems);
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
                        //items.add(object.getString("nombre") + object.getString("descripcion") + " (S/. "+object.getString("precio")+") ");
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
                        items.add(nombre);

                    }

                    String mensaje = listaProductos.get(0).getNombre();
                    Toast.makeText(ProductActivity.this,mensaje,Toast.LENGTH_SHORT).show();

                    ListView lstProductos = findViewById(R.id.lvItems);
                    adaptador = new Adaptador(ProductActivity.this, listaProductos);
                    lvtItems.setAdapter(adaptador);


                    lstProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                            String userName = prefs.getString("nombres", "");

                            if (!userName.contains("admin"))  {
                                Toast.makeText(ProductActivity.this, "No tiene permisos de administrador.", Toast.LENGTH_LONG).show();
                            } else {
                                Producto producto  = listaProductos.get(position);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("codigo", (Serializable) producto);
                                Intent intent = new Intent(ProductActivity.this,DetalleProductoActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        }
                    });



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_Pedido:
                startActivity(new Intent(this, PedidoActivity.class));
                break;
            case R.id.action_Login:
                startActivity(new Intent(this, LoginActivity.class));
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Producto> GetArrayItems(){
        ArrayList<Producto> listItems = new ArrayList<>();
        //listItems.add(new Producto(R.drawable.florencia, "Florencia", "Florencia Desc"));
        //listItems.add(new Producto(R.drawable.cayetana, "Cayetana", "Cayetana Desc"));
        //listItems.add(new Producto(R.drawable.serena, "Serena", "Serena Desc"));

        return listItems;

    }



}
