package com.example.prashant.bodhiai.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prashant.bodhiai.Interfaces.StudentHomePageInterface;
import com.example.prashant.bodhiai.POJO.IndividualTestDetails;
import com.example.prashant.bodhiai.R;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeforeTestActivity extends AppCompatActivity {


    private TextView subject,timing,topics,numQuestions;
    private Button takeTestButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_test);

        subject = findViewById(R.id.before_test_subject);
        timing = findViewById(R.id.before_test_time);
        topics = findViewById(R.id.before_test_topics);
        numQuestions = findViewById(R.id.before_test_numQuestions);
        takeTestButton = findViewById(R.id.before_test_startTestButton);
        Intent idIntent = getIntent();
        String test_id = idIntent.getStringExtra("test_id");
        Log.d("beforetest",test_id);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(StudentHomePageInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        StudentHomePageInterface testDetailsInterface  = retrofit.create(StudentHomePageInterface.class);
        Call<IndividualTestDetails> call = testDetailsInterface.getIndividualTestDetails(test_id);
        call.enqueue(new Callback<IndividualTestDetails>() {
            @Override
            public void onResponse(Call<IndividualTestDetails> call, Response<IndividualTestDetails> response) {
                if (response.code() == 200){
                    Log.d("TestID","success");
                    IndividualTestDetails details = response.body();
                    Toast.makeText(BeforeTestActivity.this, details.getTime(),Toast.LENGTH_SHORT).show();
                    subject.setText(details.getSubject());
                    String listTopics = Arrays.toString(details.getTopics());
                    listTopics = listTopics.replace(",","\n");
                    listTopics = listTopics.replace("["," ");
                    listTopics = listTopics.replace("]","");


                    topics.setText(listTopics);
                    numQuestions.setText(details.getNumQuestions());

                }
                else{
                    Log.d("beforetest", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<IndividualTestDetails> call, Throwable t) {
                Log.d("beforetest", String.valueOf(t));

            }
        });

    }
}
