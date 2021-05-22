package com.example.bs.data;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "bsData";

    // Country table name
    private static final String TABLE_USER= "User";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String USER_FIRST_NAME = "userFirstName";
    private static final String USER_LAST_NAME = "userLastName";
    private static final String USER_MAIL = "userMail";
    private static final String USER_PASSWORD = "userPassword";
    private static final String USER_IS_PARENT = "userIsParent";
    private static final String USER_IS_BS = "userIsBs";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + USER_FIRST_NAME + " TEXT,"
                + USER_LAST_NAME + " TEXT,"
                + USER_MAIL + " TEXT,"
                + USER_PASSWORD + " TEXT,"
                + USER_IS_PARENT + " TEXT,"
                + USER_IS_BS + " TEXT"
                + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new user
   public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_MAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_IS_PARENT, user.getParent());
        values.put(USER_IS_BS, user.getBabySitter());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }


    public User getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
                        USER_FIRST_NAME, USER_LAST_NAME,USER_MAIL,USER_PASSWORD,USER_IS_PARENT,USER_IS_BS}, USER_MAIL + "=? " +
                        " and " + USER_PASSWORD + "=?",
                new String[] { String.valueOf(email), String.valueOf(password)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User();

        try {
            user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    returnBooleanFromInt(cursor.getInt(5)),
                    returnBooleanFromInt(cursor.getInt(6)));
        } catch (CursorIndexOutOfBoundsException e) {

        }
        // return user
        return user;
    }
    Boolean returnBooleanFromInt(int intValue) {
       return intValue == 1;
    }

    // Getting All Users
    public List<User> getAllUsers(String userType) {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + userType + " = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                user.setParent(cursor.getInt(5)==1);
                user.setBabySitter(cursor.getInt(6)==1);
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return User list
        return userList;
    }

    // Updating single User
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_MAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_IS_PARENT, user.getParent());
        values.put(USER_IS_BS, user.getBabySitter());

        // updating row
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    // Deleting single country
    public void deleteCountry(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    // Deleting all users
    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,null,null);
        db.close();
    }

    // Getting users Count
    public int getCountriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}