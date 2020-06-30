package com.example.plugin_package;

import android.content.Intent;
import android.content.IntentFilter;
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
                Log.d("dongp", "btn_go_second onclick");
                startActivity(new Intent(appActivity, PluginSecondActivity.class));
            }
        });
        appActivity.findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dongp", "btn_start_service onclick");
                appActivity.startService(new Intent(appActivity, PluginService.class));
            }
        });

        appActivity.findViewById(R.id.btn_reg_broad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dongp", "btn_reg_broad onclick");
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.dongp.hahahaha");
                appActivity.registerReceiver(new MyReceiver(), intentFilter);
            }
        });

        appActivity.findViewById(R.id.btn_send_broad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dongp", "btn_send_broad onclick");
                Intent intent = new Intent();
                intent.setAction("com.dongp.hahahaha");
                intent.putExtra("dongp","dynamite");
                appActivity.sendBroadcast(intent);
            }
        });
    }
}
