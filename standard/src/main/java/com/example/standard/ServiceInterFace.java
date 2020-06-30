package com.example.standard;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/28
 * description: xxxx
 *
 *********************************************************/
public interface ServiceInterFace {

    //向插件注入宿主环境
    void insertAppContext(Service service);

    int onStartCommand(Intent intent, int flags, int startId);

    void onDestroy();

    IBinder onBind(Intent intent);


}
