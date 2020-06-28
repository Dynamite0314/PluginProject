package com.example.plugin_package;

import android.os.Bundle;

public class PluginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appActivity.setContentView(R.layout.activity_plugin);
    }
}
