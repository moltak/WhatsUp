package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.rest.whatsup.WhatsUpApiProvider;
import org.highway.whatsup.domain.data.ExpressData;

import javax.inject.Inject;

/**
 * Created by engeng on 8/23/15.
 */
public class WhatsUpActionCreator {
    final WhatsUpApiProvider whatsUpApiProvider;
    final DefaultActionCreator defaultActionCreator;

    @Inject public WhatsUpActionCreator(WhatsUpApiProvider whatsUpApiProvider,
                                        DefaultActionCreator defaultActionCreator) {
        this.whatsUpApiProvider = whatsUpApiProvider;
        this.defaultActionCreator = defaultActionCreator;
    }

    public DefaultActionCreator getDefaultActionCreator() {
        return defaultActionCreator;
    }

    public WhatsUpApiProvider getWhatsUpApiProvider() {
        return whatsUpApiProvider;
    }

    public ExpressData retrieveWhatsUpData(float speed, double lat, double lng) {
        return new ExpressData(speed, lat, lng, defaultActionCreator.getProgression(speed), null, null);
    }

    public ExpressData doit(float speed, double lat, double lng) {
        ExpressData data = retrieveWhatsUpData(speed, lat, lng);

        return defaultActionCreator.doit(speed, lat, lng);
    }

    private void sendHighSpeedProgressionData() {

    }
}
