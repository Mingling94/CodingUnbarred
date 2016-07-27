package com.oneweek.miluo.codingunbarred;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.IOException;

public class LessonSelectionActivity extends AppCompatActivity {
    public final static String LESSON_NAME = "LESSON_NAME";

    private String[] lessonNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_selection);

        // Get lesson names
        Intent intent = getIntent();
        final String courseName = intent.getStringExtra(CourseSelectionActivity.COURSE_NAME);
        AssetManager assets = getAssets();
        try {
            lessonNames = assets.list(CourseSelectionActivity.COURSE_DIRECTORY + '/' + courseName);
        } catch (IOException e) {
            return;
        }

        // Set Grid's adapter
        GridView grid = (GridView) findViewById(R.id.lesson_grid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lessonNames);
        grid.setAdapter(adapter);

        // Set on click listener per grid item
        final Context self = this;
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(self, LessonSelectionActivity.class);

                String lessonName = lessonNames[position];
                intent.putExtra(CourseSelectionActivity.COURSE_NAME, courseName);
                intent.putExtra(LessonSelectionActivity.LESSON_NAME, lessonName);

                startActivity(intent);
            }
        });
    }
}
