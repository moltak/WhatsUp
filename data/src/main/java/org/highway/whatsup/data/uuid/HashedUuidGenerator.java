package org.highway.whatsup.data.uuid;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by engeng on 8/24/15.
 * todo
 */
public class HashedUuidGenerator {
    public static String gen(Context context) {
        if (context == null) throw new IllegalArgumentException("Context cannot be null.");

        // If the context is mock, it will return null when call the getApplicationInfo();
        if (context.getApplicationInfo() == null) {
            return "fakeUuid";
        }

        try {
            return Sha1Helper.hash(
                    String.format("%s-%s",
                            retrievePhoneNumber(context),
                            createUniqueIdUsingDeviceInformation(context)));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return createUniqueIdUsingDeviceInformation(context);
    }

    private static String retrievePhoneNumber(Context context) {
        return MobileInfoHelper.getPhoneNumber(context);
    }

    private static String createUniqueIdUsingDeviceInformation(Context context) {
        return MobileInfoHelper.createUniqueIdUsingDeviceInformation(context);
    }
}
