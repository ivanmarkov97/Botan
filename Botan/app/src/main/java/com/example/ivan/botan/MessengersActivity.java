package com.example.ivan.botan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MessengersActivity extends AppCompatActivity {

    ListView listView;
    final String SUBJECT = "Subject";
    final String DESCRIPTION = "Description";
    final String COST = "Cost";
    final String DATE = "Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messengers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Сообщения");

        listView = (ListView) findViewById(R.id.messengers_list);

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
}
