package com.example.prashant.bodhiai.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.prashant.bodhiai.Interfaces.StudentHomePageInterface;
import com.example.prashant.bodhiai.POJO.PreviousPerformance;
import com.example.prashant.bodhiai.POJO.TestDetails;
import com.example.prashant.bodhiai.R;
import com.example.prashant.bodhiai.TestDetailsAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends BaseAuthenticatedActivity {

     private ArrayList<String> mSubjects = new ArrayList<>();
    private ArrayList<String> mnumQuestions = new ArrayList<>();
    private ArrayList<String> mPublished = new ArrayList<>();
    private ArrayList<String> mId = new ArrayList<>();
    private List<List<String>> mtopics = new ArrayList<List<String>>();
    ArrayList<String> tps = new ArrayList<String>();
    private String [] dates = null;
    private String [] marks = null;
    @Override
    protected void onBodhiCreate(Bundle savedInstanceState) {
       //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferencesKey = getSharedPreferences("authToken", Context.MODE_PRIVATE);
        String key = sharedPreferencesKey.getString("key","");
        Toast.makeText(HomeActivity.this,application.getAuth().getStudentUser().getKey(),Toast.LENGTH_SHORT).show();
        final String token_key = "Token " + key;
        Log.d("reached till call",token_key);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(StudentHomePageInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        StudentHomePageInterface testDetailsInterface  = retrofit.create(StudentHomePageInterface.class);
        Call<List<TestDetails>> call = testDetailsInterface.getTestDetails(token_key);
        Call<List<PreviousPerformance>> prevCall = testDetailsInterface.getPreviousPerformance(token_key);


       // API call for getting test details


        call.enqueue(new Callback<List<TestDetails>>() {
            @Override
            public void onResponse(Call<List<TestDetails>> call, Response<List<TestDetails>> response) {
                if (response.code() == 200){
                    Toast.makeText(HomeActivity.this,"Success !!!!",Toast.LENGTH_SHORT).show();
                    List<TestDetails> details =response.body();


                    for (TestDetails detail: details){
                        mSubjects.add(detail.getSubject());
                        mPublished.add(detail.getPublished());
                        String [] topics = detail.getTopics();
                        mId.add(detail.getId());

                        for (int i = 0 ; i < topics.length; i++){
                            tps.add(topics[i]);
                        }
                        mnumQuestions.add(detail.getNum_questions()+" questions");
                        initTestDetails();

                        mtopics.add(Arrays.asList(topics));
                    }
                }
                else{
                    Log.d("some errro", String.valueOf(response.errorBody()));
                    Log.d("some errro", String.valueOf(response.code()));
                    Toast.makeText(HomeActivity.this,"Some error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TestDetails>> call, Throwable t) {
                Log.d("some errro", String.valueOf(t.toString()));
                Toast.makeText(HomeActivity.this,t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        //------------------------------------------------------------
        // API call to get previous performance of the student
        prevCall.enqueue(new Callback<List<PreviousPerformance>>() {
            @Override
            public void onResponse(Call<List<PreviousPerformance>> call, Response<List<PreviousPerformance>> response) {
                if (response.code()==200) {
                    List<PreviousPerformance> performances = response.body();
                    for (PreviousPerformance performance: performances){
                        Log.d("performanceSubject",performance.getSubject());
                        dates = performance.getDate();
                        marks = performance.getMarks();
                        break;

                    }

                    initPreviousPerformanceChart(dates,marks);
                }
                else {
                    Log.d("previous perfromace", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<PreviousPerformance>> call, Throwable t) {
                Log.d("call failed", t.toString());

            }
        });
    }

    private void initTestDetails() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView testDetailsRecyclerView = findViewById(R.id.testDetails_recyclerView);
        testDetailsRecyclerView.setLayoutManager(layoutManager);
        TestDetailsAdapter adapter = new TestDetailsAdapter(mSubjects,mnumQuestions,mPublished,mtopics,mId,this);
        Log.d("test details",mtopics.toString());
        testDetailsRecyclerView.setAdapter(adapter);
    }
    private void initPreviousPerformanceChart(String [] x,String [] y){
        BarChart lineChart = findViewById(R.id.prevPerformanceChart);
        List<BarEntry>  xEntries = new ArrayList<>();

        for (int i =0 ; i<y.length;i++){
            xEntries.add(new BarEntry(i,Float.valueOf(y[i])));
            Log.d("marksofchart", String.valueOf(Float.valueOf(y[i])));
        }
        BarDataSet dataSet = new BarDataSet(xEntries,"Previous Performance");
        BarData lineData = new BarData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }
}
