package com.example.ivan.botan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ListView.OnItemClickListener {

    Button buttonAddOrder;
    ListView listView;
    final String SUBJECT = "Subject";
    final String DESCRIPTION = "Description";
    final String COST = "Cost";
    final String DATE = "Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        buttonAddOrder = (Button) findViewById(R.id.buttonAddOrder);
        buttonAddOrder.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.all_orders_list);
        listView.setOnItemClickListener(this);

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

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, orders,
                R.layout.cur_order_item, new String[] {SUBJECT, DESCRIPTION, COST, DATE},
                new int[] {R.id.textViewSubject, R.id.textViewDescription, R.id.textViewCost, R.id.textViewDate}
        );
        listView.setAdapter(simpleAdapter);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
        switch(id){
            case R.id.action_filter:
                Intent intent = new Intent("filter_choose");
                Log.d("MyTAG", "Нажат фильт");
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        if (id == R.id.current_orders) {
            // Handle the camera action
            intent = new Intent("current_orders");

        } else if (id == R.id.done_orders) {

            intent = new Intent("done_orders");

        } else if (id == R.id.messengers) {

            intent = new Intent("messengers");

        } else if (id == R.id.nav_settings) {

            intent = new Intent("nav_settings");

        } else if (id == R.id.nav_exit) {

        } else if (id == R.id.id_profile){
            Log.d("MyTAG", "Profile pressed");
            intent = new Intent("Profile");
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        startActivity(intent);

        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonAddOrder){
            startActivity(new Intent("addOrder"));
        }
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
