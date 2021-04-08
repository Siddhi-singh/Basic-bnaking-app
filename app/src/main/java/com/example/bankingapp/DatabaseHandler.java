package com.example.bankingapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="martialArtDatabase";
    public static final int DATABASE_VERSION=1;
    public static final String USER_TABLE="UserTable";
    public static final String ID_KEY="id";
    public static final String NAME_KEY="name";
    public static final String AMOUNT_KEY="amount";
    public static final String EMAIL_KEY="email";
    public static final String PHONE_KEY="number";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseSQL = "create table " + USER_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + AMOUNT_KEY + " real" +
                ", " + EMAIL_KEY + " text" + ", "+ PHONE_KEY + " integer" + " )";
        db.execSQL(createDatabaseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" drop table if exists "+ USER_TABLE);
            onCreate(db);
    }

    public void addMartialArt(UserD user){
        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + USER_TABLE +
                " values(null, '" + user.getName()
                + "', '" + user.getAmount() +
                "', '" + user.getEmail() + "', '"+user.getPhone() +
                "')";
        database.execSQL(addMartialArtSQLCommand);
        database.close();

    }

    public ArrayList getAllObjects(){
        ArrayList<UserD> array=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String getObject="select * from "+ USER_TABLE;
        Cursor cursor= db.rawQuery(getObject,null);
        if(cursor.moveToFirst()!=false){
            do{
                UserD us=new UserD(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Double.parseDouble(cursor.getString(2)),
                        cursor.getString(3)
                        ,Integer.parseInt(cursor.getString(4)));
                array.add(us);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return array;
    }

    public UserD returnMartialObjectByID(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + USER_TABLE +
                " where " + ID_KEY + " = " + id;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);
        UserD user=null;
        if(cursor.moveToFirst()){
            user=new UserD(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)));
        }
        database.close();
        return user;
    }

    public void modifyUser(int id,String name, double amount, String email, int phone){
        SQLiteDatabase db=this.getWritableDatabase();
        String modify= "update "+USER_TABLE+ " set "+NAME_KEY+" = '"+name+"', "+AMOUNT_KEY+" = '"+amount+"', "+EMAIL_KEY+" = '"+email+"', "+PHONE_KEY+" = '"+phone+"' "+
                "where "+ID_KEY+" = "+ id;
        db.execSQL(modify);
        db.close();
    }

}
