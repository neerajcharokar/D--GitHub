package neeraj.com.milk.Milk;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFarmer extends Fragment {
    ListView lv;
    Activity a;
    ArrayList al;
    SQLiteDatabase db;
    int[] idno={R.id.farmer_idno,R.id.farmer_name,R.id.farmer_cno,R.id.farmer_imageview};
    String[] keys={"k1","k2","k3","k4"};
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        a=(Activity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_view_farmer, container, false);
        lv=v.findViewById(R.id.farmer_view_listview);
        MilkDatabase md=new MilkDatabase(a);
        db=md.getWritableDatabase();
        Cursor c=db.query(MilkDatabase.Table_Name2,null,null,null,null,null,null);
        al=new ArrayList();
        if(c.moveToFirst())
        {
            do
            {
                HashMap hm=new HashMap();
                byte[] image=c.getBlob(3);
                hm.put(keys[0],c.getInt(0));
                hm.put(keys[1],c.getString(1));
                hm.put(keys[2],c.getLong(2));
                hm.put(keys[3],image);
                al.add(hm);
            }while(c.moveToNext());
            MyAdapter ma=new MyAdapter();
            lv.setAdapter(ma);
        }
        else
        {
            Toast.makeText(a, "No Data", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
    public class MyAdapter extends BaseAdapter
    {
        String name;
        int idno;
        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v=getLayoutInflater().inflate(R.layout.activity_view_custom_list_view,parent,false);
            ImageView iv=v.findViewById(R.id.farmer_iv);
            TextView tv1=v.findViewById(R.id.farmer_idno);
            TextView tv2=v.findViewById(R.id.farmer_name);
            TextView tv3=v.findViewById(R.id.farmer_cno);
            Button bt1=v.findViewById(R.id.farmer_edit);
            Button bt2=v.findViewById(R.id.farmer_delete);
            HashMap hm=(HashMap) al.get(position);
            idno=(int)hm.get(keys[0]);
            name=(String)hm.get(keys[1]);
            long cno=(Long) hm.get(keys[2]);
            byte[] image=(byte[])hm.get(keys[3]);
            Bitmap bit=BitmapFactory.decodeByteArray(image,0,image.length);
            iv.setImageBitmap(bit);
            tv1.setText(""+idno);
            tv2.setText(name);
            tv3.setText(""+cno);
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    AlertDialog.Builder adb= new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
                    HashMap hm=(HashMap) al.get(position);
                    idno=(int)hm.get(keys[0]);
                    name=(String)hm.get(keys[1]);
                    adb.setIcon(android.R.drawable.stat_sys_warning);
                    adb.setTitle("Farmer :"+name);
                    adb.setMessage("Farmer Delete Conformation");
                    adb.setCancelable(false);
                    adb.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Log.e(""+position,""+idno);
                            String where=MilkDatabase.Table2_Col1+"=?";
                            String where_args[]={""+idno};
                            int res=db.delete(MilkDatabase.Table_Name2,where,where_args);
                            if(res!=0)
                            {
                                Toast.makeText(a, "Farmer Deleted", Toast.LENGTH_SHORT).show();
                                a.finish();
                            }
                        }
                    });
                    adb.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.create().show();
                }
            });
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent i=new Intent(a,UpdateActivity.class);
                    i.putExtra("idno",idno);
                    startActivity(i);
                    a.finish();
                }
            });
            return v;
        }
    }
}
