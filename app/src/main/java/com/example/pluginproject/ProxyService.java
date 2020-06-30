package com.example.pluginproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.standard.ServiceInterFace;

import androidx.annotation.Nullable;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/30
 * description: xxxx
 *
 *********************************************************/
public class ProxyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra("className");
        try {
            Class<?> pluginClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            ServiceInterFace interFace = (ServiceInterFace) pluginClass.newInstance();
            interFace.onStartCommand(intent, flags, startId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
