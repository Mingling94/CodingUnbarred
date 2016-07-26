package com.oneweek.miluo.codingunbarred;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< b4795f1377a2673c9c5236ed9a805614f646bf82:app/src/main/java/com/oneweek/miluo/codingunbarred/MainActivity.java
import android.support.v7.app.AppCompatActivity;
import android.view.View;
=======
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
>>>>>>> Got the icons to workgit add .!:app/src/main/java/com/oneweek/miluo/myapplication/MainActivity.java

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends AppCompatActivity {
<<<<<<< b4795f1377a2673c9c5236ed9a805614f646bf82:app/src/main/java/com/oneweek/miluo/codingunbarred/MainActivity.java
    @Override
=======

>>>>>>> Got the icons to workgit add .!:app/src/main/java/com/oneweek/miluo/myapplication/MainActivity.java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iconify.with(new FontAwesomeModule());

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
