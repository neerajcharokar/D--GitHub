package neeraj.com.sivi;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LoginHomeActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
    public void schoolLogin(View view)
    {
        startActivity(new Intent(this,SchoolRegistration.class));
        finish();
    }
    public void teachersLogin(View view)
    {
        startActivity(new Intent(this,TeacherRegistration.class));
        finish();
    }
    public void parentsLogin(View view)
    {
        startActivity(new Intent(this,ParentsRegistration.class));
        finish();
    }
}