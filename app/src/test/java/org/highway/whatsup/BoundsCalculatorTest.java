package org.highway.whatsup;

import org.highway.whatsup.data.physics.DefaultBoundsCalculator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class BoundsCalculatorTest {
    final double lat = 36.338123f, lng = 127.393435f;

    @Test
    public void testBoundary() {
        final double ONE_KILLOMETER_PER_LAT = 0.009043717329571148f;

        double lat1Degree = 110.574;
        double lng1Degree = 111.320 * Math.cos(lat);
        System.out.println(1/lat1Degree + " : " + 1/lng1Degree); // 0.011248593925759
        // 위도 37에서 경도 1도는 88.9km
        // 1km = 0.011248593925759
        System.out.println(haversine(
                lat + 5 * (1/lat1Degree), lng + 5 * 0.011248593925759,
                lat - 5 * (1/lat1Degree), lng - 5 * 0.011248593925759
        ));
    }

    @Test
    public void testMakeBoundary() {
        DefaultBoundsCalculator defaultBoundsCalculator = new DefaultBoundsCalculator();
        DefaultBoundsCalculator.Bounds bounds = defaultBoundsCalculator.getBounds(lat, lng);
        assertThat((int)haversine(
                bounds.getLat1(), bounds.getLng1(),
                bounds.getLat2(), bounds.getLng2()),
                is(14));
    }

    public static double haversine(
            double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;
        return d;
    }
}
