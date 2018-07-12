package com.example.prashant.bodhiai.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prashant.bodhiai.Interfaces.AuthenticationInterface;
import com.example.prashant.bodhiai.POJO.AuthKey;
import com.example.prashant.bodhiai.R;
import com.example.prashant.bodhiai.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends BaseActivity {
    EditText username_text;
    EditText password_text;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_text = findViewById(R.id.login_username_text);
        password_text = findViewById(R.id.login_password_text);
        login_button = findViewById(R.id.login_button);








        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        final String username = username_text.getText().toString();
        final String password = password_text.getText().toString();
                Log.d("username",username);
                Log.d("password",password);
                User currentUser = new User(username,password);
         Retrofit.Builder builder = new Retrofit.Builder().baseUrl(AuthenticationInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        AuthenticationInterface account = retrofit.create(AuthenticationInterface.class);
        Call<AuthKey> call = account.login(username,password);

        call.enqueue(new Callback<AuthKey>() {
            @Override
            public void onResponse(Call<AuthKey> call, Response<AuthKey> response) {
                if ( response.code() == 200) {
                        AuthKey auth = response.body();
                    SharedPreferences sharedPreferencesKey = getSharedPreferences("authToken", Context.MODE_PRIVATE);
                    SharedPreferences.Editor keyEditor  = sharedPreferencesKey.edit();
                    keyEditor.putString("key",auth.getKey());
                    keyEditor.apply();
                    application.getAuth().getStudentUser().setLoggedIn(true);
                    application.getAuth().getStudentUser().setKey(auth.getKey());
                        Log.d("success",auth.getKey());
                    Intent loginIntent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(loginIntent);
                    finish();

                }
                else{
                    Log.d("error code", String.valueOf(response.code()));
                    Toast.makeText(MainActivity.this,"Sorry, something was wrong! Please try again!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AuthKey> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();

            }
        });


            }
        });

    }

}

