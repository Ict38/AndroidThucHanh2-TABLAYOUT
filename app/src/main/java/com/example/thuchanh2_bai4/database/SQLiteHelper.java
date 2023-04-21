package com.example.thuchanh2_bai4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.thuchanh2_bai4.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bookDB.db";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE books(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "tacgia TEXT," +
                "phamvi TEXT," +
                "doituong TEXT," +
                "danhgia REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", book.getTen());
        contentValues.put("tacgia", book.getTacgia());
        contentValues.put("phamvi", book.getPhamvi());
        contentValues.put("doituong", book.getDoituong());
        contentValues.put("danhgia", book.getRating());
        long result = sqLiteDatabase.insert("books", null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getAllBook() {
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String phamvi = cursor.getString(3);
            String doituong = cursor.getString(4);
            Float price = cursor.getFloat(5);
            Book book = new Book(id,name,author,phamvi,doituong,price);
            bookList.add(book);
        }
        return bookList;
    }

    public long updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", book.getTen());
        contentValues.put("tacgia", book.getTacgia());
        contentValues.put("phamvi", book.getPhamvi());
        contentValues.put("doituong", book.getDoituong());
        Log.d("DOITUONG", book.getDoituong());
        contentValues.put("danhgia", book.getRating());
        long result = sqLiteDatabase.update("books",
                contentValues,"_id=?",new String[]{book.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(int bookId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("books", "_id=?",
                new String[]{bookId+""});
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getStatistic(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<Book> listBook = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("books", new String[]{"_id", "ten", "tacgia", "phamvi", "doituong", "danhgia"},
                null, null, null, null, "danhgia DESC");
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String phamvi = cursor.getString(3);
            String doituong = cursor.getString(4);
            Float rating = cursor.getFloat(5);
            Book book = new Book(id, ten, tacgia, phamvi, doituong, rating);
            listBook.add(book);
        }
        return listBook;
    }
    public List<Book> findBooksByNameOrAuthor(String keyword){
        List<Book> listBook = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books",null,"ten LIKE ? OR tacgia LIKE ?",
                new String[]{"%" + keyword + "%", "%" + keyword + "%"},null,null,"danhgia DESC");
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String phamvi = cursor.getString(3);
            String doituong = cursor.getString(4);
            Float rating = cursor.getFloat(5);
            Book book = new Book(id, ten, tacgia, phamvi, doituong, rating);
            listBook.add(book);
        }
        return listBook;
    }


}
