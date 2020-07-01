package com.example.pluginproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HookActivity extends AppCompatActivity {


    private Button btnHook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        btnHook = findViewById(R.id.btn_hook);
        btnHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dongp", "btnHook on click");
            }
        });
        hook(btnHook);
    }

    private void hook(View view) {
        try {
            //通过view的getListenerInfo方法  拿到ListenerInfo
            Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo", new Class[]{});
            getListenerInfoMethod.setAccessible(true);
            Object listenerInfo = getListenerInfoMethod.invoke(view);

            //从ListenerInfo 中拿到mOnClickListener的属性对应的值
            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);


            //创建自己的hookedListener
            //View.OnClickListener hookedOnClickListener = (View.OnClickListener) Proxy.newProxyInstance(view.getClass().getClassLoader(), new Class[]{View.OnClickListener.class}, new MyInvokeHandler(originOnClickListener));
            View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originOnClickListener);

            //修改mOnClickListener为 hookedListener
            mOnClickListener.set(listenerInfo, hookedOnClickListener);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public class HookedClickListenerProxy implements View.OnClickListener {

        private View.OnClickListener origin;

        public HookedClickListenerProxy(View.OnClickListener origin) {
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Log.d("dongp", "hook hahaha");
            if (origin != null) {
                origin.onClick(v);
            }
        }

    }

    private class MyInvokeHandler implements InvocationHandler {
        private View.OnClickListener onClickListener;

        public MyInvokeHandler(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.d("dongp", "i love you");
            return method.invoke(onClickListener, args);
        }
    }
}
