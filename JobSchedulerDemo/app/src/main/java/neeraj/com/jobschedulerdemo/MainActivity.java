package neeraj.com.jobschedulerdemo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final int JOB_ID = 101;
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ComponentName componentName=new ComponentName(this,MJobScheduler.class);
        JobInfo.Builder builder=new JobInfo.Builder(JOB_ID,componentName);
        builder.setPeriodic(5000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);
        jobInfo=builder.build();
        jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }
    public void scheduleJob(View V)
    {
     jobScheduler.schedule(jobInfo);
        Toast.makeText(this, "JOB Scheduled....", Toast.LENGTH_SHORT).show();
    }
    public void clearJob(View V)
    {
        jobScheduler.cancel(JOB_ID);
        Toast.makeText(this, "JOB Cancelled....", Toast.LENGTH_SHORT).show();
    }
}




























