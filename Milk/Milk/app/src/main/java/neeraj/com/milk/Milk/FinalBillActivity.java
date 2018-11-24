package neeraj.com.milk.Milk;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class FinalBillActivity extends Activity

{
    TextView fat,lit,total,pay,adv,bal,billno,date,idno,day,center_name,contact_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_bill);
        center_name=findViewById(R.id.final_center_name);
        contact_no=findViewById(R.id.final_contact_no);
        fat=findViewById(R.id.final_fat);
        lit=findViewById(R.id.final_liter);
        total=findViewById(R.id.final_amount);
        pay=findViewById(R.id.final_payable);
        adv=findViewById(R.id.final_advance);
        bal=findViewById(R.id.final_balance);
        billno=findViewById(R.id.final_billno);
        date=findViewById(R.id.final_date);
        idno=findViewById(R.id.final_idno);
        day=findViewById(R.id.final_day);
        SQLiteDatabase db=new MilkDatabase(this).getWritableDatabase();
        billno.setText(""+MilkFragment.billNo);
        date.setText(MilkFragment.Date);
        idno.setText(HomeActivity.id);
        day.setText(MilkFragment.Day);
        fat.setText(""+MilkFragment.Fat_c+"%");
        lit.setText(""+MilkFragment.No_li+" Li");
        total.setText("Rs. "+MilkFragment.totalAmount+"/-");
        pay.setText("Rs. "+MilkFragment.total_amt+"/-");
        bal.setText("Rs. "+MilkFragment.balance+"/-");
        String qry0="select sum("+MilkDatabase.Table5_Col3+") from "+MilkDatabase.Table_Name5+" where "+MilkDatabase.Table5_Col1+"="+HomeActivity.id;
        Cursor c1=db.rawQuery(qry0,null);
        double advance_amt=0.0;
        if(c1.moveToFirst())
        {
            advance_amt=c1.getDouble(0);
        }
        adv.setText("Rs. "+advance_amt+"/-");
        String col[]={MilkDatabase.Table_Col5,MilkDatabase.Table_Col2};
        Cursor c=db.query(MilkDatabase.Table_Name1,col,null,null,null,null,null);
        if(c.moveToFirst())
        {
            center_name.setText(c.getString(0));
            contact_no.setText(c.getString(1));
        }
    }
}
