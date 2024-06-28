package com.example.bsit_3a;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebApi extends AsyncTask<String, String, String>
{
    DataAccess _db;

    String studentId;

    String birthdate;

    ProgressBar progressBar;


    public WebApi(Context context, String studentId,String birthdate, ProgressBar progressBar)
    {
        _db = new DataAccess(context);
        this.studentId = studentId;
        this.birthdate = birthdate;
        this.progressBar = progressBar;

    }
    @Override
    protected  void onPreExecute(){
        super.onPreExecute();

        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        try{
            URL url;
            HttpURLConnection urlConnection = null;
            try {

                url = new URL("http://192.168.1.121:5100/getStudRecord?studentId=" + studentId + "&birthdate=" + birthdate);
                //open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);
                int data = isw.read();
                while (data != -1)
                {
                    result += (char) data;
                    data = isw.read();
                }

                //return the data to onPostExecute method
                return result;

            } catch ( Exception e)
            {
                e.printStackTrace();

            }finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            return "Exception:" + e.getMessage();
        }
        return result;
    }
    @Override
    protected void onPostExecute(String s){
        SchoolYear sy;
        Grade grade;

        try{
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray;
            jsonArray = jsonObject.getJSONArray("schoolYears");

            _db.SchoolYearDeleteAll();
            _db.GradeDeleteAll();

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject e = jsonArray.getJSONObject(i);

                sy = new SchoolYear();
                sy.SYId = e.getInt("syId");
                sy.SchoolYearSemester = e.getString("semester");
                sy.id = e.getInt("id");
                sy.SchoolYearStart = e.getString("schoolYearStart");
                sy.SchoolYearEnd = e.getString("schoolYearEnd");

                _db.SchoolYearAdd(sy);
            }
            jsonArray= jsonObject.getJSONArray("grades");

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject e = jsonArray.getJSONObject(i);

                grade = new Grade();

                grade.SyId = e.getInt("syId");
                grade.SubjectCode = e.getString("subjectCode");
                grade.SubjectDescription = e.getString("subjectDescription");
                grade.Grade = e.getString("gradeValue");

                _db.GradeAdd( grade );
            }
            progressBar.setVisibility(View.GONE);
        }catch ( JSONException e){
            e.printStackTrace();
        }

    }


}
