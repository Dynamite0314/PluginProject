package com.example.plugin_package;

import android.os.Bundle;
import android.util.Log;

public class PluginSecondActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dongp","PluginSecondActivity onCreate");
        appActivity.setContentView(R.layout.activity_plugin_second);
    }
}
