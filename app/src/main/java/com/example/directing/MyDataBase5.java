package com.example.directing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase5 extends SQLiteOpenHelper {

    public MyDataBase5(Context context, String name, SQLiteDatabase.CursorFactory factory,

                      int version) {

        super(context, name, factory, version);

        // TODO Auto-generated constructor stub

    }



    public void onCreate(SQLiteDatabase db) {

        // TODO Auto-generated method stub

        db.execSQL("create table wallimage1 (id text,image blob, description text);");

    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub



    }



}


