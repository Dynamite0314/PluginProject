package com.example.pluginproject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.standard.BroadcastInterFace;
import com.example.standard.ServiceInterFace;

import androidx.annotation.Nullable;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/30
 * description: xxxx
 *
 *********************************************************/
public class ProxyReceiver extends BroadcastReceiver {
    private String name;

    public ProxyReceiver(String name) {
        this.name = name;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Class<?> pluginClass = null;
        try {
            pluginClass = PluginManager.getInstance().getDexClassLoader().loadClass(name);
            BroadcastInterFace interFace = (BroadcastInterFace) pluginClass.newInstance();
            interFace.onReceive(context, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
