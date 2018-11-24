package neeraj.com.milk.Milk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class DialogActivity extends Activity {
    EditText user_name_et,user_cno_et,user_email_et,user_center_name;
    String user_name,user_cno,user_email,gen_otp,center_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        user_name_et=findViewById(R.id.dialog_edittext1);
        user_cno_et=findViewById(R.id.dialog_edittext2);
        user_email_et=findViewById(R.id.dialog_edittext3);
        user_center_name=findViewById(R.id.farmer_center_name);
    }

    public void submit()
    {
        user_name=user_name_et.getText().toString().trim();
        user_cno=user_cno_et.getText().toString().trim();
        user_email=user_email_et.getText().toString().trim();
        center_name=user_center_name.getText().toString().trim();
        if(!(user_name.isEmpty()))
        {
            int i;
            for(i=0;i<user_name.length();i++)
            {
                if(!(((user_name.charAt(i))>64 &&((user_name.charAt(i))<93))||(((user_name.charAt(i))>96 && ((user_name.charAt(i))<125))||((user_name.charAt(i)==' ')))))
                {
                    break;
                }
            }
            if (!(i==user_name.length()))
            {
                user_name_et.setError("Invalid");
                user_name_et.requestFocus();
            }
            else
            {
                if(!(user_cno.isEmpty()))
                {
                    if(!(user_cno.charAt(0)=='7'||user_cno.charAt(0)=='8'||user_cno.charAt(0)=='9'))
                    {
                        user_cno_et.setError("Invalid");
                        user_cno_et.requestFocus();
                    }
                    else
                    {
                        if(!(user_cno.length()==10))
                        {
                            user_cno_et.setError("Invalid");
                        }
                        else
                        {
                            if(!(user_cno.isEmpty()))
                            {
                                if(!(user_cno.charAt(0)=='7'||user_cno.charAt(0)=='8'||user_cno.charAt(0)=='9'))
                                {
                                    user_cno_et.setError("Invalid");
                                }
                                else
                                {
                                    if(!(user_cno.length()==10))
                                    {
                                        user_cno_et.setError("Invalid");
                                    }
                                    else
                                    {
                                        if(!(user_email.isEmpty()))
                                        {
                                            int adpos1=user_email.lastIndexOf("@");
                                            int adpos2=user_email.indexOf("@");
                                            int dtpos=user_email.lastIndexOf('.');
                                            if(adpos1!=-1)
                                            {
                                                if(dtpos-adpos1>2)
                                                {
                                                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
                                                    if(user_email.matches(emailPattern))
                                                    {
                                                        if(adpos1==adpos2)
                                                        {
                                                            if(!(center_name.isEmpty()))
                                                            {
                                                                gen_otp = OTP(user_cno);
                                                                database();
                                                                Toast.makeText(this, "OTP:" + gen_otp, Toast.LENGTH_LONG).show();
                                                                finish();
                                                                startActivity(new Intent(DialogActivity.this, OtpValidationActivity.class));
                                                            }
                                                            else
                                                            {
                                                                user_center_name.setError("Empty");
                                                                user_center_name.requestFocus();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            user_email_et.setError("Invalid");
                                                            user_email_et.requestFocus();
                                                        }
                                                    }
                                                    else
                                                    {
                                                        user_email_et.setError("Invalid");
                                                        user_email_et.requestFocus();
                                                    }
                                                }
                                                else
                                                {
                                                    user_email_et.setError("Invalid");
                                                    user_email_et.requestFocus();
                                                }
                                            }
                                            else
                                            {
                                                user_email_et.setError("Invalid");
                                                user_email_et.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            user_email_et.setError("Empty");
                                            user_email_et.requestFocus();
                                        }
                                    }
                                }
                            }
                            else
                            {
                                user_cno_et.setError("Empty");
                                user_cno_et.requestFocus();
                            }
                        }
                    }
                }
                else
                {
                    user_cno_et.setError("Empty");
                    user_cno_et.requestFocus();
                }
            }

        }
        else
        {
            user_name_et.setError("Empty");
            user_name_et.requestFocus();
        }
    }

    public void dialogRegister(View view) {
        submit();
    }
    public String OTP(String cno)
    {
        char first=cno.charAt(0);
        char last=cno.charAt(5);
        Calendar c=Calendar.getInstance();
        int c_day=c.get(Calendar.DAY_OF_MONTH);
        int c_month=c.get(Calendar.MONTH);
        int c_hr=c.get(Calendar.HOUR);
        int c_sec=c.get(Calendar.SECOND);
        String otp="N"+(last+c_sec+c_day+c_hr+c_month+first)+"S";
        return otp;
    }
    public void database()
    {
        MilkDatabase mdb=new MilkDatabase(this);
        SQLiteDatabase db=mdb.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MilkDatabase.Table_Col1,user_name);
        cv.put(MilkDatabase.Table_Col1,user_cno);
        cv.put(MilkDatabase.Table_Col2,user_cno);
        cv.put(MilkDatabase.Table_Col3,user_email);
        cv.put(MilkDatabase.Table_Col4,gen_otp);
        cv.put(MilkDatabase.Table_Col5,center_name);
        cv.put(MilkDatabase.Table_Col6,0);
        db.insert(MilkDatabase.Table_Name1,null,cv);
    }

}
