package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;
import pe.edu.upc.faraway.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DetalleProductoActivity extends AppCompatActivity {
    TextView idProducto, descripcion, nombre, precio, stock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        idProducto = (TextView)findViewById(R.id.idProducto);
        descripcion = (TextView)findViewById(R.id.descripcion);
        nombre = (TextView)findViewById(R.id.nombre);
        precio = (TextView)findViewById(R.id.precio);
        stock = (TextView)findViewById(R.id.stock);

        Intent intent = this.getIntent();
        Bundle objetoRecibido = intent.getExtras();
        Producto productos = null;

        if (objetoRecibido!=null) {
            productos = (Producto)objetoRecibido.getSerializable("codigo");

            idProducto.setText(productos.getIdProducto().toString());
            descripcion.setText(productos.getDescripcion().toString());
            nombre.setText(productos.getNombre().toString());
            precio.setText(productos.getPrecio().toString());
            stock.setText(productos.getStock().toString());
        }

    }

    public void salir(View v) {
        startActivity(new Intent(this, ProductActivity.class));
        //finish();
    }

    public void registrar(View v) {
        idProducto = (TextView)findViewById(R.id.idProducto);
        descripcion = (TextView)findViewById(R.id.descripcion);
        nombre = (TextView)findViewById(R.id.nombre);
        precio = (TextView)findViewById(R.id.precio);
        stock = (TextView)findViewById(R.id.stock);

        if (!idProducto.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !stock.getText().toString().isEmpty())

        {
            String url = "http://faraway.atwebpages.com/index.php/productoUpdate";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Producto Actualizado", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametro = new HashMap<String, String>();
                    parametro.put("nombre",nombre.getText().toString());
                    parametro.put("descripcion",descripcion.getText().toString());
                    parametro.put("precio",precio.getText().toString());
                    parametro.put("stock",stock.getText().toString());
                    parametro.put("idProducto",idProducto.getText().toString());

                    return parametro;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        } else {
            Toast.makeText(this, "Debe registrar todos los campos solicitados", Toast.LENGTH_LONG).show();
        }


    }

    public void eliminar(View v) {
        idProducto = (TextView)findViewById(R.id.idProducto);

        String url = "http://faraway.atwebpages.com/index.php/productoDelete";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametro = new HashMap<String, String>();
                parametro.put("id_producto",idProducto.getText().toString());

                return parametro;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
