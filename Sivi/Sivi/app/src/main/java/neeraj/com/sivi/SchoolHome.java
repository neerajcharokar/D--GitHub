package neeraj.com.sivi;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext=context;
    }
}
