package neeraj.com.snackbar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayPopup(View view)
    {
        Snackbar snackbar=Snackbar.make(findViewById(R.id.rootLayout),R.string.message_received,Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.undo_string,this);
        snackbar.show();
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(this, "Undo Operation Selected", Toast.LENGTH_SHORT).show();
    }
}















