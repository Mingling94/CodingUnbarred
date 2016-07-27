package com.oneweek.miluo.codingunbarred;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ResourceSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_selection);
        WebView resourceBox = (WebView)findViewById(R.id.resourceBox);
        resourceBox.getSettings().setJavaScriptEnabled(true);
        resourceBox.loadUrl("file:///android_asset/resources/HTML_Cheat_Sheet.html");
    }
}
