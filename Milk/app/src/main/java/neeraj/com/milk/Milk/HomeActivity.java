package neeraj.com.milk.Milk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class HomeActivity extends Activity {
    public static String id,name,idr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void farmerButton(View v)

    {
        startActivity(new Intent(this,FarmerActivity.class));
    }
    public void paymentButton(View v)
    {
        final EditText et=new EditText(this);
        et.setTextColor(Color.WHITE);
        et.requestFocus();
        et.setHint("Idno");
        et.setHintTextColor(Color.YELLOW);
        final AlertDialog.Builder adb=new AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Dialog_Alert);
        adb.setIcon(R.drawable.milk);
        adb.setTitle("Billing");
        adb.setView(et);
        adb.setCancelable(false);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
             id=et.getText().toString().trim();
             if(id.isEmpty())
             {
                 et.setError("Empty");
             }
             else {
                 try {
                     int idno = Integer.parseInt(id);
                     SQLiteDatabase db = new MilkDatabase(HomeActivity.this).getWritableDatabase();
                     String sel_args[] = {id};
                     String sel = MilkDatabase.Table2_Col1 + "=?";
                     String cols[] = {MilkDatabase.Table2_Col2};
                     Cursor c = db.query(MilkDatabase.Table_Name2, cols, sel, sel_args, null, null, null, null);
                     if (c.moveToFirst()) {
                         name = c.getString(0);
                         startActivity(new Intent(HomeActivity.this, BillingActivity.class));
                     } else {
                         Toast.makeText(HomeActivity.this, "Wrong Id", Toast.LENGTH_SHORT).show();
                     }
                 }catch (Exception e)
                 {
                     Toast.makeText(HomeActivity.this, "Invalid IdNo", Toast.LENGTH_SHORT).show();
                 }
             }
            }
        });
        adb.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.create().show();
    }
    public void settingButton(View v)
    {
        startActivity(new Intent(this,SettingHomeActivity.class));
    }
    public void reportButton(View v)
    {
        final EditText et=new EditText(this);
        et.setTextColor(Color.WHITE);
        et.requestFocus();
        et.setHint("Idno");
        et.setHintTextColor(Color.YELLOW);
        final AlertDialog.Builder adb=new AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Dialog_Alert);
        adb.setIcon(R.drawable.report3);
        adb.setTitle("Reports");
        adb.setView(et);
        adb.setCancelable(false);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                idr=et.getText().toString().trim();
                if(idr.isEmpty())
                {
                    et.setError("Empty");
                }
                else {
                    try {
                        int idno = Integer.parseInt(idr);
                        SQLiteDatabase db = new MilkDatabase(HomeActivity.this).getWritableDatabase();
                        String sel_args[] = {idr};
                        String sel = MilkDatabase.Table2_Col1 + "=?";
                        String cols[] = {MilkDatabase.Table2_Col2};
                        Cursor c = db.query(MilkDatabase.Table_Name2, cols, sel, sel_args, null, null, null, null);
                        if (c.moveToFirst()) {
                            name = c.getString(0);
                            startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                        } else {
                            Toast.makeText(HomeActivity.this, "Wrong Id", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(HomeActivity.this, "Invalid IdNo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        adb.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.create().show();
    }
}
