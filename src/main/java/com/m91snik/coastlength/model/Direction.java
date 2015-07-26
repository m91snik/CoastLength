package com.m91snik.coastlength.model;

/**
 * Created by m91snik on 25.07.15.
 */
public enum Direction {
    UP_LEFT(-1, -1), UP(-1, 0), UP_RIGHT(-1, 1), RIGHT(0, 1), DOWN_RIGHT(1, 1),
    DOWN(1, 0), DOWN_LEFT(1, -1), LEFT(0, -1);

    public int xOffset, yOffset;

    Direction(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
