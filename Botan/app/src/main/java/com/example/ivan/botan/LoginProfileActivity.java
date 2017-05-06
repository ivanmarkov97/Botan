package com.example.ivan.botan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonOK;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Профиль");

        buttonOK = (Button) findViewById(R.id.buttonProfileOK);
        buttonOK.setOnClickListener(this);

        //editText = (EditText) findViewById(R.id.editTextProfileAboutMe);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(this, MainMenuActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonProfileOK:
                Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
        }
    }
}
