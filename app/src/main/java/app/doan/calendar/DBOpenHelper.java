package app.doan.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_EVENTS_TABLE = "create table  "+DBStructure.EVENT_TAbLE_NAME + "(ID INGTEGER PRIMARY KEY AUTOINCREMENT,)"
            + DBStructure.EVENT + "TEXT," + DBStructure.TIME+"TEXT,"+DBStructure.DATE +"TEXT,"+DBStructure.MONTH+"TEXT,"
            + DBStructure.YEAR+"TEXT,)";
    public static final String DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS"+DBStructure.EVENT_TAbLE_NAME;
    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);

    }
    public void SaveEvent(String event,String time,String date,String moth,String year,SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.EVENT,event);
        contentValues.put(DBStructure.TIME,time);
        contentValues.put(DBStructure.DATE,date);
        contentValues.put(DBStructure.MONTH,moth);
        contentValues.put(DBStructure.YEAR,year);
        database.insert(DBStructure.EVENT_TAbLE_NAME, null,contentValues);

    }
    public Cursor ReadEvents(String date, SQLiteDatabase database)
    {
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.DATE+"=?";
        String [] SelectionArgs = {date};
        return database.query(DBStructure.EVENT_TAbLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }

    public Cursor ReadEventsperMoth(String moth,String year, SQLiteDatabase database)
    {
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.MONTH+"=? and"+DBStructure.YEAR+"=?";
        String [] SelectionArgs = {moth,year};
        return database.query(DBStructure.EVENT_TAbLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
}
