package neeraj.com.googlesignin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

public class Welcome extends AppCompatActivity
{
    ImageView personProfilePic;
    TextView name,givenName,familyName,eMail,iD;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        personProfilePic=findViewById(R.id.profilePic);
        name=findViewById(R.id.personName);
        givenName=findViewById(R.id.personGivenName);
        familyName=findViewById(R.id.personFamilyName);
        eMail=findViewById(R.id.personEmail);
        iD=findViewById(R.id.personId);
        byte[] encodeByte=Base64.decode(MainActivity.personPhoto,Base64.DEFAULT);
        Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
        personProfilePic.setImageBitmap(bitmap);
        name.setText(MainActivity.personName);
        givenName.setText(MainActivity.personGivenName);
        familyName.setText(MainActivity.personFamilyName);
        eMail.setText(MainActivity.personEmail);
        iD.setText(MainActivity.personId);
    }
    public void signOut(View view)
    {
        MainActivity mainActivity=new MainActivity();
        MainActivity.mAuth.signOut();
        view.setVisibility(View.GONE);
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
