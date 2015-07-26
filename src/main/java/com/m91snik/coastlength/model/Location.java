/**
 * Created by m91snik on 25.07.15.
 */
package com.m91snik.coastlength.model;


/**
 * Contains values for different locations on map
 */
public enum Location {
    COAST_WATER(2), COAST_LAND(3), SEA(0), LAND(1);

    /**
     * Number representation of different locations
     */
    public final int value;

    Location(int value) {
        this.value = value;
    }
}
