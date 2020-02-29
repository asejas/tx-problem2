package com.alvaro.businesslogic.location;

import com.alvaro.domain.GeoLocationBox;
import lombok.extern.slf4j.Slf4j;

/**
 * This class implements methods to deal with the geo-location operations
 */
@Slf4j
public class GeoLocationCalculator implements LocationCalculator{
    private static final double EARTH_RADIO_KM = 6378.137;
    private static final double CONVERSION_RATE_FOR_1_M = (1d / ((2d * Math.PI / 360d) * EARTH_RADIO_KM)) / 1000d;

    /**
     * Add a distance to a latitude
     *
     * @param latitude       The initial latitude
     * @param distanceMeters The distance to add (in meters)
     * @return The final latitude
     */
    public double addMetersToLatitude(double latitude, double distanceMeters) {
        return latitude + (CONVERSION_RATE_FOR_1_M * distanceMeters);
    }

    /**
     * Add a distance to a longitude
     *
     * @param longitude      The initial longitude
     * @param latitude       The latitude
     * @param distanceMeters The distance to add (in meters)
     * @return The final longitude
     */
    public double addMetersToLongitude(double longitude, double latitude, double distanceMeters) {
        return longitude + (CONVERSION_RATE_FOR_1_M * distanceMeters) / Math.cos(latitude * (Math.PI / 180));
    }

    /**
     * Calculate the box sides for a geo-location (longitude, latitude) adding a distance (distance_meters).
     *
     * @param longitude      Longitude (for the center of the box)
     * @param latitude       Latitude (for the center of the box)
     * @param distanceMeters Box side/2 (in meters)
     * @return GeoLocationBox instance
     */
    public GeoLocationBox getGeoLocationBox(double longitude, double latitude, double distanceMeters) {
        GeoLocationBox geoLocationBox = new GeoLocationBox(
                addMetersToLatitude(latitude, (distanceMeters * (-1))),
                addMetersToLongitude(longitude, latitude, distanceMeters),
                addMetersToLatitude(latitude, distanceMeters),
                addMetersToLongitude(longitude, latitude, (distanceMeters * (-1))));
        log.debug(geoLocationBox.toString());
        return geoLocationBox;
    }
}
