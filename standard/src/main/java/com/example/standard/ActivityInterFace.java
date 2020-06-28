package com.example.standard;

import android.app.Activity;
import android.os.Bundle;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/28
 * description: xxxx
 *
 *********************************************************/
public interface ActivityInterFace {

    //向插件注入宿主环境
    void insertAppContext(Activity activity);
    //与Activity中方法保持一致，方便调用
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();

}
