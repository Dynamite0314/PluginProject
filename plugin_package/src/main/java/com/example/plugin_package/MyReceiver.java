package com.example.plugin_package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.standard.BroadcastInterFace;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/30
 * description: xxxx
 *
 *********************************************************/
public class MyReceiver extends BroadcastReceiver implements BroadcastInterFace {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("dongp", "i get receive on plugin=="+intent.getStringExtra("dongp"));
    }
}
