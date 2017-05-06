package com.example.ivan.botan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class AddOrderActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    TextView dateTextView;
    Button buttonPhoto;
    Button buttonCategory;
    Button buttonType;
    Button buttonTime;
    Button buttinOK;
    ImageView[] imageViewPhotos;

    int countDownLoadedPhotos = 0;
    String[] types;
    String[] categoties;

    int year;
    int month;
    int day;

    int chosenImageView;

    final int REQUEST = 1;
    final int CATEGORY_REQUEST = 2;
    final int TYPE_REQUEST = 3;
    final int TYPE_DIALOG = 4;
    final int CATEGORY_DIALOG = 5;
    final int DATE_DIALOG = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        buttonPhoto.setOnClickListener(this);

        buttonCategory = (Button) findViewById(R.id.buttonCategory);
        buttonCategory.setOnClickListener(this);

        buttonType = (Button) findViewById(R.id.buttonType);
        buttonType.setOnClickListener(this);

        buttonTime = (Button) findViewById(R.id.buttonTime);
        buttonTime.setOnClickListener(this);

        buttinOK = (Button) findViewById(R.id.buttonOkOrder);
        buttinOK.setOnClickListener(this);

        imageViewPhotos = new ImageView[3];
        imageViewPhotos[0] = (ImageView) findViewById(R.id.imageView1);
        imageViewPhotos[1] = (ImageView) findViewById(R.id.imageView2);
        imageViewPhotos[2] = (ImageView) findViewById(R.id.imageView3);

        imageViewPhotos[0].setOnClickListener(this);
        imageViewPhotos[1].setOnClickListener(this);
        imageViewPhotos[2].setOnClickListener(this);

        types = getResources().getStringArray(R.array.orderType);
        categoties = getResources().getStringArray(R.array.orderCategory);

        Date date = new Date();

        year = date.getYear() + 1900;
        month = date.getMonth();
        day = date.getDay();

        dateTextView = (TextView) findViewById(R.id.dateTextView);
        dateTextView.setText(day + "." + month + "." + year);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Добавить заказ");

        editText = (EditText) findViewById(R.id.DetailsEditText);
        editText.setText(getResources().getString(R.string.testText));
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
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        switch(id){
            case TYPE_DIALOG:
                alertDialogBuilder.setTitle("Тип задания");
                alertDialogBuilder.setSingleChoiceItems(types, -1, typeOnClickListener);
                alertDialogBuilder.setPositiveButton("OK", typeOnClickListener);
                break;
            case CATEGORY_DIALOG:
                alertDialogBuilder.setTitle("Категория");
                alertDialogBuilder.setSingleChoiceItems(categoties, -1, categoryOnClickListener);
                alertDialogBuilder.setPositiveButton("OK", categoryOnClickListener);
                break;
            case DATE_DIALOG:
                DatePickerDialog datePicketDialog = new DatePickerDialog(this, myDatePickerListener, year, month, day);
                return datePicketDialog;
        }
        return alertDialogBuilder.create();
    }

    DialogInterface.OnClickListener typeOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView listView = ((AlertDialog) dialog).getListView();
            if(which == dialog.BUTTON_POSITIVE){
                buttonType.setText(types[listView.getCheckedItemPosition()]);
            }
        }
    };

    DialogInterface.OnClickListener categoryOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView listView = ((AlertDialog)dialog).getListView();
            if(which == dialog.BUTTON_POSITIVE){
                String string = categoties[listView.getCheckedItemPosition()];
                String helper = "";
                if(string.length() > 15){
                    for(int i = 0; i < 15; i++) {
                        helper += string.charAt(i);
                    }
                    helper += "...";
                    buttonCategory.setText(helper);
                }
                else
                    buttonCategory.setText(categoties[listView.getCheckedItemPosition()]);
            }
        }
    };

    DatePickerDialog.OnDateSetListener myDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int c_year, int c_month, int c_dayOfMonth) {
            year = c_year;
            month = c_month;
            day = c_dayOfMonth;
            dateTextView.setText(day + "." + month + "." + year);
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.buttonPhoto:
               chosenImageView = 2;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST);
                break;
            case R.id.buttonType:
                showDialog(TYPE_DIALOG);
                break;
            case R.id.buttonCategory:
                showDialog(CATEGORY_DIALOG);
                break;
            case R.id.buttonTime:
                showDialog(DATE_DIALOG);
                break;
            case R.id.buttonOkOrder:
                Toast.makeText(this, "Заказ добавлен", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageView1:
                chosenImageView = 0;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST);
                break;
            case R.id.imageView2:
                chosenImageView = 1;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST);
                break;
            case R.id.imageView3:
                chosenImageView = 2;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap img = null;

        if(requestCode == REQUEST && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            try{
                img = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if(countDownLoadedPhotos < 3){

                imageViewPhotos[chosenImageView].setImageBitmap(img);
                //countDownLoadedPhotos++;
            //}
        }

        if(requestCode == CATEGORY_REQUEST){

        }

        if(requestCode == TYPE_REQUEST){

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
