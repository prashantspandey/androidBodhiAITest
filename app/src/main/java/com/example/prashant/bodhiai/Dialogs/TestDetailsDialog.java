package com.example.prashant.bodhiai.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prashant.bodhiai.R;

public class TestDetailsDialog extends BaseDialog {


    private TextView dsubject, dnum_questions, dtopics;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_details_dialog,container,false);
        dsubject = view.findViewById(R.id.dialog_testDetails_subject);
        dnum_questions = view.findViewById(R.id.dialog_testDetails_numQuestions);
        dtopics = view.findViewById(R.id.dialog_testDetails_topics);
        return view;
    }

}
