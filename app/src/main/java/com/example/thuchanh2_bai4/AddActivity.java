package com.example.thuchanh2_bai4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchanh2_bai4.database.SQLiteHelper;
import com.example.thuchanh2_bai4.model.Book;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    EditText ten;
    Spinner tacgia;
    RadioButton rd1 , rd2;
    CheckBox cb1, cb2, cb3;
    RatingBar ratingBar;
    Button luu, huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        initView();
        luu.setOnClickListener(this);
        huy.setOnClickListener(this);

    }

    private void initView() {
        ten = findViewById(R.id.edName);
        rd1 = findViewById(R.id.hoc);
        rd2 = findViewById(R.id.tracuu);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        ratingBar = findViewById(R.id.rating);
        luu = findViewById(R.id.btnLuu);
        huy = findViewById(R.id.btnHuy);
        tacgia = findViewById(R.id.spAuthor);
        tacgia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia)));
    }

    @Override
    public void onClick(View view) {
        if(view == luu){
            String sTen = ten.getText().toString();
            String sTacgia = tacgia.getSelectedItem().toString();
            String phamvi;
            if(rd1.isChecked()) phamvi = "hoc";
            else phamvi = "tracuu";

            String doituong = "";
            if(cb1.isChecked()) doituong += "CN, ";
            if(cb2.isChecked()) doituong += "DT, ";
            if(cb3.isChecked()) doituong += "VT, ";

            Float rating = ratingBar.getRating();
            Book bookToAdd = new Book(sTen,sTacgia,phamvi,doituong,rating);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addBook(bookToAdd);
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(view == huy){
            finish();
        }
    }
}