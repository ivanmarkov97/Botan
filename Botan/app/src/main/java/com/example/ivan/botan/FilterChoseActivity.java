package com.example.ivan.botan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterChoseActivity extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener{

    ListView listView;
    Button buttonOKFilter;
    String[] filter_categories;
    String[] types;
    final String FILTER_TITLE = "title";
    final int FILTER_SORT = 0;
    final int FILTER_CATEGORY = 1;
    final int FILTER_TYPE = 2;
    String FILTER_STATUS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_chose);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Фильтр");

        filter_categories = getResources().getStringArray(R.array.filter_categories);

        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>(filter_categories.length);
        Map<String, Object> map;
        for (int i = 0; i < filter_categories.length; i++) {
            map = new HashMap<String, Object>();
            map.put(FILTER_TITLE, filter_categories[i]);
            arrayList.add(map);
        }

        String[] from = {FILTER_TITLE};
        int[] to = {R.id.textViewFilterTitle};

        listView = (ListView) findViewById(R.id.filter_choose_list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.filter_item, from, to);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);

        buttonOKFilter = (Button) findViewById(R.id.buttonOKFilter);
        buttonOKFilter.setOnClickListener(this);
    }

    DialogInterface.OnClickListener onClickListenerSort = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView list = ((AlertDialog) dialog).getListView();
            TextView texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
            if(which == dialog.BUTTON_POSITIVE){
                texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
                Log.d("MyTAG",(getResources().getStringArray(R.array.filter_sort_items))[list.getCheckedItemPosition()]);
                FILTER_STATUS = FILTER_STATUS + "#" + (getResources().getStringArray(R.array.filter_sort_items))[list.getCheckedItemPosition()] + " ";
                texViewStatus.setText(FILTER_STATUS);
            }
        }
    };

    DialogInterface.OnClickListener onClickListenerType = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView list = ((AlertDialog) dialog).getListView();
            TextView texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
            if(which == dialog.BUTTON_POSITIVE){
                texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
                Log.d("MyTAG",(getResources().getStringArray(R.array.orderType))[list.getCheckedItemPosition()]);
                FILTER_STATUS = FILTER_STATUS + "#" + (getResources().getStringArray(R.array.orderType))[list.getCheckedItemPosition()] + " ";
                texViewStatus.setText(FILTER_STATUS);
            }
        }
    };

    DialogInterface.OnClickListener onClickListenerCategory = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView list = ((AlertDialog) dialog).getListView();
            TextView texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
            if(which == dialog.BUTTON_POSITIVE){
                texViewStatus = (TextView) findViewById(R.id.textViewFilterStatus);
                Log.d("MyTAG",(getResources().getStringArray(R.array.filter_subjects_items))[list.getCheckedItemPosition()]);
                FILTER_STATUS = FILTER_STATUS + "#" + (getResources().getStringArray(R.array.filter_subjects_items))[list.getCheckedItemPosition()] + " ";
                texViewStatus.setText(FILTER_STATUS);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainMenuActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        Log.d("MyTAG", "createDialog");
        switch (id){
            case FILTER_SORT:
                alertDialog.setTitle("Сортировка");
                alertDialog.setSingleChoiceItems(getResources().getStringArray(R.array.filter_sort_items), -1, onClickListenerSort);
                alertDialog.setPositiveButton("OK", onClickListenerSort);
                break;
            case FILTER_TYPE:
                alertDialog.setTitle("Тип задания");
                alertDialog.setSingleChoiceItems(getResources().getStringArray(R.array.orderType), -1, onClickListenerType);
                alertDialog.setPositiveButton("OK", onClickListenerType);
                break;
            case FILTER_CATEGORY:
                alertDialog.setTitle("Категория");
                alertDialog.setSingleChoiceItems(getResources().getStringArray(R.array.filter_subjects_items), -1, onClickListenerCategory);
                alertDialog.setPositiveButton("OK", onClickListenerCategory);
                break;
        }
        return alertDialog.create();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.d("MyTAG", "Postion = " + position);
        switch (position) {
            case 0:
                showDialog(FILTER_SORT);
                Log.d("MyTAG", "Postion = " + position);
                break;
            case 1:
                showDialog(FILTER_TYPE);
                Log.d("MyTAG", "Postion = " + position);
                break;
            case 2:
                showDialog(FILTER_CATEGORY);
                Log.d("MyTAG", "Postion = " + position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonOKFilter){
            startActivity(new Intent(this, MainMenuActivity.class));
        }
    }
}
