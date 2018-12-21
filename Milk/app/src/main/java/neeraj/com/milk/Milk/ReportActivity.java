package neeraj.com.milk.Milk;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class ReportActivity extends Activity
{
    EditText from,to;
    ListView lv;
    SQLiteDatabase db;
    byte[] b={};
    String keys[]={"billno","billdate","fatrate","fat","nol","total","advance","day"};
    ArrayList al;
    View v;
    String bill_no,bill_date,fat_rate,fat_per,nolit,totalpay,advance,Day;
    String from_date,to_date;
    int ids[]={R.id.lv_billno,R.id.lv_billdate,R.id.lv_fatRate,R.id.lv_fatper,R.id.lv_nol,R.id.lv_total,R.id.lv_adv,R.id.lv_day};
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        lv=findViewById(R.id.reportList);
        v=getLayoutInflater().inflate(R.layout.list_view_header,null,false);
        View v1=getLayoutInflater().inflate(R.layout.list_view_footer,null,false);
        from=findViewById(R.id.fromDate);
        to=findViewById(R.id.toDate);
        db=new MilkDatabase(this).getWritableDatabase();
        TextView total=v1.findViewById(R.id.footer_total);
        TextView advance=v1.findViewById(R.id.footer_advance);
        String where1=MilkDatabase.Table4_Col1+" =?";
        String where_args1[]={HomeActivity.idr};
        Cursor c1=db.query(MilkDatabase.Table_Name4,null,where1,where_args1,null,null,null);
        if(c1.moveToFirst())
        {
            total.setText("Rs. "+c1.getDouble(1)+" /-");
            advance.setText("Rs. "+c1.getDouble(2)+" /-");
        }
        TextView idno=v.findViewById(R.id.header_idno);
        TextView name=v.findViewById(R.id.header_name);
        TextView cno=v.findViewById(R.id.header_cno);
        ImageView iv=v.findViewById(R.id.header_iv);
        String where=MilkDatabase.Table2_Col1+" =?";
        String where_args[]={HomeActivity.idr};
        Cursor c=db.query(MilkDatabase.Table_Name2,null,where,where_args,null,null,null);
        if(c.moveToFirst())
        {
            idno.setText(""+c.getInt(0));
            name.setText(c.getString(1));
            cno.setText(""+c.getLong(2));
            b=c.getBlob(3);
        }
        Bitmap bit=BitmapFactory.decodeByteArray(b,0,b.length);
        iv.setImageBitmap(bit);
        lv.addHeaderView(v);
        lv.addFooterView(v1);
    }
    public void showDetails(View view)
    {
        from_date=from.getText().toString().trim();
        to_date=to.getText().toString().trim();
        View v1=getLayoutInflater().inflate(R.layout.custom_listview,null,false);
        String qry1="select * from "+MilkDatabase.Table_Name3+" where "+MilkDatabase.Table3_Col3+" = "+HomeActivity.idr+" AND "+MilkDatabase.Table3_Col1+" Between '"+from_date+"' AND '"+to_date+"'";
        Cursor c1=db.rawQuery(qry1,null);
        al=new ArrayList();
        if(c1.moveToFirst()) {
            do
            {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                HashMap hm = new HashMap();
                hm.put(keys[0], "" + c1.getInt(1));
                hm.put(keys[1], c1.getString(0));
                float fat=c1.getFloat(3);
                hm.put(keys[3], "" +fat);
                float nol=c1.getFloat(4);
                hm.put(keys[4], "" +nol);
                double total=c1.getDouble(6);
                hm.put(keys[5], "" +total);
                hm.put(keys[6], "" + c1.getDouble(7));
                hm.put(keys[7], c1.getString(5));
                hm.put(keys[2],""+((total/nol)/fat)*10);
                al.add(hm);
            }
            while (c1.moveToNext());
        }
        SimpleAdapter sa=new SimpleAdapter(this,al,R.layout.custom_listview,keys,ids);
        lv.setAdapter(sa);
    }
    public void fromDate(View v) {
        Calendar c = Calendar.getInstance();
        int c_year = c.get(Calendar.YEAR);
        int c_month = c.get(Calendar.MONTH);
        int c_day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dbd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                from.setText(dayOfMonth+"-"+(month+1)+"-"+year);
            }
        },c_year,c_month,c_day);
        DatePicker db=dbd.getDatePicker();
        db.setMinDate(System.currentTimeMillis()-10*24*60*60*1000);
        db.setMaxDate(System.currentTimeMillis());
        dbd.show();
    }
    public void toDate(View v) {
        Calendar c = Calendar.getInstance();
        int c_year = c.get(Calendar.YEAR);
        int c_month = c.get(Calendar.MONTH);
        int c_day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dbd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                to.setText(dayOfMonth+"-"+(month+1)+"-"+year);
            }
        },c_year,c_month,c_day);
        DatePicker db=dbd.getDatePicker();
        db.setMinDate(System.currentTimeMillis()-10*24*60*60*1000);
        db.setMaxDate(System.currentTimeMillis());
        dbd.show();
    }
}
