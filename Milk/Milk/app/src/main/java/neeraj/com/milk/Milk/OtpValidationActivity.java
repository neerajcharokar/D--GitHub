package neeraj.com.milk.Milk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class OtpValidationActivity extends Activity {
    EditText et;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_validation);
        et=findViewById(R.id.otp);
        sp=getSharedPreferences("register_status",MODE_PRIVATE);
        spe=sp.edit();
        MilkDatabase md=new MilkDatabase(this);
        db=md.getWritableDatabase();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String otp=s.toString().trim();
                if(otp.length()==5)
                {
                    String sel=MilkDatabase.Table_Col4+"=?";
                    String sel_args[]={otp};
                    Cursor c=db.query(MilkDatabase.Table_Name1,null,sel,sel_args,null,null,null);
                    boolean res=c.moveToFirst();
                    if(res)
                    {
                        spe.putBoolean("status",true);
                        spe.commit();
                        finish();
                        startActivity(new Intent(OtpValidationActivity.this,MainActivity.class));
                        //Toast.makeText(OtpValidationActivity.this, "valid", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(OtpValidationActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
