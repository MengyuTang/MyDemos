package com.example.fpm0322.myfirstapp.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @desp 版本更新定时任务
 * @author MengYu
 */
public class AlarmReceiver extends BroadcastReceiver {

    private String TAG = "AlarmReceiver";

    private Context mContext;

    private Activity currentActivity;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.foconn.fnfactoring.fnconnnew.receiver.AlarmReceiver")){
            mContext = context;
            Log.d(TAG,"AlarmReceiver：");
        }
    }

}
