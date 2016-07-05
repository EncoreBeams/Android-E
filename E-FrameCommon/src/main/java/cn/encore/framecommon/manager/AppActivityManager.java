package cn.encore.framecommon.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;


public class AppActivityManager {

	private static Stack<Activity> activityStack;
	private static AppActivityManager instance;

	private AppActivityManager() {
	}

	public static AppActivityManager getAppManager() {
		if (instance == null) {
			instance = new AppActivityManager();
		}
		return instance;
	}
	//add activity
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	//current activity
	public Activity currentActivity() {
		Activity activity = (Activity) activityStack.lastElement();
		return activity;
	}
	//finish last stack activity
	public void finishActivity() {
		Activity activity = (Activity) activityStack.lastElement();
		if(activity != null) {
			removeActivity(activity);
			activity.finish();
		}
	}

	//finsh activity
	public void removeActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
//			activity.finish();
		}
	}

	public void finishActivity(Class cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				removeActivity(activity);
				if(activity != null) activity.finish();
			}
		}
	}
	//finish all activity
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	public void reLongin(Class<?> cls) {
		int i = 0;
		for (int size = activityStack.size(); i < size; i++) {
			if ((activityStack.get(i) != null) && (!((Activity) activityStack.get(i)).getClass().equals(cls))) {
				((Activity) activityStack.get(i)).finish();
			}
		}
	}

	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}