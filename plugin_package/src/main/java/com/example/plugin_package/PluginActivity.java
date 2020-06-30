package com.example.plugin_package;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PluginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appActivity.setContentView(R.layout.activity_plugin);
        appActivity.findViewById(R.id.btn_go_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dongp","PluginActivity onclick");
                startActivity(new Intent(appActivity,PluginSecondActivity.class));
            }
        });
        appActivity.findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dongp","PluginActivity onclick");
                appActivity.startService(new Intent(appActivity,PluginService.class));
            }
        });
    }
}
