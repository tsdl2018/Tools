package com.gh.utils.tools.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

/**
 * @author: gh
 * @description: 一键退出工具类    推荐在BaseActivity中调用
 * @date: 2016/10/17 10:16.
 */
public class ACTManager {

    private static final String TAG = ACTManager.class.getSimpleName();

    private static Stack<Activity> activityStack;
    private static ACTManager instance;

    private ACTManager() {
    }

    /**
     * 单一实例
     */
    public static ACTManager getActivitypManager() {
        if (instance == null) {
            synchronized (ACTManager.class) {
                if (instance == null) {
                    instance = new ACTManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
        Log.w("AppManager", "AppManager--->>" + activity.toString() + "...size==" + activityStack.size());
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            clearData();
            finishAllActivity();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 清除数据
     */
    public void clearData() {
    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }
}
