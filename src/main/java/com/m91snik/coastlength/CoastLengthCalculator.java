/**
 * Created by m91snik on 18.07.15.
 */
package com.m91snik.coastlength;


/**
 * Coast length calculator provides API to work with land maps and calculate it's coast
 */
public interface CoastLengthCalculator {

    /**
     * It finds put all lands on map, walk on their coast and mark it, and eventually returns all coasts length.
     * <p>
     * IMPORTANT: it will modify original map and set 2 value to all cells on the coast
     *
     * @param n   number of rows
     * @param m   number of columns
     * @param map land map should contains only 0 or 1 values. 1 - ground, 0 - water
     * @return all coasts length on land map
     */
    int findCoastAndCalculateLength(int n, int m, int[][] map);
}
