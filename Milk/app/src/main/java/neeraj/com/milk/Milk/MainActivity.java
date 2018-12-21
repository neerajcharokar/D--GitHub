package neeraj.com.milk.Milk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ProgressBar;

import neeraj.com.milk.R;

public class MainActivity extends Activity {
    android.os.Handler mh=new android.os.Handler();
    //ProgressBar pb;
    boolean res;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pb = findViewById(R.id.pb);
        sp=getSharedPreferences("register_status",MODE_PRIVATE);
        res=sp.getBoolean("status",false);
        mh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(res)
                {
                    finish();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                else
                {
                    createShortcut();
                    finish();
                    show();
                }
            }
        },2000);

    }
    public void createShortcut()
    {
        Intent intentShortcut=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,getString(R.string.app_name));
        Parcelable appicon=Intent.ShortcutIconResource.fromContext(getApplicationContext(),R.drawable.milk_2);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,appicon);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,new Intent(getApplicationContext(),MainActivity.class));
        intentShortcut.putExtra("duplicate",false);
        sendBroadcast(intentShortcut);
    }
    public void show ()
    {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }
}
