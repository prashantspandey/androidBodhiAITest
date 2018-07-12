package com.example.prashant.bodhiai.Dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.prashant.bodhiai.Infrastructure.BodhiApplication;

public class BaseDialog  extends DialogFragment{
        protected BodhiApplication application;


    @Override
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);

        application = (BodhiApplication) getActivity().getApplication();
    }

}
