package com.oneweek.miluo.codingunbarred;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResourceSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_selection);
    }

    // Resources button
    public void showResource(View view) {
        Intent intent = new Intent(this, ResourceActivity.class);
        startActivity(intent);
    }
}
