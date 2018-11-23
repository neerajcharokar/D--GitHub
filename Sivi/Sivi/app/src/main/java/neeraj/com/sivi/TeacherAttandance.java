package neeraj.com.sivi;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAttandance extends Fragment
{
    ListView lv;
    String[] studentName={"Mohit","Sanju","Rishab","Arpit","Ankita","Neeraj","Divyanshi","Dimpy","Akshay","Yash"};
    String keys[]={"k1","k2","k3"};
    ArrayList al;
    int ids[]={R.id.tv,R.id.switch1,R.id.checkBox};
    Context appContext;

    public TeacherAttandance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_teacher_attandance, container, false);
        lv=view.findViewById(R.id.lvAttandance);
        al=new ArrayList();
        for(int i=0;i<studentName.length;i++)
        {
            HashMap hm=new HashMap();
            hm.put(keys[0],studentName[i]);
            al.add(hm);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(appContext,al,R.layout.my_list_style,keys,ids);
        lv.setAdapter(simpleAdapter);
        View v1=getLayoutInflater().inflate(R.layout.view_footer,null,false);
        Button bt=v1.findViewById(R.id.submit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(appContext, "Attendance Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        lv.addFooterView(v1);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext=context;
    }
}
