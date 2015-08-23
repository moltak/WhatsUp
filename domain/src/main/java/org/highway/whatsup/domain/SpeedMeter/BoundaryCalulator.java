package org.highway.whatsup.domain.SpeedMeter;

/**
 * Created by engeng on 8/22/15.
 * 위도 37에서 경도 1도는 88.9km
 * 1km = 0.011248593925759
 *
 * 경도 1도는 110.574
 * 1도 = 1/110.574 =
 */
public class BoundaryCalulator {
    private final double lat1Degree = 0.009043717329571148f;
    private final double lng1Degree = 0.011248593925759;

    public BoundaryCalulator.Bounds getBoundary(double lat, double lng) {
        return new Bounds(
                lat + 5 * lat1Degree, lng + 5 * lng1Degree,
                lat - 5 * lat1Degree, lng - 5 * lng1Degree
        );
    }

    public static class Bounds {
        private final double lat1, lng1;
        private final double lat2, lng2;

        public Bounds(double lat1, double lng1, double lat2, double lng2) {
            this.lat1 = lat1;
            this.lng1 = lng1;
            this.lat2 = lat2;
            this.lng2 = lng2;
        }

        public double getLat1() {
            return lat1;
        }

        public double getLng1() {
            return lng1;
        }

        public double getLat2() {
            return lat2;
        }

        public double getLng2() {
            return lng2;
        }
    }
}
