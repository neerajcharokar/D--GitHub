package neeraj.com.milk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Neeraj on 07-Dec-17.
 */

public class MilkDatabase extends SQLiteOpenHelper {
    public static final String Table_Name1="user_register";
    public static final String Table_Col1="NAME";
    public static final String Table_Col2="CONTACT_NO";
    public static final String Table_Col3="EMAIL_ID";
    public static final String Table_Col4="OTP";
    public static final String Table_Col5="CENTER_NAME";
    public static final String Table_Col6="FAT_RATE";

    public static final String Table_Name2="farmer_details";
    public static final String Table2_Col1="IDNO";
    public static final String Table2_Col2="NAME";
    public static final String Table2_Col3="CONTACT_NO";
    public static final String Table2_Col4="FARMER_IMAGE";

    public static final String Table_Name3="billing";
    public static final String Table3_Col1="Bill_Date";
    public static final String Table3_Col2="Bill_NO";
    public static final String Table3_Col3="Farmer_Id";
    public static final String Table3_Col4="FAT";
    public static final String Table3_Col5="LITERS";
    public static final String Table3_Col6="DAY";
    public static final String Table3_Col7="Total";
    public static final String Table3_Col8="ADVANCE";

    public static final String Table_Name4="payments";
    public static final String Table4_Col1="FARMER_ID";
    public static final String Table4_Col2="TOTAL";
    public static final String Table4_Col3="ADVANCE";
    public static final String Table4_Col4="BALANCE";

    public static final String Table_Name5="advance_payments";
    public static final String Table5_Col1="FARMER_ID";
    public static final String Table5_Col2="ADVANCE_DATE";
    public static final String Table5_Col3="ADVANCE";
    public static final int version=1;
    public static final String  DBname="MilkDatabase";
    public MilkDatabase(Context context) {
        super(context, DBname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1="create table "+Table_Name1+"("+Table_Col1+" TEXT,"+Table_Col2+" NUMBER,"+Table_Col3+" TEXT,"+Table_Col4+" TEXT,"+Table_Col5+" TEXT,"+Table_Col6+" REAL)";
        db.execSQL(qry1);
        String qry2="create table "+Table_Name2+"("+Table2_Col1+" NUMBER,"+Table2_Col2+" TEXT,"+Table2_Col3+" NUMBER,"+Table2_Col4+" BLOB)";
        db.execSQL(qry2);
        String qry3="create table "+Table_Name3+"("+Table3_Col1+" TEXT,"+Table3_Col2+" NUMBER,"+Table3_Col3+" NUMBER,"+Table3_Col4+" REAL,"+Table3_Col5+" REAL,"+Table3_Col6+" TEXT,"+Table3_Col7+" REAL,"+Table3_Col8+" REAL)";
        db.execSQL(qry3);
        String qry4="create table "+Table_Name4+"("+Table4_Col1+" NUMBER,"+Table4_Col2+" REAL,"+Table4_Col3+" REAL,"+Table4_Col4+" REAL)";
        db.execSQL(qry4);
        String qry5="create table "+Table_Name5+"("+Table5_Col1+" NUMBER,"+Table5_Col2+" TEXT,"+Table5_Col3+" REAL)";
        db.execSQL(qry5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
