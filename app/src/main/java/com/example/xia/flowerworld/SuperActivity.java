package com.example.xia.flowerworld;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

public class SuperActivity extends Activity {
    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void exitClient(Context ctx)
    {
        // 关闭所有Activity
        for (int i = 0; i < activityList.size(); i++)
        {
            if (null != activityList.get(i))
            {
                activityList.get(i).finish();
            }
        }
        ActivityManager activityMgr = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE );
        activityMgr.restartPackage(ctx.getPackageName());
        System.exit(0);
    }
}
