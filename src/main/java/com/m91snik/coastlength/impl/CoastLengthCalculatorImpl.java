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

        Location location = Location.SEA;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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

    //can be used for logging
//    private static void printMap(int n, int m, int[][] map) {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("--------------------------");
//    }

    private int calculateCoastLength(int[][] map, int i, int j) {
        int coastLength = 0;
        int x = i, y = j;
        Direction firstDir = null;
        Direction prev = null, next = null;
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
                    if (next == null) {
                        firstDir = direction;
                    }
                    next = direction;

                    if (prev != null) {
                        List<Direction> coastLine = MovementCalculator.findCoastLine(prev, next);
                        coastLength += coastLine.size();
                        map[x][y] = Location.COAST_LAND.value;
                        MovementCalculator.updateLocationByDirections(x, y, map, coastLine, Location.COAST_WATER);
//                        System.out.println(prev + " -> " + next + " " + coastLength);
                    }

                    x = nextX;
                    y = nextY;
                    continue nextMovement;
                }
            }
        } while (x != i || y != j);

        //NOTE: this is 1 island case
        if (next == null) {
            map[x][y] = Location.COAST_LAND.value;
            MovementCalculator.updateLocationByDirections(x, y, map, MovementCalculator.ALL_DIRECTIONS, Location.COAST_WATER);
            return 4;
        }

        List<Direction> coastLine = MovementCalculator.findCoastLine(next, firstDir);
        map[x][y] = Location.COAST_LAND.value;
        MovementCalculator.updateLocationByDirections(x, y, map, coastLine, Location.COAST_WATER);
        coastLength += coastLine.size();
        return coastLength;
    }


}