package com.vilcapoma.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vilcapoma.notesapp.R;
import com.vilcapoma.notesapp.repository.UserRepository;

public class RegistroActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_fullname;
    private EditText et_email;
    private EditText et_password;
    private Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_username=findViewById(R.id.et_username);
        et_fullname=findViewById(R.id.et_fullname);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        btn_registrar=findViewById(R.id.btn_registrar);


    }
    public void callRegister(View view){
        String username=et_username.getText().toString();
        String fullname =et_fullname.getText().toString();
        String email =et_email.getText().toString();
        String password =et_password.getText().toString();

        if(username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.Create(username,fullname, email, password);
        finish();

    }
}
