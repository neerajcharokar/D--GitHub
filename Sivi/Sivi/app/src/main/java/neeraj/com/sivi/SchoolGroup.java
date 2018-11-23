package neeraj.com.sivi;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolGroup extends Fragment implements AdapterView.OnItemSelectedListener
{
    Button createBt;
    Spinner spinnerTo,spinnerFrom;
    Integer[] Class={1,2,3,4,5,6,7,8,9,10,11,12};
    String[] c={"Meereak"};
    Context appContext;
    public SchoolGroup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_school_group, container, false);
        createBt=view.findViewById(R.id.createBt);
        spinnerTo=view.findViewById(R.id.spinnerto);
        spinnerFrom=view.findViewById(R.id.spinnerfrom);
        //spinnerTo.setOnItemClickListener(getContext());

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item, Class);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerTo.setAdapter(aa);
        //spinnerFrom.setOnItemClickListener((AdapterView.OnItemClickListener) this.getActivity());

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa2 = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item, Class);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerFrom.setAdapter(aa2);
        createBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(appContext, "Group Created", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext=context;
    }
}
