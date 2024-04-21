package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase database;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "MultiTables.db", null,1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//    sqLiteDatabase = getWritableDatabase();
    sqLiteDatabase.execSQL("create table Employee(id integer primary key AUTOINCREMENT,name text,email text);");
    sqLiteDatabase.execSQL("create table Manager(id integer primary key AUTOINCREMENT,name text,email text);");
    sqLiteDatabase.execSQL("create table Director(id integer primary key AUTOINCREMENT,name text,email text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

public boolean insertEmp(String name,String email){
    ContentValues cv = new ContentValues();
    cv.put("name",name);
    cv.put("email",email);

    long res = database.insert("Employee",null,cv);
    if(res==-1)
        return false;
    else
        return true;
}

public boolean insertMan(String name,String email){
    ContentValues cv = new ContentValues();
    cv.put("name",name);
    cv.put("email",email);

    long res = database.insert("Manager",null,cv);
    if(res==-1)
        return false;
    else
        return true;
}

public  boolean insertDir(String name,String email){
    ContentValues cv = new ContentValues();
    cv.put("name",name);
    cv.put("email",email);

    long res = database.insert("Director",null,cv);
    if(res==-1)
        return false;
    else
        return true;
}

public Cursor showEmprec(){

        Cursor result = database.rawQuery("select * from Employee",null);
        if (result.getCount()!=0)
            return result;
        else
            return result;

}

public Cursor showManrec(){
    Cursor result = database.rawQuery("select * from Manager",null);
    if (result.getCount()!=0)
        return result;
    else
        return result;
}
public Cursor showDir(){
    Cursor result = database.rawQuery("select * from Director",null);
    if (result.getCount()!=0)
        return result;
    else
        return result;
}
}
