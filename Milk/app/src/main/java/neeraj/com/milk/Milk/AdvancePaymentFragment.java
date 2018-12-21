package neeraj.com.milk.Milk;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvancePaymentFragment extends Fragment
{
    double adv=0.0;
    String Date;
    public AdvancePaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_advance_payment, container, false);
        TextView date=v.findViewById(R.id.advance_date);
        final EditText advance=v.findViewById(R.id.advance_amount);
        Button submit=v.findViewById(R.id.advance_submit);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        Date=sdf.format(new Date());
        date.setText("Date : "+Date);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=advance.getText().toString().trim();
                if(!(s1.isEmpty()))
                {
                    double advance_amt=Double.parseDouble(s1);
                    SQLiteDatabase db=new MilkDatabase(getContext()).getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    cv.put(MilkDatabase.Table5_Col1,HomeActivity.id);
                    cv.put(MilkDatabase.Table5_Col2,Date);
                    cv.put(MilkDatabase.Table5_Col3,advance_amt);
                    db.insert(MilkDatabase.Table_Name5,null,cv);
                    String qry="select sum("+MilkDatabase.Table5_Col3+") from "+MilkDatabase.Table_Name5+" where "+MilkDatabase.Table5_Col1+"="+HomeActivity.id;
                    Cursor c1=db.rawQuery(qry,null);
                    if(c1.moveToFirst())
                    {
                        adv=c1.getDouble(0);
                        adv=adv+advance_amt;
                    }
                    String where=MilkDatabase.Table4_Col1+" =?";
                    String where_args2[]={HomeActivity.id};
                    ContentValues cv1=new ContentValues();
                    cv1.put(MilkDatabase.Table4_Col3,adv);
                    long no=db.update(MilkDatabase.Table_Name4,cv1,where,where_args2);
                    if(no!=-1)
                    {
                        Log.e("Adv",""+no);
                    }
                    getActivity().finish();
                }
                else
                {
                    advance.setError("Empty");
                }
            }
        });
        return v;
    }

}
