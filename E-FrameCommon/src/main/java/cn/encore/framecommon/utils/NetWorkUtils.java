/*
 * 文件名: NetWorkTools.java
 */
package cn.encore.framecommon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * @author Encore.liang
 * @date 2012-7-16
 */
public class NetWorkUtils {
	public static String TAG = "NetWorkTools";
	public static final int NETWORK_WIFI = 1;
	public static final int NETWORK_3G = 2;
	public static final int NETWORK_2G = 3;
	public static final int NETWORK_NULL = 4;
	
	/**
	 * 判断是否支持WIFI
	 * 
	 * @param context
	 * @return true表示不支持，false表示不支持
	 */
	public static boolean isNetworkWIFI(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	 * [wifi是否可用]<br/>
	 * 功能详细描述
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnable(Context context){
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return wifiManager != null && wifiManager.isWifiEnabled();
	}

	/**
	 * 判断当前是否有可用的网络,2G网络、3G网络、wifi可使用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context == null) {
			return false;
		}
		ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cwjManager == null){
			return false;
		}
		NetworkInfo networkInfo = cwjManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isAvailable();
		}
		return false;
	}

	/**
	 * 判断是否GPRS
	 * 
	 * @param context
	 * @return true表示是GPRS，否则不是GPRS
	 */
	public static boolean isNetworkGprs(Context context) {
		TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (telMgr == null) {
			return false;
		}
		/*
		 * telMgr.getNetworkCountryIso();//获取电信网络国别 telMgr.getPhoneType();//获得行动通信类型
		 * telMgr.getNetworkType();//获得网络类型
		 */
		//Log.i(TAG, "NetworkType" + telMgr.getNetworkType());
		int networkType = telMgr.getNetworkType();
		if (networkType == TelephonyManager.NETWORK_TYPE_GPRS
				|| networkType == TelephonyManager.NETWORK_TYPE_EDGE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否3G网络
	 * 
	 * @param context
	 * @return true表示是3G，否则不是3G
	 */
	public static boolean isNetwork3G(Context context) {
		TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (telMgr == null) {
			return false;
		}
		int networkYype = telMgr.getNetworkType();
		// NETWORK_TYPE_EDGE 是为GPRS到第三代移动通信的过渡性技术方案(GPRS俗称2.5G， EDGE俗称2.75G）
		if (networkYype != TelephonyManager.NETWORK_TYPE_GPRS && networkYype != TelephonyManager.NETWORK_TYPE_UNKNOWN
				&& networkYype != TelephonyManager.NETWORK_TYPE_EDGE) {
			return true;
		}
		return false;
	}
	
	

	/**
	 * 判断3G是否可用
	 * 
	 * @param context
	 * @return true 可用， false不可用
	 */
	public static boolean is3GConnected(Context context) {

		if (!isNetwork3G(context)) {
			return false;
		}

		ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(
				Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		}
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		int netType = info.getType();   
		int netSubtype = info.getSubtype(); 
		
	    if(netType != ConnectivityManager.TYPE_MOBILE){
	    	return false;
	    }
        switch(netSubtype){
        case TelephonyManager.NETWORK_TYPE_EVDO_0:  
            return true; // ~ 400-1000 kbps  
        case TelephonyManager.NETWORK_TYPE_EVDO_A:  
            return true; // ~ 600-1400 kbps  
        case TelephonyManager.NETWORK_TYPE_HSDPA:  
            return true; // ~ 2-14 Mbps  
        case TelephonyManager.NETWORK_TYPE_HSPA:  
            return true; // ~ 700-1700 kbps  
        case TelephonyManager.NETWORK_TYPE_HSUPA:  
            return true; // ~ 1-23 Mbps  
        case TelephonyManager.NETWORK_TYPE_UMTS:  
            return true; // ~ 400-7000 kbps  
        }  
        
        if (android.os.Build.VERSION.SDK_INT > 7) {
        	// NOT AVAILABLE YET IN API LEVEL 7  
        	switch(netSubtype){
            case TelephonyManager.NETWORK_TYPE_EHRPD:  
                return true; // ~ 1-2 Mbps  
            case TelephonyManager.NETWORK_TYPE_EVDO_B:  
                return true; // ~ 5 Mbps  
            case TelephonyManager.NETWORK_TYPE_HSPAP:  
                return true; // ~ 10-20 Mbps  
            case TelephonyManager.NETWORK_TYPE_LTE:  
                return true; // ~ 10+ Mbps  
        	}
        }
		return false;
	}

	/**
	 * 判断GPRS是否可用
	 * 
	 * @param context
	 * @return true 可用， false不可用
	 */
	public static boolean isGprsConnected(Context context) {

		if (!isNetworkGprs(context)) {
			return false;
		}

		ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(
				Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		}
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info == null) {
			 return false;
		}
		int netType = info.getType();   
		int netSubtype = info.getSubtype(); 
	    if(netType != ConnectivityManager.TYPE_MOBILE){
	    	return false;
	    }
    	switch(netSubtype){  
    	case TelephonyManager.NETWORK_TYPE_1xRTT:
           return true; // ~ 50-100 kbps
    	case TelephonyManager.NETWORK_TYPE_CDMA:
           return true; // ~ 14-64 kbps
    	case TelephonyManager.NETWORK_TYPE_EDGE:
           return true; // ~ 50-100 kbps
    	case TelephonyManager.NETWORK_TYPE_GPRS:
           return true; // ~ 100 kbps
    	} 
	    if (android.os.Build.VERSION.SDK_INT > 7 && 
	    		netSubtype == TelephonyManager.NETWORK_TYPE_IDEN) {
	    	return true;
	    }
		return false;
	}

	/**
	 * 判断Wifi是否可用
	 * 
	 * @param context
	 * @return true 可用， false不可用
	 */
	public static boolean isWifiConnected(Context context) {
		if (context == null) {
			return false;
		}
		ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(
				Context.CONNECTIVITY_SERVICE);
		
		if (connectivity == null) {
			return false;
		}
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		if (info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 判断网络连接的类型
	 * 
	 * @param context
	 * @return
	 */
	public static int getNetworkType(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(
				Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					NetworkInfo network = info[i];
					if (network.isConnected()) {
						final int type = network.getType();
						if (type == ConnectivityManager.TYPE_WIFI) {
							return NETWORK_WIFI;
						}else if(type == ConnectivityManager.TYPE_MOBILE){
							if(isNetworkGprs(context)){
								return NETWORK_2G;
							}else if(isNetwork3G(context)){
								return NETWORK_3G;
							}
						}
					}
				}
			}
		}

		return NETWORK_NULL;
	}
}
