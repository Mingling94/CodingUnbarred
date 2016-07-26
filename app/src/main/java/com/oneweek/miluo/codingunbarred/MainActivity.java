package com.oneweek.miluo.codingunbarred;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iconify.with(new FontAwesomeModule());

        TextView tv1=(TextView)findViewById(R.id.title);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/segoeuil.ttf");
        tv1.setTypeface(face);

        Typeface buttonFace = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        Button b1=(Button)findViewById(R.id.courses);
        Button b2=(Button)findViewById(R.id.resources);
        Button b3=(Button)findViewById(R.id.sandbox);
        b1.setTypeface(buttonFace);
        b2.setTypeface(buttonFace);
        b3.setTypeface(buttonFace);
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
        Intent intent = new Intent(this, DevModeActivity.class);
        //intent.putExtra("LESSON_NAME", "lesson1");
        startActivity(intent);
    }
}
