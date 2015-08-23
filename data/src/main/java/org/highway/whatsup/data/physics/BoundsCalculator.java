package org.highway.whatsup.data.physics;

/**
 * Created by engeng on 8/22/15.
 */
public interface BoundsCalculator {
    BoundsCalculator.Bounds getBounds(double lat, double lng);

    public class Bounds {
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
