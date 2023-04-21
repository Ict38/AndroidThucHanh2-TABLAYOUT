package com.example.thuchanh2_bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.thuchanh2_bai4.database.SQLiteHelper;
import com.example.thuchanh2_bai4.model.Book;

public class UpdateAndDelete extends AppCompatActivity implements View.OnClickListener {

    EditText ten;
    Spinner tacgia;
    RadioButton rd1 , rd2;
    CheckBox cb1, cb2, cb3;
    RatingBar ratingBar;
    Button luu,xoa, huy;

    Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);


        ten = findViewById(R.id.edName);
        rd1 = findViewById(R.id.hoc);
        rd2 = findViewById(R.id.tracuu);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        ratingBar = findViewById(R.id.rating);
        luu = findViewById(R.id.btnUpdate);
        huy = findViewById(R.id.btnHuy);
        xoa = findViewById(R.id.btnXoa);
        tacgia = findViewById(R.id.spAuthor);
        tacgia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia)));
        luu.setOnClickListener(this);
        huy.setOnClickListener(this);
        xoa.setOnClickListener(this);

        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("book");

        ten.setText(book.getTen());
        if(book.getPhamvi().equals("hoc")) rd1.setChecked(true);
        else rd2.setChecked(true);
        String[] tmp = book.getDoituong().split(", ");
        String doituongSTring = "";
        for(String i : tmp){
            if(i.equals("CN")) {
                cb1.setChecked(true);
                Log.d("CN?", "TRUE");
            }
            if(i.equals("DT")) {
                cb2.setChecked(true);
                Log.d("DT?", "TRUE");
            }
            if(i.equals("VT")) {
                cb3.setChecked(true);
                Log.d("VT?", "TRUE");
            }
        }
        Log.d("DoiTUongSTRUNG", doituongSTring );
        ratingBar.setRating(book.getRating());
        for(int i=0;i<tacgia.getCount();i++){
            if(book.getTacgia().equals(tacgia.getItemAtPosition(i))){
                tacgia.setSelection(i);
                break;
            }
        }

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
            Book bookToUpdate = new Book(book.getId(), sTen,sTacgia,phamvi,doituong,rating);
            Log.d("BOOK UPDATE", bookToUpdate.toString());
            SQLiteHelper db = new SQLiteHelper(this);
            db.updateBook(bookToUpdate);
            Intent intent = new Intent(UpdateAndDelete.this, MainActivity.class);
            startActivity(intent);
        }
        if(view == huy){
            finish();
        }
        if(view == xoa){
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateAndDelete.this);
            sqLiteHelper.deleteBook(book.getId());
            Intent intentToMainActivity = new Intent(UpdateAndDelete.this, MainActivity.class);
            startActivity(intentToMainActivity);
        }
    }
}