package com.example.sqlitedatainsertanddeleteapp.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME ="User.db";
    public static String TABLE_NAME ="User";
    public static String COL_ID ="Id";
    public static String COL_NAME ="Name";
    public static String COL_AGE ="Age";
    public static int VERSION =1;


    public static String CREATE_TABLE = "create table "+TABLE_NAME+"(Id integer primary key, Name Text,Age Text)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


      /*  //Update Kaj korte hoba */

    }

    public long insertData(String name,String age){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return id;

    }

    public Cursor showData(){
        String showAll ="Select * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(showAll,null);
        return cursor;

    }


    public void deleteData(int id){

        getWritableDatabase().delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(id)});
    }

}
