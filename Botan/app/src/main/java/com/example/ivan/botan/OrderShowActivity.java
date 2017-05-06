package com.example.ivan.botan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class OrderShowActivity extends AppCompatActivity implements View.OnClickListener{

    TextView subject;
    TextView description;
    TextView date;
    TextView cost;
    Button buttonTakeOrder;
    Button buttonOffer;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_show);


        Log.d("MyTAG", "I am in OrderShow");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Заказы");

        Intent intent = getIntent();
        Log.d("MyTAG", "I am after Intent");
        String s_subject = intent.getStringExtra("subject");
        Log.d("MyTAG", s_subject);
        String s_description = intent.getStringExtra("description");
        Log.d("MyTAG", s_description);
        String s_date = intent.getStringExtra("date");
        Log.d("MyTAG", s_date);
        String s_cost = intent.getStringExtra("cost");
        Log.d("MyTAG", s_cost);
        Log.d("MyTAG", "I am after getExtra");

        subject = (TextView) findViewById(R.id.textViewOrderShowSubject);
        description = (TextView) findViewById(R.id.textViewOrderShowDesciption);
        cost = (TextView) findViewById(R.id.textViewOrderCost);
        Log.d("MyTAG", "I am after init TextViews");

        buttonTakeOrder = (Button) findViewById(R.id.buttonTakeOrder);
        buttonBack = (Button) findViewById(R.id.buttonReturnToSelect);
        Log.d("MyTAG", "I am after init Buttons");

        subject.setText(s_subject);
        Log.d("MyTAG", "subject is OK");
        description.setText(s_description);
        Log.d("MyTAG", "desc is OK");
        //date.setText("27.02.2017");
        //Log.d("MyTAG", "date is OK");
        cost.setText("цена: " + s_cost + " р");
        Log.d("MyTAG", "cost is OK");

        buttonBack.setOnClickListener(this);
        buttonTakeOrder.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.buttonTakeOrder:
                Toast.makeText(this, "Заказ принят", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonReturnToSelect:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
            default:
                break;
        }
    }
}
