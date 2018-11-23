package neeraj.com.sivi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherRegistration extends Activity
{
    EditText etPhone,etPassword;
    public static boolean flag=false;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        sharedPreferences=this.getSharedPreferences("neeraj.com.file",MODE_PRIVATE);
        etPhone=findViewById(R.id.editTextPhone);
        etPassword=findViewById(R.id.editTextPassword);
    }
    public void registerUser(View view)
    {
        flag=true;
        startActivity(new Intent(this,RegistrationPage.class));
        finish();
    }
    public void loginUser(View view)
    {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String regPhone=sharedPreferences.getString("tPhone",null);
        String regPass=sharedPreferences.getString("tPassword",null);
        if(phone.equals("8888888888"))
        {
            if (password.equals("password"))
            {
                startActivity(new Intent(this,TeacherHomePage.class));
                finish();
            }
            else
            {
                etPassword.setError("Invalid");
                etPassword.requestFocus();
            }
        }
        else
        {
            etPhone.setError("Invalid");
            etPhone.requestFocus();
            Toast.makeText(this, "Not Registered!! Plz Register First", Toast.LENGTH_SHORT).show();
        }
    }
}
