package com.oneweek.miluo.codingunbarred;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1=(TextView)findViewById(R.id.textView);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/segoeuil.ttf");
        tv1.setTypeface(face);

        Typeface buttonFace = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);
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
        startActivity(intent);
    }
}
