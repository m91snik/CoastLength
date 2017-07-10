/**
 * Created by m91snik on 18.07.15.
 */
package com.m91snik.coastlength.impl;

import com.m91snik.coastlength.CoastLengthCalculator;
import com.m91snik.coastlength.model.Direction;
import com.m91snik.coastlength.model.Location;

import java.util.List;


public class CoastLengthCalculatorImpl implements CoastLengthCalculator {


    /**
     * {@inheritDoc}
     */
    @Override
    public int findCoastAndCalculateLength(int n, int m, int[][] map) {
        int coastLength = 0;

        Location location;
        for (int i = 1; i < n-1; i++) {
            location = Location.SEA;
            for (int j = 1; j < m-1; j++) {
                //NOTE: we can go from water to coast water, than to coast ground and only than to ground
                // here we ignore lakes and do not update location value to SEA because we are on the land
                if (map[i][j] == Location.COAST_LAND.value) {
                    if (location == Location.COAST_WATER || location == Location.LAND) {
                        location = Location.COAST_LAND;
                    }
                } else if (map[i][j] == Location.COAST_WATER.value) {
                    if (location == Location.SEA || location == Location.COAST_LAND) {
                        location = Location.COAST_WATER;
                    }
                } else if (map[i][j] == Location.LAND.value) {
                    if (location == Location.SEA || location == Location.COAST_WATER) {
                        coastLength += calculateCoastLength(map, i, j);
                        location = Location.COAST_LAND;
//                        printMap(n, m, map);
                    } else if (location == Location.COAST_LAND) {
                        location = Location.LAND;
                    }
                } else {
                    if (location == Location.COAST_WATER) {
                        location = Location.SEA;
                    }
                }
            }
        }

        return coastLength;
    }

    private int calculateCoastLength(int[][] map, int startX, int startY) {
        int coastLength = 0;
        int x = startX, y = startY;
        Direction prev = null, next = null;
        boolean continueCoastWalking = true;
        nextMovement:
        do {
            if (next != null) {
                prev = next;
            }
            List<Direction> directions = MovementCalculator.getPossibleDirections(prev == null ? Direction.RIGHT : prev);

            for (Direction direction : directions) {
                int nextX = x + direction.xOffset;
                int nextY = y + direction.yOffset;
                if (map[nextX][nextY] == Location.LAND.value || map[nextX][nextY] == Location.COAST_LAND.value) {
                    next = direction;
                    if (prev != null) {
                        List<Direction> coastLine = MovementCalculator.findCoastLine(prev, next);
                        coastLength += coastLine.size();
                        map[x][y] = Location.COAST_LAND.value;
                        MovementCalculator.updateLocationByDirections(x, y, map, coastLine, Location.COAST_WATER);
//                        System.out.println(prev + " -> " + next + " " + coastLength);
                    }
                    if (x == startX && y == startY) {
                        if (map[nextX][nextY] == Location.COAST_LAND.value) {
                            continueCoastWalking = false;
                        }
                    }
                    x = nextX;
                    y = nextY;
                    continue nextMovement;
                }
            }
            break;
        } while (continueCoastWalking);

        //NOTE: this is 1 island case
        if (next == null) {
            map[x][y] = Location.COAST_LAND.value;
            MovementCalculator.updateLocationByDirections(x, y, map, MovementCalculator.ALL_DIRECTIONS, Location.COAST_WATER);
            return 4;
        }

        return coastLength;
    }


}
