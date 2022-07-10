package com.example.basicandroidconcepts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String LOCATIONS_TABLE = "LOCATIONS_TABLE";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "locations.db", null, 1);
    }

    //Method is called the first time a database is accessed. There should be code to create a database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LOCATIONS_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " VARCHAR(50), " + COLUMN_DESCRIPTION + " VARCHAR(300) )";

        db.execSQL(createTableStatement);
    }

    //Method is called if the database version number is changed. Prevents previous users apps from breaking when you change database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(LocationsModel locationsModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, locationsModel.getName());
        cv.put(COLUMN_DESCRIPTION, locationsModel.getDescription());

        long insert = db.insert(LOCATIONS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else{
            return true;
        }

    }

    public List<LocationsModel> getLocations() {
        List<LocationsModel> returnlist = new ArrayList<>();

        //get data from the database
        String queryString = "SELECT * FROM " + LOCATIONS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do{
                int locationID = cursor.getInt(0);
                String locationName = cursor.getString(1);
                String locationDescription = cursor.getString(2);

                LocationsModel newLocation = new LocationsModel(locationID, locationName, locationDescription);
                returnlist.add(newLocation);

            } while(cursor.moveToNext());
        }else {

        }

        cursor.close();
        db.close();
        return returnlist;
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = COLUMN_NAME + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(LOCATIONS_TABLE);

        Cursor cursor = builder.query(this.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LOCATIONS_TABLE,null);
        return res;
    }

}
