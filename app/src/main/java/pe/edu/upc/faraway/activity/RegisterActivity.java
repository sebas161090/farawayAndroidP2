package pe.edu.upc.faraway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.upc.faraway.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
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
                String userName = edt_Mail.getText().toString();
                String nombres = edt_nombres.getText().toString();
                String apellidos = edt_apellidos.getText().toString();
                String celular = edt_cel.getText().toString();
                String password = edt_password.getText().toString();

                if (!userName.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !celular.isEmpty() && !password.isEmpty())

                {
                    ContentValues registro = new ContentValues();
                    registro.put("codigo", "");
                    registro.put("username", userName);
                    registro.put("nombre", nombres);
                    registro.put("apellidos", apellidos);
                    registro.put("celular", celular);
                    registro.put("password", password);
                    bdFaraway.insert("cliente", null,registro);
                    bdFaraway.close();
                    Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_LONG).show();
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
