package neeraj.com.sivi;


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.KeyEventDispatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolHome extends Fragment
{
    Context appContext;

    public SchoolHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_school_home, container, false);
        view.findViewById(R.id.feeib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(appContext, "Fee Selected", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.noticeib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(appContext, "Notice Selected", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.attib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(appContext, "Attendance Selected", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.ttib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(appContext, "TimeTableS Selected", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName("com.android.calculator2","com.android.calculator2.Calculator"));
                try {
                    startActivity(intent);
                    } catch (ActivityNotFoundException noSuchActivity) {
                    Log.e("Activity Error",noSuchActivity.getMessage());
                    }*/
                ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();

                final PackageManager pm = appContext.getPackageManager();
                List<PackageInfo> packs = pm.getInstalledPackages(0);
                for (PackageInfo pi : packs) {
                    if( pi.packageName.toString().toLowerCase().contains("calcul")){
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("appName", pi.applicationInfo.loadLabel(pm));
                        map.put("packageName", pi.packageName);
                        items.add(map);
                    }
                }
                if(items.size()>=1){
                    String packageName = (String) items.get(0).get("packageName");
                    Intent i = pm.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivity(i);
                }
                else{
                    // Application not found
                    Toast.makeText(appContext, "Application Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.diary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appContext,CalenderActivity.class));
            }
        });
        view.findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appContext,DocumentScanner.class));
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext=context;
    }
}
