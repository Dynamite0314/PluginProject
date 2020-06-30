package com.example.standard;

import android.content.Context;
import android.content.Intent;

/**********************************************************
 *
 * author: dongp
 * date: 2020/6/30
 * description: xxxx
 *
 *********************************************************/
public interface BroadcastInterFace {

    void onReceive(Context context, Intent intent);
}
