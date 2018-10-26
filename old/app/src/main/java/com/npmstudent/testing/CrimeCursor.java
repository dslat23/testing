package com.npmstudent.testing;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.npmstudent.testing.crimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by npmstudent on 5/25/16.
 */
public class CrimeCursor extends CursorWrapper {
    public CrimeCursor(Cursor cursor){
        super(cursor);
    }

    public Crime getCrime(){
        String uuid = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));

        Crime c = new Crime(UUID.fromString(uuid));
        c.setTitle(title);
        c.setDate(new Date(date));
        c.setSolved(isSolved != 0);
        return c;
    }
}
