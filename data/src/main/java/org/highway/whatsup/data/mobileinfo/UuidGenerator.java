package org.highway.whatsup.data.mobileinfo;

import android.content.Context;

/**
 * Created by engeng on 8/24/15.
 * todo
 */
public class UuidGenerator {
    public static String gen(Context context) {
        if (context == null) throw new IllegalArgumentException("Context cannot be null.");

        // If the context is mock, it will return null when call the getApplicationInfo();
        if (context.getApplicationInfo() == null) {
            return "fakeUuid";
        }

        // todo
        return "";
    }
}
