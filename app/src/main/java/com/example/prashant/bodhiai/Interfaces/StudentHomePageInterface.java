package com.example.prashant.bodhiai.Interfaces;

import com.example.prashant.bodhiai.POJO.IndividualTestDetails;
import com.example.prashant.bodhiai.POJO.PreviousPerformance;
import com.example.prashant.bodhiai.POJO.TestDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StudentHomePageInterface {

    //String BASE_URL = "http://10.0.2.2:8000/api/";
    String BASE_URL = "http://www.bodhiai.in/api/";

    @GET("papers/paper_details_android/")
    Call<List<TestDetails>>  getTestDetails(@Header("Authorization") String key);

    @GET("basicinformation/student_previous_performance_android/")
    Call <List<PreviousPerformance>> getPreviousPerformance(@Header("Authorization") String key);


    @FormUrlEncoded
    @POST("papers/individual_test_details/")
    Call<IndividualTestDetails> getIndividualTestDetails(@Field("testid") String id);
}
