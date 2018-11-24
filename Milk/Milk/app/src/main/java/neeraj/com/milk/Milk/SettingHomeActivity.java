package neeraj.com.milk.Milk;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class SettingHomeActivity extends Activity
{
    Button bt1,bt2,bt3,bt4;
    TextView tv;
    EditText et1,et2,et3;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_home);
        bt1=findViewById(R.id.setting_add_rate);
        tv=findViewById(R.id.tv);
        et1=findViewById(R.id.setting_fat_rate);
        bt2=findViewById(R.id.setting_save_fat);
        bt3=findViewById(R.id.setting_add_milk_center);
        et2=findViewById(R.id.setting_center_name);
        et3=findViewById(R.id.setting_contact_no);
        db=new MilkDatabase(this).getWritableDatabase();
        bt4=findViewById(R.id.setting_save_center);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                String col[]={MilkDatabase.Table_Col6};
                Cursor c=db.query(MilkDatabase.Table_Name1,col,null,null,null,null,null);
                if(c.moveToFirst())
                {
                    bt2.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    et1.setVisibility(View.VISIBLE);
                    double d=c.getDouble(0);
                    tv.setText("Old fat Rate:"+d);
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fat=et1.getText().toString().trim();
                if(!(fat.isEmpty()))
                {
                    float fat_per= Float.parseFloat(fat);
                    ContentValues cv=new ContentValues();
                    cv.put(MilkDatabase.Table_Col6,fat_per);
                    int res=db.update(MilkDatabase.Table_Name1,cv,null,null);
                    if(res>0)
                    {
                        Toast.makeText(SettingHomeActivity.this, "Fat Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String col[]={MilkDatabase.Table_Col5,MilkDatabase.Table_Col2};
                Cursor c=db.query(MilkDatabase.Table_Name1,col,null,null,null,null,null);
                if(c.moveToFirst())
                {
                    bt1.setVisibility(View.GONE);
                    bt2.setVisibility(View.GONE);
                    bt3.setVisibility(View.GONE);
                    bt4.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.GONE);
                    et1.setVisibility(View.GONE);
                    et2.setVisibility(View.VISIBLE);
                    et3.setVisibility(View.VISIBLE);
                    et2.setText(c.getString(0));
                    et3.setText(c.getString(1));
                }
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int res1=0,res2=0;
                String cname=et2.getText().toString().trim();
                String cno=et3.getText().toString().trim();
                if(!(cname.isEmpty()))
                {
                    ContentValues cv=new ContentValues();
                    cv.put(MilkDatabase.Table_Col5,cname);
                    res1=db.update(MilkDatabase.Table_Name1,cv,null,null);
                }
                if(!(cno.isEmpty()))
                {
                    if(!(cno.charAt(0)=='7'||cno.charAt(0)=='8'||cno.charAt(0)=='9'))
                    {
                        et3.setError("Invalid");
                    }
                    else
                    {
                        if(!(cno.length()==10))
                        {
                            et3.setError("Invalid");
                            et3.requestFocus();
                        }
                        else
                        {
                            ContentValues cv=new ContentValues();
                            cv.put(MilkDatabase.Table_Col2,cno);
                            res2=db.update(MilkDatabase.Table_Name1,cv,null,null);
                        }
                    }
                }
                else
                {
                    et3.setError("Empty");
                    et3.requestFocus();
                }
                if(res1>0||res2>0)
                {
                    Toast.makeText(SettingHomeActivity.this, "Center name & Contact No Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
