package org.highway.whatsup.location;

/**
 * Created by engeng on 7/29/15.
 */
public interface LocationRetriever {
    void locationClientConnect();
    void locationClientDisconnect();
    void locationUpdateStart(boolean isService);
    void locationUpdateStop(boolean isService);
    void setLocationParams(int interval, int locationPriority);
}
