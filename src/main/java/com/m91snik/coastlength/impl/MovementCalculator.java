/**
 * Created by m91snik on 20.07.15.
 */
package com.m91snik.coastlength.impl;

import com.m91snik.coastlength.model.Direction;
import com.m91snik.coastlength.model.Location;
import com.m91snik.coastlength.model.Movement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.m91snik.coastlength.model.Direction.*;




/**
 * Movement calculator contains methods and fields to help calculate one movement coast and it's length.
 * It uses all predefined possible movements based on direction.
 */
class MovementCalculator {

    private static final Map<Movement, List<Direction>> MOVEMENT_SCORE = new HashMap<>();

    static final List<Direction> ALL_DIRECTIONS = Arrays.asList(UP, RIGHT, DOWN, LEFT);

    static {
        //right
        MOVEMENT_SCORE.put(new Movement(RIGHT, UP_RIGHT), Arrays.asList(UP));
        MOVEMENT_SCORE.put(new Movement(RIGHT, RIGHT), Arrays.asList(UP));
        MOVEMENT_SCORE.put(new Movement(RIGHT, DOWN_RIGHT), Arrays.asList(UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(RIGHT, DOWN), Arrays.asList(UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(RIGHT, DOWN_LEFT), Arrays.asList(UP, RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(RIGHT, LEFT), Arrays.asList(UP, RIGHT, DOWN));

        //down-right
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, UP_RIGHT), Arrays.asList(UP));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, RIGHT), Arrays.asList(UP));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, DOWN_RIGHT), Arrays.asList(UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, DOWN), Arrays.asList(UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, DOWN_LEFT), Arrays.asList(UP, RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, LEFT), Arrays.asList(UP, RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN_RIGHT, UP_LEFT), ALL_DIRECTIONS);

        //down

        MOVEMENT_SCORE.put(new Movement(DOWN, DOWN_RIGHT), Arrays.asList(RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN, DOWN), Arrays.asList(RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN, DOWN_LEFT), Arrays.asList(RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN, LEFT), Arrays.asList(RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN, UP_LEFT), Arrays.asList(RIGHT, DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(DOWN, UP), Arrays.asList(RIGHT, DOWN, LEFT));

        //down-left
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, DOWN_RIGHT), Arrays.asList(RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, DOWN), Arrays.asList(RIGHT));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, DOWN_LEFT), Arrays.asList(RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, LEFT), Arrays.asList(RIGHT, DOWN));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, UP_LEFT), Arrays.asList(RIGHT, DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, UP), Arrays.asList(RIGHT, DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(DOWN_LEFT, UP_RIGHT), ALL_DIRECTIONS);

        //left
        MOVEMENT_SCORE.put(new Movement(LEFT, DOWN_LEFT), Arrays.asList(DOWN));
        MOVEMENT_SCORE.put(new Movement(LEFT, LEFT), Arrays.asList(DOWN));
        MOVEMENT_SCORE.put(new Movement(LEFT, UP_LEFT), Arrays.asList(DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(LEFT, UP), Arrays.asList(DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(LEFT, UP_RIGHT), Arrays.asList(DOWN, LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(LEFT, RIGHT), Arrays.asList(DOWN, LEFT, UP));

        //up-left
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, DOWN_LEFT), Arrays.asList(DOWN));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, LEFT), Arrays.asList(DOWN));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, UP_LEFT), Arrays.asList(DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, UP), Arrays.asList(DOWN, LEFT));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, UP_RIGHT), Arrays.asList(DOWN, LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, RIGHT), Arrays.asList(DOWN, LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP_LEFT, DOWN_RIGHT), ALL_DIRECTIONS);

        //up
        MOVEMENT_SCORE.put(new Movement(UP, UP_LEFT), Arrays.asList(LEFT));
        MOVEMENT_SCORE.put(new Movement(UP, UP), Arrays.asList(LEFT));
        MOVEMENT_SCORE.put(new Movement(UP, UP_RIGHT), Arrays.asList(LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP, RIGHT), Arrays.asList(LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP, DOWN_RIGHT), Arrays.asList(LEFT, UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(UP, DOWN), Arrays.asList(LEFT, UP, RIGHT));

        //up-right
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, UP_LEFT), Arrays.asList(LEFT));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, UP), Arrays.asList(LEFT));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, UP_RIGHT), Arrays.asList(LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, RIGHT), Arrays.asList(LEFT, UP));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, DOWN_RIGHT), Arrays.asList(LEFT, UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, DOWN), Arrays.asList(LEFT, UP, RIGHT));
        MOVEMENT_SCORE.put(new Movement(UP_RIGHT, DOWN_LEFT), ALL_DIRECTIONS);
    }

    private static final Map<Direction, List<Direction>> DIRECTIONS = new HashMap<>();

    static {
        DIRECTIONS.put(RIGHT, Arrays.asList(UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT));
        DIRECTIONS.put(DOWN_RIGHT, Arrays.asList(UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT));
        DIRECTIONS.put(DOWN, Arrays.asList(DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, UP));
        DIRECTIONS.put(DOWN_LEFT, Arrays.asList(DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, UP, UP_RIGHT));
        DIRECTIONS.put(LEFT, Arrays.asList(DOWN_LEFT, LEFT, UP_LEFT, UP, UP_RIGHT, RIGHT));
        DIRECTIONS.put(UP_LEFT, Arrays.asList(DOWN_LEFT, LEFT, UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT));
        DIRECTIONS.put(UP, Arrays.asList(UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN));
        DIRECTIONS.put(UP_RIGHT, Arrays.asList(UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT));
    }

    static List<Direction> findCoastLine(Direction from, Direction to) {
        return MOVEMENT_SCORE.get(new Movement(from, to));
    }

    static List<Direction> getPossibleDirections(Direction direction) {
        return DIRECTIONS.get(direction);
    }

    static void updateLocationByDirections(int x, int y, int[][] map, List<Direction> directions, Location location) {
        for (Direction direction : directions) {
            map[x + direction.xOffset][y + direction.yOffset] = location.value;
        }
    }
}
