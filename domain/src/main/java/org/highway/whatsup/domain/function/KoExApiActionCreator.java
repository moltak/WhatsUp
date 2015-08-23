package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiActionCreator {

    private final BoundsCalculator boundsCalculator;

    public KoExApiActionCreator(BoundsCalculator boundsCalculator) {
        this.boundsCalculator = boundsCalculator;
    }
}
