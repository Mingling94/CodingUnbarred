package com.oneweek.miluo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.oneweek.miluo.codingunbarred.R;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iconify.with(new FontAwesomeModule());
    }
}
