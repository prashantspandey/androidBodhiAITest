package com.example.prashant.bodhiai.Interfaces;

import com.example.prashant.bodhiai.POJO.AuthKey;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthenticationInterface {
   //String BASE_URL = "http://10.0.2.2:8000/";
    String BASE_URL = "http://www.bodhiai.in/";
    @FormUrlEncoded
    @POST("rest-auth/login/")
    Call<AuthKey>  login(@Field("username") String username, @Field("password") String password);
}
