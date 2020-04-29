package pe.edu.upc.faraway.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.faraway.R;
import pe.edu.upc.faraway.api.service.LoginService;
import pe.edu.upc.faraway.bean.request.LoginReq;
import pe.edu.upc.faraway.bean.response.LoginRes;
import pe.edu.upc.faraway.util.Constants;
import pe.edu.upc.faraway.util.Loading;
import retrofit2.Call;
import retrofit2.Callback;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Usuarios> listaUsuarios;
    ArrayList<String> listaInformacion;
    EditText clave;
    Button btn_iniciar,btn_reg;
    String criterio = "";

    private String resultado="";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_iniciar = (Button)findViewById(R.id.btnLogin);
        Button btn_reg = (Button)findViewById(R.id.btnRegistro);
        btn_iniciar.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }
    private void busqueda () {
        EditText clave = (EditText) findViewById(R.id.editTxtPassword);
        criterio = clave.getText().toString();
        String url = "http://faraway.atwebpages.com/index.php/usuarios/" + criterio;
        mensaje(url);

    }
    private void buscarUsuario () {
        EditText clave = (EditText) findViewById(R.id.editTxtPassword);
        criterio = clave.getText().toString();
        final String url = "http://faraway.atwebpages.com/index.php/usuarios/" + criterio;
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("id_usuario", jsonObject.getString("id_usuario"));
                        editor.putString("nombres", jsonObject.getString("nombres"));
                        editor.commit();


                        resultado = jsonObject.getString("nombres");
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        mensaje(e.getMessage());
                    }
                }
                if (!resultado.isEmpty()) {
                    Intent intent = new Intent(LoginActivity.this,ProductActivity.class);
                    startActivity(intent);
                }else
                {
                    mensaje("clave incorrecta");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mensaje("Ingresar Datos");
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
    private void mensaje(String msg)
    {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin:
                buscarUsuario();
                break;

            case R.id.btnRegistro:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
