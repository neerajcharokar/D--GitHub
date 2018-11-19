package neeraj.com.ameerpetstreets;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    String url="https://nareshit.in/course-schedule/";
    String urlSathya="https://sathyatech.com/time-table/";
    ProgressDialog progressDialog;
    ArrayList course=new ArrayList();
    ArrayList faculty=new ArrayList();
    ArrayList date=new ArrayList();
    ArrayList sathyaCourse=new ArrayList();
    ArrayList sathyaFaculty=new ArrayList();
    ArrayList sathyaDate=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textVie5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
    }
    public void showData(View view)
    {
        Content content=new Content();
        content.execute();
    }
    public class Content extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try {
                //NareshIt data Fetch Starts
                Document document= Jsoup.connect(url).get();
                Elements coursee=document.getElementsByClass("column-1");
                Elements facultye=document.getElementsByClass("column-2");
                Elements datee=document.getElementsByClass("column-3");
                for(int i=1;i<coursee.size();i++)
                {
                    String str=coursee.get(i).getElementsByAttribute("href").toString();
                    int index=str.indexOf(">");
                    str=str.substring(index+1,str.length()-4);
                    course.add(str);
                }
                for (int i=1;i<facultye.size();i++)
                {
                    String str=facultye.get(i).toString();
                    int index=str.indexOf(">");
                    str=str.substring(index+1,str.length()-5);
                    faculty.add(str);
                }
                for (int i=1;i<datee.size();i++)
                {
                    String str=datee.get(i).toString();
                    int index=str.indexOf(">");
                    str=str.substring(index+1,str.length()-5);
                    date.add(str);
                }
                //NareshIt Data Fetch Ends

                //Sathya Data Fetch Starts

                Document documentSathya=Jsoup.connect(urlSathya).get();
                Elements sathyaData=documentSathya.getElementsByTag("td");
                sathyaCourse.clear();
                sathyaFaculty.clear();
                sathyaDate.clear();
                int flag=0;
                for (Element element:sathyaData)
                {
                    flag++;
                    //String strC=element.getElementsByAttributeValue("data-title=","Course Name").toString();
                    if(flag==1)
                    {
                        String strC = element.attr("data-title=", "Course Name").ownText();
                        sathyaCourse.add(strC);
                    }
                    else if(flag==2) {
                        String strF = element.attr("data-title=", "Faculty Name").ownText();
                        sathyaFaculty.add(strF);
                    }
                    else if(flag==3){
                        String strD = element.attr("data-title=", "Start Date").ownText();
                        sathyaDate.add(strD);
                    }
                    else if(flag==5)
                    {
                        flag=0;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Log.e("Course:==>",course.get(0).toString());
            Log.e("Faculty:==>",faculty.get(0).toString());
            Log.e("Date:==>",date.get(0).toString());
            textView1.setText(course.get(0).toString());
            textView2.setText(faculty.get(0).toString());
            textView3.setText(date.get(0).toString());
//            textView4.setText(course.get(1).toString());
//            textView5.setText(faculty.get(1).toString());
//            textView6.setText(date.get(1).toString());
//            textView7.setText(course.get(2).toString());
//            textView8.setText(faculty.get(2).toString());
//            textView9.setText(date.get(2).toString());
//            textView1.setText(sathyaCourse.get(0).toString());
//            textView2.setText(sathyaFaculty.get(0).toString());
//            textView3.setText(sathyaDate.get(0).toString());
            textView4.setText(sathyaCourse.get(1).toString());
            textView5.setText(sathyaFaculty.get(1).toString());
            textView6.setText(sathyaDate.get(1).toString());
//            textView7.setText(sathyaCourse.get(2).toString());
//            textView8.setText(sathyaFaculty.get(2).toString());
//            textView9.setText(sathyaDate.get(2).toString());
            /*Log.e("Sathya Course==>",sathyaCourse.get(0).toString());
            Log.e("Sathya Faculty==>",sathyaFaculty.get(0).toString());
            Log.e("Sathya Date==>",sathyaDate.get(0).toString());
            Log.e("Sathya Course==>",sathyaCourse.get(2).toString());
            Log.e("Sathya Faculty==>",sathyaFaculty.get(2).toString());
            Log.e("Sathya Date==>",sathyaDate.get(2).toString());*/
            progressDialog.dismiss();
        }
    }
}
