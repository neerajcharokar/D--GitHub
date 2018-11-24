package neeraj.com.sivi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class CalenderActivity extends Activity
{
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView=findViewById(R.id.simpleCalendarView);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
    }
}
