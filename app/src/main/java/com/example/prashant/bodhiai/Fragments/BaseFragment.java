package com.example.prashant.bodhiai.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.prashant.bodhiai.Infrastructure.BodhiApplication;

public abstract class BaseFragment extends Fragment {

    protected BodhiApplication application;


    @Override
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);

        application = (BodhiApplication) getActivity().getApplication();
    }

}
