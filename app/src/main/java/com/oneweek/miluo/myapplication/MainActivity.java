package com.oneweek.miluo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import android.view.View;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.content.Intent;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Courses button
    public void showCourseSelection(View view) {
        Intent intent = new Intent(this, CourseSelectionActivity.class);
        startActivity(intent);
    }

    // Resources button
    public void showResourceSelection(View view) {
        Intent intent = new Intent(this, ResourceSelectionActivity.class);
        startActivity(intent);
    }

    // Sandbox button
    public void showSandbox(View view) {
        Intent intent = new Intent(this, DeveloperModeActivity.class);
        startActivity(intent);
    }
}
