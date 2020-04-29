package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.edu.upc.faraway.R;

public class PedidoActivity extends AppCompatActivity {


    TextView total;
    Date fecha = Calendar.getInstance().getTime();
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String newDateStr = formatDate.format(fecha);
    String id_producto, precio;


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
                    //listaProductos = new ArrayList<Producto>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject filas = jsonArray.getJSONObject(i);
                        items.add(filas.getString("idProducto")  + "-" +   filas.getString("nombre") + "-" + filas.getString("precio"));
                    }

                    Spinner spinner = (Spinner) findViewById(R.id.products_spinner);
                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter(PedidoActivity.this, android.R.layout.simple_spinner_item, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String[] parts = parent.getItemAtPosition(position).toString().split("-");
                            id_producto = parts[0];
                            precio = parts[2];
                            //Toast.makeText(parent.getContext(),
                            //      parts[0], Toast.LENGTH_LONG).show();

                            //String substr=mysourcestring.substring(mysourcestring.indexOf("characterValue"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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

    }


    public void registrar(View v) {
        total = (TextView)findViewById(R.id.total);

        if (!total.getText().toString().isEmpty())
        {
            String url = "http://faraway.atwebpages.com/index.php/pedidos";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Pedido Registrado", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                    String id_usuarioSession = prefs.getString("id_usuario", "");
                    Map<String,String> parametro = new HashMap<String, String>();
                    parametro.put("fecha",newDateStr);
                    parametro.put("total",total.getText().toString());
                    parametro.put("id_usuario", id_usuarioSession);
                    parametro.put("id_producto", id_producto);
                    parametro.put("precio", precio);

                    return parametro;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Debe registrar todos los campos solicitados", Toast.LENGTH_LONG).show();
        }



    }


    public void salir(View v) {
        startActivity(new Intent(this, ProductActivity.class));
    }


}
