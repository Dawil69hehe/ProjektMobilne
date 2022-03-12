package com.example.sqlitedw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.text.format.Time;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="DBase.db";
    public static final String TABLE_NAME="Tabela";
    public static final String COL_2="EMAIL";
    public static final String COL_3="INFO";
    public static final String COL_4="PRICE";
    public static final String COL_5="NAME";
    public static final String COL_6="DATA1";

    public DataBaseHelper (@NonNull Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(EMAIL TEXT, INFO TEXT, PRICE INT, NAME TEXT, DATA1 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP table IF EXISTS "+TABLE_NAME);
    }

    public boolean insertdata(String name, String info, int cena, String name2){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, info);
        contentValues.put(COL_4, cena);
        contentValues.put(COL_5, name2);
        contentValues.put(COL_6, LocalDate.now()+" "+LocalTime.now() );
        long sucess=db.insert(TABLE_NAME, null, contentValues);
        if (sucess==1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAlldata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);
        return cursor;
    }

}
