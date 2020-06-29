package com.example.plugin_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.standard.ActivityInterFace;

import androidx.appcompat.app.AppCompatActivity;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/28
 * description: xxxx
 *
 *********************************************************/
public class BaseActivity extends AppCompatActivity implements ActivityInterFace {
    public Activity appActivity;

    @Override
    public void insertAppContext(Activity activity) {
        appActivity = activity;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra("className",intent.getComponent().getClassName());
        appActivity.startActivity(intent);
    }
}
