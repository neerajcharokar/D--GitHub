package neeraj.com.firebasejobdispatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity
{
    public static final String JOB_TAG="my_job_tag";
    private FirebaseJobDispatcher firebaseJobDispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
    }

    public void startJob(View view)
    {
        Job job=firebaseJobDispatcher.newJobBuilder()
                .setService(MyService.class)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTag(JOB_TAG)
                .setTrigger(Trigger.executionWindow(0,60))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        firebaseJobDispatcher.mustSchedule(job);
        Toast.makeText(this, "Job Schedulled...", Toast.LENGTH_SHORT).show();
    }

    public void stopJob(View view)
    {
        firebaseJobDispatcher.cancel(JOB_TAG);
        Toast.makeText(this, "Job Cancalled...", Toast.LENGTH_SHORT).show();
    }
}



























