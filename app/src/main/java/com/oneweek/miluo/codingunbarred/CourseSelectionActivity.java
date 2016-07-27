package com.oneweek.miluo.codingunbarred;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;

public class CourseSelectionActivity extends AppCompatActivity {
    public final static String COURSE_NAME = "COURSE_NAME";
    public final static String COURSE_DIRECTORY = "courses";

    private String[] courseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        // Set title font
        TextView title =(TextView)findViewById(R.id.course_selection_title);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/segoeuil.ttf");
        title .setTypeface(face);

        // Get course names
        AssetManager assets = getAssets();
        try {
            courseNames = assets.list(COURSE_DIRECTORY);
        } catch (IOException e) {
            return;
        }

        // Set Grid's adapter
        GridView grid = (GridView) findViewById(R.id.course_grid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.grid_item, courseNames);
        grid.setAdapter(adapter);

        // Set on click listener per grid item
        final Context self = this;
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(self, LessonSelectionActivity.class);

                String courseName = courseNames[position];
                intent.putExtra(CourseSelectionActivity.COURSE_NAME, courseName);

                startActivity(intent);
            }
        });
    }
}
