package com.xlirik.hiragana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "resultsManager";
    private static final String TABLE_RESULTS = "results";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "result";
    private static final String KEY_PH_NO = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RESULTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);

        onCreate(db);
    }

    public void addResult(Results contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getResult());
        values.put(KEY_PH_NO, contact.getTime());

        db.insert(TABLE_RESULTS, null, values);
        db.close();
    }

    public Results getResult(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESULTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, KEY_ID + "DESC");

        if (cursor != null){
            cursor.moveToFirst();
        }

        Results result = new Results(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return result;
    }

    public List<Results> getAllContacts() {
        List<Results> contactList = new ArrayList<Results>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESULTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Results contact = new Results();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setResult(cursor.getString(1));
                contact.setTime(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
// refactor
    public int updateContact(Results contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getResult());
        values.put(KEY_PH_NO, contact.getTime());

        return db.update(TABLE_RESULTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    public void deleteContact(Results contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESULTS, KEY_ID + " = ?", new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESULTS, null, null);
        db.close();
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RESULTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}