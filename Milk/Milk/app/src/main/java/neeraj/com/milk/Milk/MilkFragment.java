package neeraj.com.milk.Milk;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class MilkFragment extends Fragment {
    TextView billno,date,day,idno,name,total;
    EditText NoOfLi,Fc;
    Button save_milk;
    SQLiteDatabase db;
    public static double totalAmount,total_adv=0.0,total_amt,balance;
    public static String Day,Date,Fat_c,No_li;
    public static int billNo;
    public MilkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_milk, container, false);
        db=new MilkDatabase(getContext()).getWritableDatabase();
        ImageView iv=v.findViewById(R.id.milk_farmer_image);
        iv.setImageBitmap(fimage());
        billno=v.findViewById(R.id.milk_billno);
        date=v.findViewById(R.id.milk_date);
        day=v.findViewById(R.id.milk_day);
        idno=v.findViewById(R.id.milk_idno);
        name=v.findViewById(R.id.milk_name);
        total=v.findViewById(R.id.milk_total_amount);
        NoOfLi=v.findViewById(R.id.milk_nol);
        Fc=v.findViewById(R.id.milk_fat);
        save_milk=v.findViewById(R.id.milk_farmer_save);
        idno.setText(HomeActivity.id);
        name.setText(HomeActivity.name);
        billNo=genBillNo();
        int res=Calendar.getInstance().get(Calendar.AM_PM);
        if(res==Calendar.AM)
        {
            Day="AM";
            day.setText("AM");
        }
        else
        {
            Day="PM";
            day.setText("PM");
        }
        billno.setText(""+billNo);
        SimpleDateFormat sdf=new SimpleDateFormat("d-MM-yyyy");
        Date =sdf.format(new Date());
        date.setText(Date);
        NoOfLi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                No_li=NoOfLi.getText().toString().trim();
                Fat_c=Fc.getText().toString().trim();
                if(!(Fat_c.isEmpty()))
                {
                    if(!(No_li.isEmpty()))
                    {
                        float fat=Float.parseFloat(Fat_c);
                        float ltr=Float.parseFloat(No_li);
                        totalAmount=((getFatRate()/10)*fat)*ltr;
                        total.setText(String.valueOf(totalAmount));
                    }
                    else
                    {
                        NoOfLi.setError("Invalid");
                    }
                }
                else
                {
                    Fc.setError("Invalid");
                }

            }
        });
        save_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String qry="select sum("+MilkDatabase.Table5_Col3+") from "+MilkDatabase.Table_Name5+" where "+MilkDatabase.Table5_Col1+"="+HomeActivity.id;
                Cursor c1=db.rawQuery(qry,null);
                if(c1.moveToFirst())
                {
                    //total_adv=total_adv+c1.getDouble(0);
                    total_adv=c1.getDouble(0);
                }
                ContentValues cv=new ContentValues();
                cv.put(MilkDatabase.Table3_Col1,Date);
                cv.put(MilkDatabase.Table3_Col2,billNo);
                cv.put(MilkDatabase.Table3_Col3,HomeActivity.id);
                cv.put(MilkDatabase.Table3_Col4,Fat_c);
                cv.put(MilkDatabase.Table3_Col5,No_li);
                cv.put(MilkDatabase.Table3_Col6,Day);
                cv.put(MilkDatabase.Table3_Col7,totalAmount);
                cv.put(MilkDatabase.Table3_Col8,total_adv);
                long no1=db.insert(MilkDatabase.Table_Name3,null,cv);
                ContentValues cv1=new ContentValues();
                String qry0="select sum("+MilkDatabase.Table3_Col7+") from "+MilkDatabase.Table_Name3+" where "+MilkDatabase.Table3_Col3+"="+HomeActivity.id;
                Cursor c=db.rawQuery(qry0,null);
                if(c.moveToFirst())
                {
                    total_amt=c.getDouble(0);
                }
                else
                {
                    total_amt=0.0;
                }
                balance=total_amt-total_adv;
                cv1.put(MilkDatabase.Table4_Col1,HomeActivity.id);
                cv1.put(MilkDatabase.Table4_Col2,total_amt);
                cv1.put(MilkDatabase.Table4_Col3,total_adv);
                cv1.put(MilkDatabase.Table4_Col4,balance);
                String col1[]={MilkDatabase.Table4_Col2};
                String where=MilkDatabase.Table4_Col1+" =?";
                String where_args1[]={HomeActivity.id};
                c=db.query(MilkDatabase.Table_Name4,col1,where,where_args1,null,null,null);
                long no2=0;
                if(c.moveToFirst())
                {
                    where=MilkDatabase.Table4_Col1+" =?";
                    String where_args2[]={HomeActivity.id};
                    no2=db.update(MilkDatabase.Table_Name4,cv1,where,where_args2);
                }
                else
                {
                    no2=db.insert(MilkDatabase.Table_Name4,null,cv1);
                }
                if(no1>0&&no2>0)
                {
                    getActivity().finish();
                    startActivity(new Intent(getContext(),FinalBillActivity.class));
                }
            }
        });
        return v;
    }
    public int genBillNo()
    {
        int billNo=101;
        db=new MilkDatabase(getActivity()).getWritableDatabase();
        String qry="Select max("+MilkDatabase.Table3_Col2+") from "+MilkDatabase.Table_Name3;
        Cursor c=db.rawQuery(qry,null);
        if(c.moveToFirst())
        {
            billNo=c.getInt(0);
            if(billNo==0)
            {
                billNo=101;
            }
            else
            {
                billNo++;
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        return billNo;
    }
    public double getFatRate()
    {
        double rate=0;
        String col[]={MilkDatabase.Table_Col6};
        Cursor c=db.query(MilkDatabase.Table_Name1,col,null,null,null,null,null);
        if(c.moveToFirst())
        {
            rate=c.getDouble(0);
        }
        return rate;
    }
    public Bitmap fimage()
    {
        String col[]={MilkDatabase.Table2_Col4};
        String where=MilkDatabase.Table2_Col1+" =?";
        String where_args[]={HomeActivity.id};
        Cursor c=db.query(MilkDatabase.Table_Name2,col,where,where_args,null,null,null);
        byte[] b=null;
        if(c.moveToFirst())
        {
            b=c.getBlob(0);
        }
        return BitmapFactory.decodeByteArray(b,0,b.length);
    }
}