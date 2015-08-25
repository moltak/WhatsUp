package org.highway.whatsup.data.uuid;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by engeng on 8/25/15.
 */
public class MobileInfoHelper {
    public static String getPhoneNumber(Context context) throws NullPointerException {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        try {
            return mTelephonyMgr.getLine1Number().replace("+82", "0").replace("-", "");
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String createUniqueIdUsingDeviceInformation(Context context) {
        try {
            return getDeviceName() + "_" + getAndroidId(context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("There is no an unique id for device.");
    }

    private static String getDeviceName() throws UnsupportedEncodingException {
        return URLEncoder.encode(Build.MODEL, "utf-8");
    }

    private static String getAndroidId(Context context) throws UnsupportedEncodingException {
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return URLEncoder.encode(android_id, "utf-8");
    }
}
