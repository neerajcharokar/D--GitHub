package neeraj.com.milk.Milk;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFarmer extends Fragment {
    ImageView iv;
    TextView tv;
    EditText et1,et2,et3;
    Button bt;
    int farmer_id;
    Activity a;
    MilkDatabase md;
    Bitmap bit=null;
    ContentValues cv;
    SQLiteDatabase db;
    View v;

    public static  final int CAM_REQ_CODE=1212;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        a=(Activity)context;
    }

    public AddFarmer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.fragment_add_farmer, container, false);
        iv=v.findViewById(R.id.farmer_imageview);
        et1=v.findViewById(R.id.farmer_idno);
        et2=v.findViewById(R.id.farmer_name);
        et3=v.findViewById(R.id.farmer_cno);
        bt=v.findViewById(R.id.add_farmer_save);
        tv=v.findViewById(R.id.tv);
        farmer_id=idNoGen();
        et1.setText(""+farmer_id);
        et1.setEnabled(false);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,CAM_REQ_CODE);
            }

        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,CAM_REQ_CODE);
            }
        });

        return v;
    }
    public void saveData()
    {
        String name= et2.getText().toString().trim();
        String contact_no= et3.getText().toString().trim();
        md=new MilkDatabase(getContext());
        db= md.getWritableDatabase();
        cv=new ContentValues();
        if(bit!=null)
        {
            if (!(name.isEmpty()))
            {
                if(!(contact_no.isEmpty()))
                {
                    if(!(contact_no.charAt(0)=='7'||contact_no.charAt(0)=='8'||contact_no.charAt(0)=='9'))
                    {
                        et3.setError("Invalid");
                    }
                    else
                    {
                        if(!(contact_no.length()==10))
                        {
                            et3.setError("Invalid");
                            et3.requestFocus();
                        }
                        else
                        {
                            long contact=Long.parseLong(contact_no);
                            cv.put(MilkDatabase.Table2_Col1,farmer_id);
                            cv.put(MilkDatabase.Table2_Col2,name);
                            cv.put(MilkDatabase.Table2_Col3,contact);
                            ByteArrayOutputStream bout=new ByteArrayOutputStream();
                            bit.compress(Bitmap.CompressFormat.JPEG,100,bout);
                            byte[] image=bout.toByteArray();
                            cv.put(MilkDatabase.Table2_Col4,image);
                            long result=db.insert(MilkDatabase.Table_Name2,null,cv);
                            if(result!=-1)
                            {
                                Toast.makeText(a, "Farmer Added", Toast.LENGTH_SHORT).show();
                                a.finish();
                            }
                        }
                    }
                }
                else
                {
                    et3.setError("Empty");
                    et3.requestFocus();
                }
            }
            else
            {
                et2.setError("Empty");
            }
        }
        else
        {
            Toast.makeText(getContext(), "Capture Image First", Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(!(data==null)) {
            Bundle b = data.getExtras();
            bit = (Bitmap) b.get("data");
            iv.setImageBitmap(bit);
        }
    }
    public int idNoGen()
    {
        int idno;
        MilkDatabase md1=new MilkDatabase(getContext());
        SQLiteDatabase db1=md1.getWritableDatabase();
        String qry="select max("+MilkDatabase.Table2_Col1+")from "+MilkDatabase.Table_Name2;
        Cursor c=db1.rawQuery(qry,null);
        if(c.moveToFirst())
        {
            idno=c.getInt(0);
            idno++;
        }
        else
        {
         idno=1;
        }
        c.close();
        return idno;
    }
}
