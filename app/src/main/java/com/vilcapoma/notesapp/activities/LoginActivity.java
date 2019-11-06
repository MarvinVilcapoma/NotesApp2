package com.vilcapoma.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vilcapoma.notesapp.R;
import com.vilcapoma.notesapp.models.User;
import com.vilcapoma.notesapp.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usernameinput;
    private TextInputEditText passwordinput;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameinput=findViewById(R.id.usernameinput);
        passwordinput=findViewById(R.id.passwordinput);
        login=findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

    }

    public void Registrar(View view) {
        Intent Registrar = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(Registrar);
    }

    public void doLogin(){
        String username=usernameinput.getText().toString();
        String password=passwordinput.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this,"Ingrese el usuario",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this,"Ingrese la contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        User user= UserRepository.Login(this,username,password);

        if (user==null){
            Toast.makeText(this,"Usuario y/o clave incorrecta",Toast.LENGTH_SHORT).show();
            return;
        }

        //Guardar el estado del Login en la SharedPreferences

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit()
                .putBoolean("isLogged",true)
                .putString("username",username)
                .commit();

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void LoadLastUsername(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username=sp.getString("username",null);
        if(username!=null){
            usernameinput.setText(username);
            passwordinput.requestFocus();
        }
    }
}
