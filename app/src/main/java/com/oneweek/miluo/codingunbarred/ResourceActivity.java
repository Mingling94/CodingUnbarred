package com.oneweek.miluo.codingunbarred;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.webkit.WebViewClient;

public class ResourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        WebView webview;
        webview = (WebView) findViewById(R.id.wv);
        webview.setWebViewClient(new WebC());

        // Enable Javascript
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadUrl("file:///assets/htmlCheatSheet.html");
        //webview.loadUrl("http://google.com");

    }

    public class WebC extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
