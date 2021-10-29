package com.example.pertemuan5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BantuDatabase extends SQLiteOpenHelper {


    private static final String DATABASE_BUAH = "db_fruit";
    private static final String TABEL_BUAH = "tabel_buah";
    private static final String KODE = "kode";
    private static final String NAMA_BUAH = "nm_buah";

    public BantuDatabase(@Nullable Context context) {
        super(context, DATABASE_BUAH, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nama_table="create table "+TABEL_BUAH+ "(" + KODE + " integer primary key autoincrement, " + NAMA_BUAH + " text)";
        db.execSQL(nama_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean tambahData(String namabuah) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(NAMA_BUAH,namabuah);

        long result=db.insert(TABEL_BUAH,null,contentValues);
        return result != -1;
    }

    public Cursor tampilBuah() {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from "+TABEL_BUAH;
        Cursor cursor=db.rawQuery(sql,null);
        return cursor;
    }

    public boolean hapusRekord(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABEL_BUAH,KODE+"="+id,null)>0;

    }
}
