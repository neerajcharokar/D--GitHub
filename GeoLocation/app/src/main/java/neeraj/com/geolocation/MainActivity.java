package neeraj.com.geolocation;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    int op;
    TextView tv;
    LocationManager lm;
    String my_per[]={Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
    }
    public void getLoc(View v)
    {
        int res=ContextCompat.checkSelfPermission(this,my_per[0]);
        if(res==PackageManager.PERMISSION_GRANTED)
        {
            ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.show();
            lm=(LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, this);
            progressDialog.dismiss();
        }
        else
        {
            ActivityCompat.requestPermissions(this,my_per,1212);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1212)
        {
            if(permissions.equals(my_per[0]))
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Use Location", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat=location.getLatitude();
        double lon=location.getLongitude();
        tv.setText(lat+"\n\n"+lon);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        op=1;
        Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "GPS Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(op==1)
        {
            lm.removeUpdates(this);
        }
    }
}