package com.alvaro.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents the four sides of a box storing its latitude and longitude
 */
@Data
@RequiredArgsConstructor
@Slf4j
public class GeoLocationBox {
    private final Double left;
    private final Double top;
    private final Double right;
    private final Double bottom;

    /**
     * Check if the (lat, lng) is in the box, return True or False
     *
     * @param lat latitude
     * @param lng longitude
     * @return if the coordinate is in the box
     */
    public boolean isInBox(Double lat, Double lng) {
        boolean isBetweenHorizontal = lat <= this.right && lat >= this.left;
        boolean isBetweenVertical = lng <= this.top && lng >= this.bottom;
        boolean isInTheBox = isBetweenHorizontal && isBetweenVertical;
        log.debug(String.format("(%s %s) Is in the box? %s", lat, lng, isInTheBox));
        return isInTheBox;
    }

    @Override
    public String toString() {
        return "GeoLocationBox{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
