package neeraj.com.sivi;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolInvite extends Fragment
{
    Button sendBt;
    EditText inviteEt;
    Context appContext;
    public SchoolInvite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_school_invite, container, false);
        sendBt=view.findViewById(R.id.sendBt);
        inviteEt=view.findViewById(R.id.inviteEt);
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                inviteEt.setText("");
                Toast.makeText(appContext, "Invitation Send", Toast.LENGTH_SHORT).show();
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
