package com.m91snik.coastlength;

import com.m91snik.coastlength.impl.CoastLengthCalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by m91snik on 19.07.15.
 */
public class CoastLengthCalculatorTest {

    private com.m91snik.coastlength.CoastLengthCalculator coastLengthCalculator = new CoastLengthCalculatorImpl();

    @Test
    public void testOnlyWater() {
        int[][] map = new int[][]{{0, 0,}, {0, 0}};
        Assert.assertEquals(0, coastLengthCalculator.findCoastAndCalculateLength(2, 2, map));
    }

    @Test
    public void testIsland() {
        int[][] map = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assert.assertEquals(4, coastLengthCalculator.findCoastAndCalculateLength(3, 3, map));
    }

    @Test
    public void testSquare() {
        int[][] map = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        Assert.assertEquals(8, coastLengthCalculator.findCoastAndCalculateLength(4, 4, map));
    }

    @Test
    public void testRectangle() {
        int[][] map = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        Assert.assertEquals(10, coastLengthCalculator.findCoastAndCalculateLength(5, 4, map));
    }

    @Test
    public void testPoly1() {
        int[][] map = new int[][]{{0, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        Assert.assertEquals(8, coastLengthCalculator.findCoastAndCalculateLength(4, 4, map));
    }

    @Test
    public void testPoly2() {
        int[][] map = new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        Assert.assertEquals(8, coastLengthCalculator.findCoastAndCalculateLength(4, 4, map));
    }

    @Test
    public void testPoly3() {
        int[][] map = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};
        Assert.assertEquals(12, coastLengthCalculator.findCoastAndCalculateLength(5, 5, map));
    }

    @Test
    public void testPolyWithBlanks() {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        Assert.assertEquals(16, coastLengthCalculator.findCoastAndCalculateLength(5, 7, map));
    }

    @Test
    public void testComplexPolyWithLakes() {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//0
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},//1
                {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},//2
                {0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},//3
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},//4
                {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},//5
                {0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0},//6
                {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0},//7
                {0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0},//8
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},//9
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},//10
                {0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},//11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//12
        };
        Assert.assertEquals(66, coastLengthCalculator.findCoastAndCalculateLength(13, 13, map));
    }

    @Test
    public void testComplexPolyWithBlanks() {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//0
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},//1
                {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0},//2
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},//3
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},//4
                {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},//5
                {0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0},//6
                {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0},//7
                {0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0},//8
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},//9
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0},//10
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0},//11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//12
        };
        Assert.assertEquals(78, coastLengthCalculator.findCoastAndCalculateLength(13, 13, map));
    }

    @Test
    public void testComplexPolyWithBlanks2() {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },//0
                {0, 1, 1, 1, 0, 1, 1, 0, 1, 0, },//1
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, },//2
                {0, 0, 1, 0, 0, 1, 1, 0, 0, 0, },//3
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 0, },//4
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, },//5
                {0, 0, 1, 1, 0, 1, 1, 1, 1, 0, },//6
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0, },//7
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },//8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },//9
        };
        Assert.assertEquals(66, coastLengthCalculator.findCoastAndCalculateLength(10, 10, map));
    }

    @Test
    public void testTaskExample() {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        Assert.assertEquals(20, coastLengthCalculator.findCoastAndCalculateLength(7, 8, map));
    }
}
