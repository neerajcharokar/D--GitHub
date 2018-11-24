package neeraj.com.milk.Milk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import neeraj.com.milk.R;
import neeraj.com.milk.db.MilkDatabase;

public class UpdateActivity extends Activity {
    EditText et1,et2,et3;
    ImageView iv;
    int idno;
    long cno;
    String fname,name,contact;
    Bitmap bit=null,image;
    SQLiteDatabase db;
    public static  final int CAM_REQ_CODE=1212;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        et1 = findViewById(R.id.update_farmer_idno);
        et2 = findViewById(R.id.update_farmer_name);
        et3 = findViewById(R.id.update_farmer_cno);
        iv = findViewById(R.id.update_farmer_imageview);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAM_REQ_CODE);
            }
        });
        Button bt = findViewById(R.id.update_farmer_update);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        idno = b.getInt("idno");
        db = new MilkDatabase(this).getWritableDatabase();
        String qry = "select * from " + MilkDatabase.Table_Name2 + " where " + MilkDatabase.Table2_Col1 + " = " + idno;
        Cursor c = db.rawQuery(qry, null);
        c.moveToFirst();
        name = c.getString(1);
        cno = c.getLong(2);
        byte[] img = c.getBlob(3);
        image = BitmapFactory.decodeByteArray(img, 0, img.length);
        et1.setText("" + idno);
        et1.setEnabled(false);
        et2.setText(name);
        et3.setText("" + cno);
        iv.setImageBitmap(image);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = et2.getText().toString().trim();
                contact = et3.getText().toString().trim();
                if (!(fname.isEmpty())) {
                    if (!(fname.equals(name))) {
                        String qry1 = "update " + MilkDatabase.Table_Name2 + " set " + MilkDatabase.Table2_Col2 + " = '" + fname + "' where " + MilkDatabase.Table2_Col1 + " = " + idno;
                        db.execSQL(qry1);
                    }
                } else {
                    et2.setError("Empty");
                    et2.requestFocus();
                }
                if (contact.isEmpty()) {
                    et3.setError("Empty");
                    et3.requestFocus();
                } else {
                    long contact_no = Long.parseLong(contact);
                    if (!(contact_no == cno)) {
                        if (!(contact.charAt(0) == '7' || contact.charAt(0) == '8' || contact.charAt(0) == '9')) {
                            et3.setError("Invalid");
                        } else {
                            if (!(contact.length() == 10)) {
                                et3.setError("Invalid");
                                et3.requestFocus();
                            } else {
                                String qry1 = "update " + MilkDatabase.Table_Name2 + " set " + MilkDatabase.Table2_Col3 + " = " + contact_no + " where " + MilkDatabase.Table2_Col1 + " = " + idno;
                                db.execSQL(qry1);
                            }
                        }
                    }
                }
                if(!(bit==null))
                {
                    ByteArrayOutputStream bout=new ByteArrayOutputStream();
                    bit.compress(Bitmap.CompressFormat.JPEG,100,bout);
                    byte[] image=bout.toByteArray();
                    ContentValues cv=new ContentValues();
                    cv.put(MilkDatabase.Table2_Col4,image);
                    String where=MilkDatabase.Table2_Col1+" =?";
                    String where_args[]={""+idno};
                    db.update(MilkDatabase.Table_Name2,cv,where,where_args);
                }
                if (!(fname.isEmpty() || contact.isEmpty())) {
                    Toast.makeText(UpdateActivity.this, "Farmer Details Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public  void clickImage(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, CAM_REQ_CODE);
    }
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Bundle b=data.getExtras();
            bit=(Bitmap)b.get("data");
            iv.setImageBitmap(bit);
        }

}
