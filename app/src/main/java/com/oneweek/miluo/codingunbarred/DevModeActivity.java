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
import java.io.InputStream;
import java.io.InputStreamReader;

public class DevModeActivity extends AppCompatActivity {

    public static final String LESSON_NAME = "LESSON_NAME";

    private static final String HTML_EXTENSION = ".html";

    private static final String CSS_EXTENSION = ".css";

    private static final String JS_EXTENSION = ".js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devmode);
        WebView webBox = (WebView)findViewById(R.id.WebBox);

        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);

        //code to load in the lesson
        WebView lessonBox = (WebView)findViewById(R.id.LessonBox);
        lessonBox.getSettings().setJavaScriptEnabled(true);
        lessonBox.loadUrl("file:///android_asset/lessons/" + lessonName + "/" + lessonName + ".html");

        String htmlSnippet = tryReadFile(getSavedSnippetFilename(lessonName, HTML_EXTENSION));
        String cssSnippet = tryReadFile(getSavedSnippetFilename(lessonName, CSS_EXTENSION));
        String jsSnippet = tryReadFile(getSavedSnippetFilename(lessonName, JS_EXTENSION));

        if (htmlSnippet != null && cssSnippet != null && jsSnippet != null) {
            this.populateEditors(htmlSnippet, cssSnippet, jsSnippet);
        } else {
            this.resetCodeSnippets();
        }

        this.runCode();
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.saveCodeSnippets();
    }

    private void runCode() {
        this.saveCodeSnippets();

        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);

        WebView webBox = (WebView)findViewById(R.id.WebBox);
        webBox.getSettings().setJavaScriptEnabled(true);
        webBox.loadUrl(getSavedSnippetFilename(lessonName, HTML_EXTENSION));
    }

    private void populateEditors(String htmlSnippet, String cssSnippet, String jsSnippet) {
        EditText htmlEditor = (EditText)findViewById(R.id.HTMLBox);
        EditText cssEditor = (EditText)findViewById(R.id.CSSBox);
        EditText jsEditor = (EditText)findViewById(R.id.JSBox1);

        htmlEditor.setText(htmlSnippet);
        cssEditor.setText(cssSnippet);
        jsEditor.setText(jsSnippet);
    }

    private void resetCodeSnippets() {
        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);

        String htmlSnippet = tryReadAsset(getDefaultSnippetFilename(lessonName, HTML_EXTENSION));
        String cssSnippet = tryReadAsset(getDefaultSnippetFilename(lessonName, CSS_EXTENSION));
        String jsSnippet = tryReadAsset(getDefaultSnippetFilename(lessonName, JS_EXTENSION));

        this.populateEditors(htmlSnippet, cssSnippet, jsSnippet);
        this.saveCodeSnippets();
    }

    private void saveCodeSnippets() {
        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);

        EditText htmlEditor = (EditText)findViewById(R.id.HTMLBox);
        EditText cssEditor = (EditText)findViewById(R.id.CSSBox);
        EditText jsEditor = (EditText)findViewById(R.id.JSBox1);

        saveFile(getSavedSnippetFilename(lessonName, HTML_EXTENSION), htmlEditor.getText().toString());
        saveFile(getSavedSnippetFilename(lessonName, CSS_EXTENSION), cssEditor.getText().toString());
        saveFile(getSavedSnippetFilename(lessonName, JS_EXTENSION), jsEditor.getText().toString());
    }

    private String getDefaultSnippetFilename(String lessonName, String suffix) {
        return "file:///android_asset/" + lessonName + "/snippets/" + lessonName + suffix;
    }

    private String getSavedSnippetFilename(String lessonName, String suffix) {
        return new File(new File(lessonName), lessonName + suffix).getPath();
    }

    private String tryReadAsset(String filename) {
        InputStream inputStream;
        StringBuilder buffer = new StringBuilder();

        try {
            inputStream = getAssets().open(filename);
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

    private String tryReadFile(String filename) {
        FileInputStream inputStream;
        StringBuilder buffer = new StringBuilder();

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
