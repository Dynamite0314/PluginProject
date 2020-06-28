package com.example.pluginproject;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.standard.ActivityInterFace;

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

}
