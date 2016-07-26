package com.oneweek.miluo.codingunbarred;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DevModeActivity extends AppCompatActivity {

    private static final String LESSON_NAME = "LESSON_NAME";

    private static final String HTML_EXTENSION = ".html";

    private static final String CSS_EXTENSION = ".css";

    private static final String JS_EXTENSION = ".js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devmode);
        WebView webBox = (WebView)findViewById(R.id.WebBox);
        //todo load webBox from our saved dynamic storage


        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);


        //code to load in the lesson
        WebView lessonBox = (WebView)findViewById(R.id.LessonBox);
        lessonBox.getSettings().setJavaScriptEnabled(true);
        lessonBox.loadUrl("file:///android_asset/lessons/" + lessonName + "/" + lessonName + ".html");


        EditText htmlEditor = (EditText)findViewById(R.id.HTMLBox);
        EditText cssEditor = (EditText)findViewById(R.id.CSSBox);
        EditText jsEditor = (EditText)findViewById(R.id.JSBox1);

        String htmlSnippet = tryReadFile(getFilename(lessonName, HTML_EXTENSION));
        String cssSnippet = tryReadFile(getFilename(lessonName, CSS_EXTENSION));
        String jsSnippet = tryReadFile(getFilename(lessonName, JS_EXTENSION));

        if (htmlSnippet != null && cssSnippet != null && jsSnippet != null) {
            htmlEditor.setText(htmlSnippet);
            cssEditor.setText(cssSnippet);
            jsEditor.setText(jsSnippet);
        } else {
            // TODO: populate snippets from assets

            this.saveCodeSnippets();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.saveCodeSnippets();
    }

    private void saveCodeSnippets() {

        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);

        EditText htmlEditor = (EditText)findViewById(R.id.HTMLBox);
        EditText cssEditor = (EditText)findViewById(R.id.CSSBox);
        EditText jsEditor = (EditText)findViewById(R.id.JSBox1);

        saveFile(getFilename(lessonName, HTML_EXTENSION), htmlEditor.getText().toString());
        saveFile(getFilename(lessonName, CSS_EXTENSION), cssEditor.getText().toString());
        saveFile(getFilename(lessonName, JS_EXTENSION), jsEditor.getText().toString());
    }

    private String getFilename(String lessonName, String suffix) {
        return new File(new File(lessonName), lessonName + suffix).getPath();
    }

    private String tryReadFile(String filename) {
        FileInputStream inputStream;
        StringBuilder buffer = new StringBuilder();;

        try {
            inputStream = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();
            while (line != null) {
                buffer.append(buffer);
                line = reader.readLine();
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return buffer.toString();
    }

    private void saveFile(String filename, String snippet) {
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(snippet.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
