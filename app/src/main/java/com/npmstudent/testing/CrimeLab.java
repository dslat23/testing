package com.npmstudent.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.npmstudent.testing.crimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by npmstudent on 5/24/16.
 */
public class CrimeLab {
    private static CrimeLab crimeLab;
    private Context context;
    private SQLiteDatabase db;

    public static CrimeLab get(Context context){
        if(crimeLab == null){
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    private CrimeLab(Context context) {
        context = context.getApplicationContext();
        db = new CrimeBaseHelper(context).getWritableDatabase();
        }

        public List<Crime> getCrimes(){
            List<Crime> list = new ArrayList<>();
            CrimeCursor c = queryCrimes(null, null);

            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    list.add(c.getCrime());
                    c.moveToNext();
                }
            }finally{
                c.close();
        }
        return list;
    }

    public Crime getCrime(UUID id){
        CrimeCursor c = queryCrimes(CrimeTable.Cols.UUID + " + ?", new String[] {id.toString()});
        try{
            if(c.getCount() == 0){
                return null;
            }
            c.moveToFirst();
            return c.getCrime();
        }finally{
            c.close();
        }
    }

    private CrimeCursor queryCrimes(String where, String[] whereArgs){
        Cursor cursor = db.query(
          CrimeTable.name,
          null,
          where,
          whereArgs,
          null,
          null,
          null
        );
        return new CrimeCursor(cursor);
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);
        db.insert(CrimeTable.name, null, values);
    }

    public void updateCrime(Crime c){
        String uuid = c.getId().toString();
        ContentValues values = getContentValues(c);
        db.update(CrimeTable.name, values, CrimeTable.Cols.UUID + " + ?", new String[] {uuid});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }
}
