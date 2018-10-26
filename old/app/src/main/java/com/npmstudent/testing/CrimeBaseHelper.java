package com.npmstudent.testing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.npmstudent.testing.crimeDbSchema.CrimeTable;

/**
 * Created by npmstudent on 5/25/16.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DB_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CrimeTable.name + "(_id integer primary key autoincrement, " + CrimeTable.Cols.UUID + "," + CrimeTable.Cols.TITLE + "," + CrimeTable.Cols.DATE + "," + CrimeTable.Cols.SOLVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){

    }
}
