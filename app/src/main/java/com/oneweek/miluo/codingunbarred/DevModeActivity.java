package com.oneweek.miluo.codingunbarred;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DevModeActivity extends AppCompatActivity {

    public static final String SANDBOX = "sandbox";

    public static final String COURSE_NAME = "COURSE_NAME";

    public static final String LESSON_NAME = "LESSON_NAME";

    private static final String HTML_EXTENSION = ".html";

    private static final String CSS_EXTENSION = ".css";

    private static final String JS_EXTENSION = ".js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devmode);

        Intent intent = getIntent();
        String courseName = intent.getStringExtra(COURSE_NAME);

        //code to load in the lesson
        WebView lessonBox = (WebView)findViewById(R.id.LessonBox);
        if (courseName.equals(SANDBOX)) {
            LinearLayout parentLayout = (LinearLayout)findViewById((R.id.DevModeLayout));
            parentLayout.removeView(lessonBox);
        } else {
            lessonBox.getSettings().setJavaScriptEnabled(true);
            lessonBox.loadUrl(getAssetLessonFilename());
        }

        String htmlSnippet = tryReadFile(getSavedSnippetFile(HTML_EXTENSION));
        String cssSnippet = tryReadFile(getSavedSnippetFile(CSS_EXTENSION));
        String jsSnippet = tryReadFile(getSavedSnippetFile(JS_EXTENSION));

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

    protected void resetButtonClick(View v) {
        resetCodeSnippets();
    }

    protected void runButtonClick(View v) {
        runCode();
    }

    private void runCode() {
        this.saveCodeSnippets();

        WebView webBox = (WebView)findViewById(R.id.WebBox);
        webBox.getSettings().setJavaScriptEnabled(true);
        webBox.getSettings().setDomStorageEnabled(true);
        webBox.getSettings().setDatabaseEnabled(true);

        File file = getSavedSnippetFile(HTML_EXTENSION);
        webBox.loadUrl("file:///" + file.getPath());
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
        String htmlSnippet = tryReadAsset(getAssetSnippetFilename(HTML_EXTENSION));
        String cssSnippet = tryReadAsset(getAssetSnippetFilename(CSS_EXTENSION));
        String jsSnippet = tryReadAsset(getAssetSnippetFilename(JS_EXTENSION));

        this.populateEditors(htmlSnippet, cssSnippet, jsSnippet);
        this.saveCodeSnippets();
    }

    private void saveCodeSnippets() {
        EditText htmlEditor = (EditText)findViewById(R.id.HTMLBox);
        EditText cssEditor = (EditText)findViewById(R.id.CSSBox);
        EditText jsEditor = (EditText)findViewById(R.id.JSBox1);

        File htmlFilename = getSavedSnippetFile(HTML_EXTENSION);
        String htmlCode = htmlEditor.getText().toString();
        saveFile(htmlFilename, htmlCode);

        saveFile(getSavedSnippetFile(CSS_EXTENSION), cssEditor.getText().toString());
        saveFile(getSavedSnippetFile(JS_EXTENSION), jsEditor.getText().toString());
    }

    private String getAssetLessonFilename() {
        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);
        String courseName = intent.getStringExtra(COURSE_NAME);

        return "file:///android_asset/courses/" + courseName + "/" + lessonName + "/" + lessonName + HTML_EXTENSION;
    }

    private String getAssetSnippetFilename(String suffix) {
        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);
        String courseName = intent.getStringExtra(COURSE_NAME);

        return "courses/" + courseName + "/" + lessonName + "/preloadedCode/" + lessonName + suffix;
    }

    private File getSavedSnippetFile(String suffix) {
        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(LESSON_NAME);
        String courseName = intent.getStringExtra(COURSE_NAME);

        return new File(new File(new File(getFilesDir(), courseName), lessonName), lessonName + suffix);
    }

    private String tryReadAsset(String filename) {
        InputStream inputStream;
        StringBuilder buffer = new StringBuilder();

        try {
            inputStream = getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                buffer.append("\n");
                line = reader.readLine();
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            // do nothing and return empty string (hacky :<)
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return buffer.toString();
    }

    private String tryReadFile(File file) {
        if (!file.exists())
        {
            return null;
        }

        StringBuilder buffer = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                buffer.append("\n");
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return buffer.toString();
    }

    private void saveFile(File file, String snippet) {
        try {
            if (!file.exists()) {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            writer.write(snippet);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
