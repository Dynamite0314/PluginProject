package com.example.pluginproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.standard.ActivityInterFace;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
        PluginManager.getInstance().loadPlugin();
    }

    public void loadPlugin() {
        //mPluginManager.loadPlugin();
    }

    public void startPluginActivity() {
        //Intent intent = new Intent(this, ProxyActivity.class);
        //startActivity(intent);

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "plugin_package-debug.apk");
        PackageManager packageManager = getPackageManager();
        //获得插件的AndroidManifest中注册的Activity
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
        ActivityInfo activityInfo = packageInfo.activities[0];
        //实际启动ProxyActivity
        Intent intent = new Intent(this,ProxyActivity.class);
        //把插件Activity类名传过去
        intent.putExtra("className",activityInfo.name);
        startActivity(intent);
    }

    public void startPlugin(View view) {
        startPluginActivity();
    }
}
