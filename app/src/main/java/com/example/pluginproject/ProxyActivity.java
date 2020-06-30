package com.example.pluginproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.standard.ActivityInterFace;
import com.example.standard.BroadcastInterFace;

import java.lang.reflect.Constructor;

import androidx.annotation.Nullable;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/28
 * description: 代理的activity
 *
 *********************************************************/
public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String className = getIntent().getStringExtra("className");
            Class<?> pluginClass = getClassLoader().loadClass(className);
            Constructor<?> constructor = pluginClass.getConstructor(new Class[]{});
            Object pluginActivity = constructor.newInstance(new Object[]{});

            //强转为对应的接口
            ActivityInterFace activityInterface = (ActivityInterFace) pluginActivity;
            activityInterface.insertAppContext(this);

            Bundle bundle = new Bundle();
            bundle.putString("content", "从宿主传过来");
            //执行插件中的方法
            activityInterface.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("dongp","exception=="+e.toString());
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className);
        super.startActivity(intent1);
    }

    @Override
    public ComponentName startService(Intent service) {
        String className = service.getComponent().getClassName();
        service = new Intent(this, ProxyService.class);
        service.putExtra("className", className);
        return super.startService(service);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        IntentFilter intentFilter = new IntentFilter();
        for (int i = 0; i < filter.countActions(); i++) {
            intentFilter.addAction(filter.getAction(i));
        }
        return super.registerReceiver(new ProxyReceiver(receiver.getClass().getName()), intentFilter);
    }

}
