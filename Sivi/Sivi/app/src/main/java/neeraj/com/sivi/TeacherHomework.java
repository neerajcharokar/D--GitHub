package neeraj.com.sivi;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomework extends Fragment {
    ImageView imageView;
    Bitmap bit = null;
    Context appContext;
    public TeacherHomework() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_homework, container, false);
        imageView = view.findViewById(R.id.hwiv);
        Button bt=view.findViewById(R.id.submitHw);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1111);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bit!=null)
                {
                    Toast.makeText(appContext, "Homework Submitted", Toast.LENGTH_SHORT).show();
                    bit=null;
                    imageView.setImageResource(R.drawable.camera);
                }
                else
                {
                    Toast.makeText(appContext, "Plz click image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if (!(data == null)) {
                Bundle b = data.getExtras();
                bit = (Bitmap) b.get("data");
                imageView.setImageBitmap(bit);
            }
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext=context;
    }
}
