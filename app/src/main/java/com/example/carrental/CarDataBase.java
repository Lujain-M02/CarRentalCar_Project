package com.example.carrental;

import android.annotation.SuppressLint;
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
    public static final String COL3= "email";//
    public static final String COL4= "phone";//


    public static final String RENTAL_APPLICATION_TABLE = "RENTAL_APPLICATION";
    public static final String RENTER_NAME = "RENTER_NAME";
    public static final String CAR_ID = "CAR_ID";


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

        String createUsersTable =  "create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +//
                COL4 + " TEXT)";//
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

        String createRentalApplicationTable = "CREATE TABLE " + RENTAL_APPLICATION_TABLE + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RENTER_NAME + " TEXT REFERENCES " + TABLENAME + "(" + COL1 + "), " +
                CAR_ID + " INTEGER REFERENCES " + CAR_TABLE + "(" + ID + ")" +
                ")";
        sqLiteDatabase.execSQL(createRentalApplicationTable);


        //sqLiteDatabase.execSQL(CreationTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CAR_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RENTAL_APPLICATION_TABLE);
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
        SQLiteDatabase db=getWritableDatabase();
        String ID= String.valueOf(id);
        if(isRented(id) == false)
        return db.delete(CAR_TABLE , "ID = ?" , new String[]{ID});
        return null;

    }

    public Integer ReturnOne(int id){
        SQLiteDatabase db=getWritableDatabase();
        String ID= String.valueOf(id);
        return db.delete(RENTAL_APPLICATION_TABLE , "ID = ?" , new String[]{ID});

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
                int Cprice = cursor.getInt(4);//car price
                byte[] CimageByte=cursor.getBlob(5);//image in bytes
                Bitmap Object= BitmapFactory.decodeByteArray(CimageByte,0,CimageByte.length);//convert byte array to bitmap
                String Cowner_name = cursor.getString(6); //car owner name

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
public Boolean insertData(String username, String password , String email , String phone){
    SQLiteDatabase MyDB = this.getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("username", username);
    contentValues.put("password", password);
    contentValues.put("email", email);//
    contentValues.put("phone", phone);//
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

//---------------- end user -------------------------------------------


    public List<RentalApplication> getRentals(){
        List<RentalApplication> returnList = new ArrayList<>();
        // get data from database
        String queryString = "Select * from "+ RENTAL_APPLICATION_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            // loop through cursor results
            do{
                int ID = cursor.getInt(0); // ID
                String RName = cursor.getString(1); //renter name
                int CID = cursor.getInt(2); // CAR ID

                RentalApplication newRental = new RentalApplication(ID, RName, CID);
                returnList.add(newRental);
            }while (cursor.moveToNext());
        } else{
            // nothing happens. no one is added.
        }
        //close
        cursor.close();
        db.close();
        return returnList;
    }

    public Car getCarObject(int id){
        Car newCar = null;
        String queryString = "Select * from "+ CAR_TABLE + " Where " + ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            // loop through cursor results
            do{
                String CName = cursor.getString(1); //car name
                int Cpassenger = cursor.getInt(2);//car number of passenger
                String Ctype = cursor.getString(3);//car type
                int Cprice = cursor.getInt(4);//car price
                byte[] CimageByte=cursor.getBlob(5);//image in bytes
                Bitmap Object= BitmapFactory.decodeByteArray(CimageByte,0,CimageByte.length);//convert byte array to bitmap
                String Cowner_name = cursor.getString(6); //car owner name

                newCar = new Car(id, CName, Cpassenger ,Ctype,Cprice,Object,Cowner_name);
            }while (cursor.moveToNext());
        }
        //close
        cursor.close();
        db.close();

        return newCar;


    }

//==================renCar==========================
    public boolean rentCar(RentalApplication rent){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues cv=new ContentValues();

        //cv.put(ID,car.getId());
        //أتأكد لو أحتاج اسم الاونر ولالا
        cv.put(RENTER_NAME,rent.getRenter_name());
        cv.put(CAR_ID,rent.getCar_id());

       long insert = db.insert("RENTAL_APPLICATION",null,cv);
        //long insert = db.insert(RENTAL_APPLICATION_TABLE,null,cv);

        if (insert==-1)
            return false;
        else{
            return true;}
    }

    //=====================check if the car is Rented===================
    public boolean isRented(int id){
        //Car newCar = null;
        String queryString = "Select * from "+ RENTAL_APPLICATION_TABLE + " Where " + CAR_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
        //close
        // cursor.close();
        // db.close();

    }

//=====================check if the car is yours===================
   /* public boolean isYours(String name,String id){
        //Car newCar = null;
        //String queryString = "Select * from "+ CAR_TABLE + " Where " + ID + " = " + id + " AND " + OWNER + " = " + name;

        //String queryString = "Select * from " + CAR_TABLE + " Where " + OWNER + " = " + name + " AND " + ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("Select * from "+ CAR_TABLE + " Where " + OWNER + " = " + name, null);
        Cursor cursor = db.rawQuery("Select * from CAR_TABLE where OWMER_NAME = ? and ID = ?", new String[] {name,id});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
        //close
        // cursor.close();
        // db.close();

    }*/

}