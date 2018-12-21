package neeraj.com.firebasejobdispatcher;

import android.os.AsyncTask;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyService extends JobService
{
    BackgroundTask backgroundTask;
    @Override
    public boolean onStartJob(final JobParameters job)
    {
        backgroundTask = new BackgroundTask()
        {
            @Override
            protected void onPostExecute(String s)
            {
                Toast.makeText(MyService.this, "Message from background Task"+s, Toast.LENGTH_SHORT).show();
                jobFinished(job,false);
            }
        };
        backgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job)
    {
        return true;
    }
    public static class BackgroundTask extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... voids)
        {
            return "Hello from backgound job...";
        }
    }
}




























