package neeraj.com.sivi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DocumentScanner extends AppCompatActivity
{
    ImageView imageView;
    Bitmap bit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_scanner);
        imageView = findViewById(R.id.hwiv);
        Button bt=findViewById(R.id.submitHw);
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
                    Toast.makeText(DocumentScanner.this, "Document Added", Toast.LENGTH_SHORT).show();
                    bit=null;
                    imageView.setImageResource(R.drawable.camera);
                }
                else
                {
                    Toast.makeText(DocumentScanner.this, "Plz click image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if (!(data == null)) {
                Bundle b = data.getExtras();
                bit = (Bitmap) b.get("data");
                imageView.setImageBitmap(bit);
            }
        }
    }
}
