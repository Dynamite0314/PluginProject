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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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

    public void regPluginReceiver(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "plugin_package-debug.apk");
        Class packageParserClass = null;
        try {
            packageParserClass = Class.forName("android.content.pm.PackageParser");
            Method parsePackageMethod = packageParserClass.getDeclaredMethod("parsePackage", File.class, int.class);

            Object packageParser = packageParserClass.newInstance();
            // 调用parsePackage方法 返回PackageParser.Package
            Object packageObj = parsePackageMethod.invoke(packageParser, file, PackageManager.GET_ACTIVITIES);

            Field receiverField = packageObj.getClass().getDeclaredField("receivers");
            //拿到receivers  广播集合
            List receivers = (List) receiverField.get(packageObj);

            Class<?> componentClass = Class.forName("android.content.pm.PackageParser$Component");
            Field intentsField = componentClass.getDeclaredField("intents");


        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
