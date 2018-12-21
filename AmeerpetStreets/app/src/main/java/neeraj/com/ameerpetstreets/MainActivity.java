package neeraj.com.ameerpetstreets;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ProgressDialog progressDialog;
    ListView listView;
    Elements coursee;
    ArrayList course=new ArrayList();
    ArrayList sathyaCourse=new ArrayList();
    ArrayList durgaCourse=new ArrayList();
    ArrayList inetSolvCourse=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv);
    }
    public void showData(View V)
    {
        Content content=new Content();
        content.execute();
    }
    public class Content extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            /*try
            {
                //NareshIt Data Fetch Starts
                Document document =Jsoup.connect(getString(R.string.nareshit_url)).get();
                for(int j=0;j<100;j++)
                {
                    Elements coursee=document.select("tbody[class=row-hover]").select("td[class=column-1]").eq(j);
                    for (int i=0;i<coursee.size();i++)
                    {
                        //String str=coursee.get(i).getElementsByAttribute("href").toString();
                        String str=coursee.select("b").eq(0).text();
                        course.add(str);
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }*/
            //Sathya Data Fetch
            /*try
            {
                Document document=Jsoup.connect(getString(R.string.sathya_url)).get();
                int length=document.select("div[class=fusion-text]").size();
                Log.e("Length==>",""+length);
                for(int j=0;j<100;j++)
                {
                    Elements coursee=document.select("table[class=col-md-12 table-bordered table-striped table-condensed cf]")
                            .select("td[data-title=Course Name]").eq(j);
                    for (int i=0;i<coursee.size();i++)
                    {
                        String str=coursee.text();
                        sathyaCourse.add(str);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            /*try {
                Document document=Jsoup.connect(getString(R.string.durga_url)).get();
                for(int j=0;j<100;j++)
                {
                    *//*Elements coursee=document.select("tbody").select("tr").select("td[height=30]")
                            .select("div[align=center]").select("p").eq(j);*//*
                    Elements coursee=document.select("div[align=center]").eq(j);
                    for (int i=0;i<coursee.size();i++)
                    {
                        String str=coursee.select("strong").select("a").text();
                        durgaCourse.add(str);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try
            {
                //<div class="col-md-12 col-sm-12 col-xs-12 table-responsive">
                //<div class="col-md-12 col-sm-12 col-xs-12 table-responsive">
                Document document=Jsoup.connect(getString(R.string.inetSolv_url)).get();

                for(int j=0;j<100;j++)
                {
                    //<td style="color:#000000" id="844">Oracle 18C/12C</td>
                        //coursee=document.select("div[col-md-12 col-sm-12 col-xs-12 table-responsive]").eq(0);
                    Element courseee=document.select("tbody").get(1);

                    //for (int i=0;i<courseee.size();i++)
                    //{
                        //<tr style="color:#000000;background-color:#ffffff">
                        //<td style="color:#000000"
                        Elements rows=courseee.getElementsByTag("tr");
                        for(Element row: rows)
                        {
                            String str=row.toString();
                            inetSolvCourse.add(str);
                        }
                    //}
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
            ArrayAdapter aa=new ArrayAdapter(MainActivity.this,android.R.layout.simple_dropdown_item_1line,inetSolvCourse);
            listView.setAdapter(aa);
            for(int i=0;i<course.size();i++)
            Log.e("Course==>",inetSolvCourse.get(i).toString());
            Log.e("Course size",""+inetSolvCourse.size());
            //Log.e("Coursee==>",""+coursee.toString());
            //Log.e("Coursee size",""+coursee.size());
            progressDialog.dismiss();
        }
    }
}
