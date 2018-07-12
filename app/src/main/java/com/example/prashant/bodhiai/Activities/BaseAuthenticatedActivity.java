package com.example.prashant.bodhiai.Activities;

import android.content.Intent;
import android.os.Bundle;

public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected final void onCreate(Bundle savedState){
        super.onCreate(savedState);

        if (!application.getAuth().getStudentUser().isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }
        onBodhiCreate(savedState);
                
    }

    protected abstract void onBodhiCreate(Bundle savedState);
}
