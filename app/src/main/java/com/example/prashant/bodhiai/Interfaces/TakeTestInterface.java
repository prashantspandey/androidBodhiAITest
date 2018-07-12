package com.example.prashant.bodhiai.Interfaces;

import com.example.prashant.bodhiai.POJO.TakeTestFirstPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TakeTestInterface {
        //String BASE_URL = "http://10.0.2.2:8000/api/";
        String BASE_URL = "http://www.bodhiai.in/api/papers/";
            @FormUrlEncoded
            @POST("papers/individual_test_first/")
            Call<List<TakeTestFirstPOJO>> getTestFirstDetails(@Header("Authorization") String key, @Field("takeTest") String testid);

}
