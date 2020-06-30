package com.example.plugin_package;

import android.content.Intent;
import android.util.Log;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/30
 * description: xxxx
 *
 *********************************************************/
public class PluginService extends BaseService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("dongp", "hello in plugin service");
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
