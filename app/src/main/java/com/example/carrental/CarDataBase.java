package com.example.carrental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CarDataBase extends SQLiteOpenHelper {
    public final static String CAR_TABLE="CAR_TABLE";
    public static final String ID = "ID";
    public static final String CAR_NAME = "CAR_NAME";
    public static final String PASSENGER = "PASSENGER";
    public static final String TYPE = "TYPE";
    public static final String PRICE = "PRICE";
    public static final String IMAGE = "IMAGE";
    public static final String OWNER = "OWMER_NAME";
    private ByteArrayOutputStream ByteArray;
    private byte[] imageByte;
    public static final String DBNAME = "Login.db";
    public static final String TABLENAME = "users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";



    public CarDataBase(@Nullable Context context) {
        super(context, CAR_TABLE, null, 2);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*sqLiteDatabase.execSQL("create Table users(username TEXT primary key, password TEXT)");
        String CreationTable="CREATE TABLE "+CAR_TABLE+ " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_NAME + " TEXT, "
                + PASSENGER + " INT, "
                + TYPE + " TEXT, "
                + PRICE + " INT, "
                + IMAGE + " BLOB )";*/

        String createUsersTable =  "create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT)";
        sqLiteDatabase.execSQL(createUsersTable);

        String createCarTable = "CREATE TABLE " + CAR_TABLE + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAR_NAME + " TEXT, " +
                PASSENGER + " INT, " +
                TYPE + " TEXT, " +
                PRICE + " INT, " +
                IMAGE + " BLOB, " +
                OWNER + " TEXT REFERENCES " + TABLENAME + "(" + COL1 + ")" +
                ")";
        sqLiteDatabase.execSQL(createCarTable);

        //sqLiteDatabase.execSQL(CreationTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CAR_TABLE);
        onCreate(sqLiteDatabase);
    }



    public boolean addOne(Car car){
        SQLiteDatabase db=getWritableDatabase();

//==================================================for image ==========================================
        Bitmap ImageToStore=car.getImage();
        ByteArray=new ByteArrayOutputStream();
        ImageToStore.compress(Bitmap.CompressFormat.JPEG,100,ByteArray);
        imageByte =ByteArray.toByteArray();
//============================================================================================

        ContentValues cv=new ContentValues();

        cv.put(CAR_NAME,car.getName());
        cv.put(PASSENGER,car.getNumberOfPassenger());
        cv.put(TYPE,car.getType());
        cv.put(PRICE,car.getPrice());
        cv.put(IMAGE,imageByte);
        cv.put(OWNER,car.getOwner_name());

        long insert = db.insert(CAR_TABLE,null,cv);

        if (insert==-1)
            return false;
        else{
            return true;}
    }


    public Integer deleteOne(int id){
        boolean flag;
        SQLiteDatabase db=getWritableDatabase();
        String ID= String.valueOf(id);
        return db.delete(CAR_TABLE , "ID = ?" , new String[]{ID});

    }


    public List<Car> getEveryone(){
        List<Car> returnList = new ArrayList<>();
        // get data from database
        String queryString = "Select * from "+ CAR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            // loop through cursor results
            do{
                int CID = cursor.getInt(0); // car ID
                String CName = cursor.getString(1); //car name
                int Cpassenger = cursor.getInt(2);//car number of passenger
                String Ctype = cursor.getString(3);//car type
                int Cprice = cursor.getInt(4);//car number of passenger
                byte[] CimageByte=cursor.getBlob(5);//image in bytes
                Bitmap Object= BitmapFactory.decodeByteArray(CimageByte,0,CimageByte.length);//convert byte array to bitmap
                String Cowner_name = cursor.getString(6); //car name

                Car newCar = new Car(CID, CName, Cpassenger ,Ctype,Cprice,Object,Cowner_name);
                returnList.add(newCar);
            }while (cursor.moveToNext());
        } else{
            // nothing happens. no one is added.
        }
        //close
        cursor.close();
        db.close();
        return returnList;
    }
//-----------------------------------------users----------
public Boolean insertData(String username, String password){
    SQLiteDatabase MyDB = this.getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("username", username);
    contentValues.put("password", password);
    long result = MyDB.insert("users", null, contentValues);
    if(result==-1) return false;
    else
        return true;
}
public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
//---------------- end user -------------------------------------------




