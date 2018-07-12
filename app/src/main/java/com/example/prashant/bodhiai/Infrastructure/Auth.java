package com.example.prashant.bodhiai.Infrastructure;

import android.content.Context;

public class Auth {

    private final Context context;
    private StudentUser studentUser;

    public Auth(Context context) {
        this.context = context;
        studentUser = new StudentUser();
    }

    public StudentUser getStudentUser() {
        return studentUser;
    }
}
