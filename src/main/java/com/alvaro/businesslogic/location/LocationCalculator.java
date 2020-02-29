package com.alvaro.businesslogic.location;

import com.alvaro.domain.GeoLocationBox;

/**
 * This interface defines methods to deal with Longitude, Latitude and the GeoLocationBox
 */
public interface LocationCalculator {
    /**
     * Add a distance to a latitude
     *
     * @param latitude       The initial latitude
     * @param distanceMeters The distance to add (in meters)
     * @return The final latitude
     */
    double addMetersToLatitude(double latitude, double distanceMeters);

    /**
     * Add a distance to a longitude
     *
     * @param longitude      The initial longitude
     * @param latitude       The latitude
     * @param distanceMeters The distance to add (in meters)
     * @return The final longitude
     */
    double addMetersToLongitude(double longitude, double latitude, double distanceMeters);

    /**
     * Calculate the box sides for a geo-location (longitude, latitude) adding a distance (distance_meters).
     *
     * @param longitude      Longitude (for the center of the box)
     * @param latitude       Latitude (for the center of the box)
     * @param distanceMeters Box side/2 (in meters)
     * @return GeoLocationBox instance
     */
    GeoLocationBox getGeoLocationBox(double longitude, double latitude, double distanceMeters);
}
