package com.vilcapoma.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vilcapoma.notesapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout_item:
                callLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void callLogout(){
        //Eliminar el estado isLogged del sp
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("isLogged").commit();

        //Solo finalizamos
        finish();
        //Solo finalizamos o además redireccionamos al LoginActivity

        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }
}
