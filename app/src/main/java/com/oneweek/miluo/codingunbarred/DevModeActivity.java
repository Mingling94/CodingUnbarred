package com.oneweek.miluo.codingunbarred;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.File;

public class DevModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devmode);
        WebView webView = (WebView)findViewById(R.id.WebBox);
        File htmlFile = new File("file:///android_asset/webexample.html");
        if(htmlFile.exists()) {
            webView.loadUrl(htmlFile.getAbsolutePath());
        }
    }
}
