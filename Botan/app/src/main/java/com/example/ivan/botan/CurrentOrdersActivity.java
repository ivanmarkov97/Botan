package com.example.ivan.botan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CurrentOrdersActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    ListView listView;
    final String SUBJECT = "Subject";
    final String DESCRIPTION = "Description";
    final String COST = "Cost";
    final String DATE = "Date";
    //ArrayList<HashMap<String, Object>> allOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        listView = (ListView) findViewById(R.id.current_orders);
        listView.setOnItemClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Текущие заказы");

        int num = 100;
        ArrayList<HashMap<String, Object>> orders = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map;

        for(int i = 0; i < num; i++){
            map = new HashMap<String, Object>();
            map.put(SUBJECT, "Математика");
            map.put(DESCRIPTION, getResources().getString(R.string.testText));
            map.put(COST, 100*i + " p");

            Date currentDate;
            currentDate = new Date();
            Long time = currentDate.getTime();
            long anotherDate = (long)Math.random()*2;
            time = time + (60*60*24*1000*anotherDate);
            currentDate = new Date(time);
            Locale local = new Locale("ru","RU");
            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

            map.put(DATE, df.format(currentDate));

            orders.add(map);
        }

       // allOrders = orders;
        //Log.d("MyTAG", orders.get(0).get(DATE).toString());


        SimpleAdapter simpleAdapter = new SimpleAdapter(this, orders,
                R.layout.cur_order_item, new String[] {SUBJECT, DESCRIPTION, COST, DATE},
                new int[] {R.id.textViewSubject, R.id.textViewDescription, R.id.textViewCost, R.id.textViewDate}
        );
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Log.v("MyTAG", "нажата HOME");
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.d("MyTAG", listView.getItemAtPosition(position));
        HashMap<String, Object>  hash = (HashMap<String, Object>)listView.getItemAtPosition(position);
        //Log.d("MyTAG", hash.get(DESCRIPTION).toString());
        Intent intent = new Intent(this, OrderShowActivity.class);
        intent.putExtra("subject", hash.get(SUBJECT).toString());
        Log.d("MyTAG", hash.get(SUBJECT).toString());
        intent.putExtra("description", hash.get(DESCRIPTION).toString());
        Log.d("MyTAG", hash.get(DESCRIPTION).toString());
        intent.putExtra("date", hash.get(DATE).toString());
        Log.d("MyTAG", hash.get(DATE).toString());
        intent.putExtra("cost", hash.get(COST).toString());
        Log.d("MyTAG", hash.get(COST).toString());
        startActivity(intent);
    }
}
