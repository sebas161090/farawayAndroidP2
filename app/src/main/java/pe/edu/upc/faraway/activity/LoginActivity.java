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


import pe.edu.upc.faraway.R;
import pe.edu.upc.faraway.api.service.LoginService;
import pe.edu.upc.faraway.bean.request.LoginReq;
import pe.edu.upc.faraway.bean.response.LoginRes;
import pe.edu.upc.faraway.util.Constants;
import pe.edu.upc.faraway.util.Loading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog loading;
    private SharedPreferences prefs;

    //UI
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);

        getSupportActionBar().hide();

        bindUI();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                prepareLogin(username, password);
            }
        });
        btnRegistro.setOnClickListener(this);
    }

    private void bindUI(){
        loading = Loading.init(this);
        editTextUsername = findViewById(R.id.editTxtUsername);
        editTextPassword = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);
    }

    private void prepareLogin(String username, String password){
        Log.i("login","inicio");
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Usuario no valido", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password no valido", Toast.LENGTH_LONG).show();
        }else {
            login(username, password);
        }
    }

    private void login(String username, String password){
        Log.i("login"  + username ,"password " + password);
//        LoginService loginService = Api.getApi().create(LoginService.class);
//        Call<LoginRes> login = loginService.login(new LoginReq(username, password));
//        Loading.show(loading);
//        login.enqueue(new Callback<LoginRes>() {
//            @Override
//            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
//                Loading.close(loading);
//                if(response.code()==200){
//                    LoginRes loginRes = response.body();
//                    saveToken(loginRes);
//                }else{
//                    Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<LoginRes> call, Throwable t) {
//                Loading.close(loading);
//                Toast.makeText(LoginActivity.this, Constants.ERROR_GENERIC, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void saveToken(LoginRes loginRes){
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(Constants.TOKEN, loginRes.getToken());
//        editor.commit();
//
//        goToAcceptanceSearch();
    }

    private void goToAcceptanceSearch(){
//        Intent intent = new Intent(this, AcceptanceSearchActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegistro:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
