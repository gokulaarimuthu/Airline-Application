package com.example.tabbed;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "EJ.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(Firstname TEXT ,Lastname TEXT,Mail TEXT PRIMARY KEY,Password TEXT, Contact TEXT, DOB TEXT,Gender TEXT)");

        DB.execSQL("create Table Contact(Name TEXT,Email TEXT,Message TEXT)");

        DB.execSQL("create Table Subscribe(Name TEXT,Email TEXT PRIMARY KEY)");

        DB.execSQL("create Table Feedback(Name TEXT,Email TEXT,Feedback TEXT,Rating TEXT)");

        DB.execSQL("create Table Booking(Firstname TEXT ,Lastname TEXT,Mail TEXT,Contact TEXT, Departure TEXT, Arrival TEXT, Departuredate TEXT ,Arrivaldate TEXT,Passengercount TEXT,Triptype TEXT,Passengerstype TEXT,Cabinclass TEXT,Paymenttype TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists Contact");
        DB.execSQL("drop Table if exists Subscribe");
        DB.execSQL("drop Table if exists Feedback");
        DB.execSQL("drop Table if exists Booking");
    }

    public Boolean insertcontactdata(String name,String email, String message)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("Message", message);
        long result=DB.insert("Contact", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertbookingdata(String fname,String lname, String email, String contact,String departure,String arrival,String departdate,String arrivaldate,String totalpassenger,String triptype,String passengerstype,String cabinclass,String paymenttype)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", fname);
        contentValues.put("Lastname", lname);
        contentValues.put("Mail", email);
        contentValues.put("Contact", contact);
        contentValues.put("Departure", departure);
        contentValues.put("Arrival", arrival);
        contentValues.put("Departuredate", departdate);
        contentValues.put("Arrivaldate", arrivaldate);
        contentValues.put("Passengercount", totalpassenger);
        contentValues.put("Triptype", triptype);
        contentValues.put("Passengerstype", passengerstype);
        contentValues.put("Cabinclass", cabinclass);
        contentValues.put("Paymenttype", paymenttype);
        long result=DB.insert("Booking", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertsubscribedata(String name,String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        long result=DB.insert("Subscribe", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertfeedbackdata(String name,String email,String feedback, String rating)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("Feedback", feedback);
        contentValues.put("Rating", rating);
        long result=DB.insert("Feedback", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }



    public Boolean insertuserdata(String fname,String lname,String mail,String password, String contact, String dob,String gender)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", fname);
        contentValues.put("Lastname", lname);
        contentValues.put("Mail", mail);
        contentValues.put("Password", password);
        contentValues.put("Contact", contact);
        contentValues.put("DOB", dob);
        contentValues.put("Gender", gender);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateuserdata(String fname,String lname,String mail,String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", fname);
        contentValues.put("Lastname", lname);
        contentValues.put("Mail", mail);
        contentValues.put("Password", password);
        /*
        contentValues.put("Contact", contact);
        contentValues.put("DOB", dob);
        contentValues.put("Gender", gender);
         */
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ?", new String[]{mail});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "mail=?", new String[]{mail});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Boolean updateuserprofiledata(String fname,String lname,String mail,String password,String contact,String dob, String gender) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", fname);
        contentValues.put("Lastname", lname);
        contentValues.put("Mail", mail);
        contentValues.put("Password", password);
        contentValues.put("Contact", contact);
        contentValues.put("DOB", dob);
        contentValues.put("Gender", gender);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ?", new String[]{mail});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "mail=?", new String[]{mail});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }



    public Boolean deletedata (String mail)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ?", new String[]{mail});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "mail=?", new String[]{mail});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

    public Cursor getdataprofile (String mail)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ?", new String[]{mail});
        return cursor;
    }


    public Cursor check(String mail,String password)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ? and password= ?", new String[]{mail,password});
        return  cursor;
    }
    public Cursor check1(String mail)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mail = ?", new String[]{mail});
        return  cursor;
    }
}