package org.highway.whatsup.data.physics;

/**
 * Created by engeng on 8/22/15.
 * 위도 37에서 경도 1도는 88.9km
 * 1km = 0.011248593925759
 *
 * 경도 1도는 110.574
 * 1도 = 1/110.574 =
 */
public class DefaultBoundsCalculator implements BoundsCalculator {
    private final double lat1Degree = 0.009043717329571148f;
    private final double lng1Degree = 0.011248593925759;

    @Override
    public BoundsCalculator.Bounds getBounds(double lat, double lng) {
        return new BoundsCalculator.Bounds(
                lat + 5 * lat1Degree, lng + 5 * lng1Degree,
                lat - 5 * lat1Degree, lng - 5 * lng1Degree
        );
    }
}
