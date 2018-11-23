
package neeraj.com.sivi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegistrationPage extends Activity
{
    EditText etName, etPhone, etPassword, etConPassword, etRefferal,etEmail;
    public String schoolReferral;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        etName = findViewById(R.id.editTextName);
        etPhone = findViewById(R.id.editTextPhone);
        etEmail=findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etConPassword = findViewById(R.id.editTextConPassword);
        etRefferal = findViewById(R.id.editTextAdminReferral);
        sharedPreferences=this.getSharedPreferences("neeraj.com.file",MODE_PRIVATE);

        if(SchoolRegistration.flag==true)
        {
            etName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {

                }

                @Override
                public void afterTextChanged(Editable s)
                {
                    schoolReferral=s.toString()+"Refferal";
                    etRefferal.setText(schoolReferral);
                    etRefferal.setEnabled(false);
                    Log.e("Refferal",schoolReferral);
                }
            });
        }
    }
    public void registerDetail(View view)
    {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email=   etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String conPassword=etConPassword.getText().toString().trim();
        String refferal =etRefferal.getText().toString().trim();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        /*String name="DPS";
        String phone="8888888888";
        String email="school@gmail.com";
        String password="password";
        String conPassword="password";
        String refferal="DPSRefferal";*/
        if(!name.isEmpty())
        {
            if(!phone.isEmpty())
            {
                if ((phone.length()==10)&&(phone.charAt(0)=='6'||phone.charAt(0)=='7'||phone.charAt(0)=='8'||phone.charAt(0)=='9'))
                {
                    if(!(email.isEmpty()))
                    {
                        int adpos1=email.lastIndexOf("@");
                        int adpos2=email.indexOf("@");
                        int dtpos=email.lastIndexOf('.');
                        if(adpos1!=-1)
                        {
                            if(dtpos-adpos1>2)
                            {
                                if((email.endsWith("com"))||(email.endsWith("in")))
                                {
                                    if(adpos1==adpos2)
                                    {
                                        if(!password.isEmpty())
                                        {
                                            if (!conPassword.isEmpty())
                                            {
                                                if(!refferal.isEmpty())
                                                {
                                                    if (password.equals(conPassword))
                                                    {
                                                        if(SchoolRegistration.flag==true)
                                                        {
                                                            SchoolRegistration.flag=false;
                                                            ParentsRegistration.flag=false;
                                                            TeacherRegistration.flag=false;
                                                            String sPhone=phone;
                                                            String sPass=password;
                                                            editor.putString("sPhone",sPhone);
                                                            editor.putString("sPassword",sPass);
                                                            startActivity(new Intent(this,SchoolRegistration.class));
                                                            finish();
                                                        }
                                                        else if(TeacherRegistration.flag=true)
                                                        {
                                                            if(refferal.equals("Refferal"))
                                                            {
                                                                SchoolRegistration.flag=false;
                                                                ParentsRegistration.flag=false;
                                                                TeacherRegistration.flag=false;
                                                                String tPhone=phone;
                                                                String tPass=password;
                                                                editor.putString("tPhone",tPhone);
                                                                editor.putString("tPassword",tPass);
                                                                startActivity(new Intent(this,TeacherRegistration.class));
                                                                finish();

                                                            }
                                                            else
                                                            {
                                                                etRefferal.setError("Invalid Referral");
                                                                etRefferal.requestFocus();
                                                            }
                                                        }
                                                        else if(ParentsRegistration.flag==true)
                                                        {
                                                            if(refferal.equals("Refferal"))
                                                            {
                                                                SchoolRegistration.flag=false;
                                                                ParentsRegistration.flag=false;
                                                                TeacherRegistration.flag=false;
                                                                String pPhone=phone;
                                                                String pPass=password;
                                                                editor.putString("pPhone",pPhone);
                                                                editor.putString("pPassword",pPass);
                                                                startActivity(new Intent(this,ParentsRegistration.class));
                                                                finish();
                                                            }
                                                            else
                                                            {
                                                                etRefferal.setError("Invalid Referral");
                                                                etRefferal.requestFocus();
                                                            }
                                                        }
                                                    }
                                                    else
                                                    {
                                                        etConPassword.setError("Invalid");
                                                        etConPassword.requestFocus();
                                                    }
                                                }
                                                else
                                                {
                                                    etRefferal.setError("Empty");
                                                    etRefferal.requestFocus();
                                                }
                                            }
                                            else
                                            {
                                                etConPassword.setError("Empty");
                                                etConPassword.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            etPassword.setError("Empty");
                                            etPassword.requestFocus();
                                        }
                                    }
                                    else
                                    {
                                        etEmail.setError("Invalid");
                                        etEmail.requestFocus();
                                    }
                                }
                                else
                                {
                                    etEmail.setError("Invalid");
                                    etEmail.requestFocus();
                                }
                            }
                            else
                            {
                                etEmail.setError("Invalid");
                                etEmail.requestFocus();
                            }
                        }
                        else
                        {
                            etEmail.setError("Invalid");
                            etEmail.requestFocus();
                        }
                    }
                    else
                    {
                        etEmail.setError("Empty");
                        etEmail.requestFocus();
                    }
                }
                else
                {
                    etPhone.setError("Invalid");
                    etPhone.requestFocus();
                }
            }
            else
            {
                etPhone.setError("Empty");
                etPhone.requestFocus();
            }
        }
        else
        {
            etName.setError("Empty");
            etName.requestFocus();
        }
    }
}
