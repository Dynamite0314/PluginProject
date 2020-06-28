package com.example.pluginproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/28
 * description: xxxx
 *
 *********************************************************/
public class PluginManager {
    private Context appContext;
    private DexClassLoader dexClassLoader;
    private static final String ADD_ASSET_PATH = "addAssetPath";
    private Resources pluginResources;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        return InnerHolder.instance;
    }

    public static class InnerHolder {
        public static PluginManager instance = new PluginManager();
    }

    public PluginManager init(Context appContext) {
        this.appContext = appContext;
        return this;
    }

    /**
     * 加载插件
     */
    public void loadPlugin() {
        if (appContext == null)
            throw new IllegalStateException("没有调用初始化init方法");
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "plugin_package-debug.apk");
            if (!file.exists()) {
                return;
            }
            //插件apk在sdk卡下面的绝对路径
            String pluginApkPath = file.getAbsolutePath();

            //加载class
            File fileDir = appContext.getDir("pDir", Context.MODE_PRIVATE);
            dexClassLoader = new DexClassLoader(pluginApkPath, fileDir.getAbsolutePath(), null, appContext.getClassLoader());

            //加载layout
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod(ADD_ASSET_PATH, String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager, pluginApkPath);
            Resources appResources = appContext.getResources();
            pluginResources = new Resources(assetManager, appResources.getDisplayMetrics(), appResources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("dongp","ee="+e.toString());
        }
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getPluginResources() {
        return pluginResources;
    }
}
