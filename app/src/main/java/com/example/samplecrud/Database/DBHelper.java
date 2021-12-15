package com.example.samplecrud.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_ENAME = "UserInfo.db";
    public DBHelper(Context context) {
        super(context,DATABASE_ENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE  TABLE " + UserMaster.Users.TABLE_NAME + " (" +
                        UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Users.COLUMN_NAME_NAME + " TEXT," +
                        UserMaster.Users.COLUMN_NAME_AGE + " INTEGER," +
                        UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                        UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT) ";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long addInfo(String name,String age, String userName, String password){

        //Open database as write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_NAME, name);
        values.put(UserMaster.Users.COLUMN_NAME_AGE, age);
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, userName);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        return db.insert(UserMaster.Users.TABLE_NAME, null,values);
    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection ={
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_NAME,
                UserMaster.Users.COLUMN_NAME_AGE,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UserMaster.Users._ID + " DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
    List info = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_NAME));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_AGE));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            info.add(name+","+age+","+username+","+password);
        };
        cursor.close();
        return info;
    }

    public  void updateInfo(View view,String name, String age, String userName, String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_NAME, name);
        values.put(UserMaster.Users.COLUMN_NAME_AGE, age);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String [] selectionArg = {userName};

        int count = db.update(
                UserMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArg
        );

        Snackbar snackbar = Snackbar.make(view, "Update successful", Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();

    }

    public void deleteInfo(View view,String userName){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";

        String[] selectionArg = {userName};

        db.delete(UserMaster.Users.TABLE_NAME,selection,selectionArg);

        Snackbar snackbar = Snackbar.make(view, "Delete successful", Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_FADE);
        snackbar.show();
    }


}
