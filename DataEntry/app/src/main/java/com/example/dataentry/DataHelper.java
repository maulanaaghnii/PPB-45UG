package com.example.dataentry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "snk.db";
    public static final String TABLE_NAME = "snk_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "GENDER";
    public static final String COL_4 = "RANK";
    public static final String COL_5 = "AFFILIATION";
    public static final String COL_6 = "RESIDENCE";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table SNK_table(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, GENDER TEXT, RANK TEXT, AFFILIATION TEXT, RESIDENCE TEXT)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String gender,String rank, String affiliation, String residence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, gender);
        contentValues.put(COL_4, rank);
        contentValues.put(COL_5, affiliation);
        contentValues.put(COL_6, residence);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String name,String gender,String rank, String affiliation, String residence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,gender);
        contentValues.put(COL_4,rank);
        contentValues.put(COL_5,affiliation);
        contentValues.put(COL_6,residence);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


























//end
}
