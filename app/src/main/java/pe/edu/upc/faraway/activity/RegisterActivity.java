package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import pe.edu.upc.faraway.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    String id_usuario, nombres, apellidos, telefono, password;
    private EditText edt_Mail, edt_nombres, edt_apellidos, edt_cel, edt_password;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_Mail = (EditText)findViewById(R.id.edtMail);
        edt_nombres = (EditText)findViewById(R.id.edtNombres);
        edt_apellidos = (EditText)findViewById(R.id.edtApellidos);
        edt_cel = (EditText)findViewById(R.id.edtCel);
        edt_password = (EditText)findViewById(R.id.edtPassword);
        btnReg = (Button) findViewById(R.id.btnRegistro);
        btnReg.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnRegistro:
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bdFaraway", null, 1);
                SQLiteDatabase bdFaraway = admin.getWritableDatabase();


                id_usuario = edt_Mail.getText().toString();
                nombres = edt_nombres.getText().toString();
                apellidos = edt_apellidos.getText().toString();
                telefono = edt_cel.getText().toString();
                password = edt_password.getText().toString();


                if (!id_usuario.toString().isEmpty() && !nombres.toString().isEmpty() && !apellidos.toString().isEmpty() && !telefono.toString().isEmpty() && !password.toString().isEmpty())

                {
                    ContentValues registro = new ContentValues();
                    registro.put("codigo", "");
                    registro.put("username", id_usuario);
                    registro.put("nombre", nombres);
                    registro.put("apellidos", apellidos);
                    registro.put("celular", telefono);
                    registro.put("password", password);
                    bdFaraway.insert("cliente", null,registro);
                    bdFaraway.close();


                    String url = "http://faraway.atwebpages.com/index.php/usuarios";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), "Se registro correctamente", Toast.LENGTH_SHORT).show();
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
                            parametro.put("id_usuario",id_usuario);
                            parametro.put("nombres",nombres);
                            parametro.put("apellidos", apellidos);
                            parametro.put("telefono", telefono);
                            parametro.put("password", password);

                            return parametro;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);

                    edt_Mail.setText("");
                    edt_nombres.setText("");
                    edt_apellidos.setText("");
                    edt_cel.setText("");
                    edt_password.setText("");


                } else {
                    Toast.makeText(this, "Debe registrar todos los campos solicitados", Toast.LENGTH_LONG).show();
                }


                break;

        }


    }
}
