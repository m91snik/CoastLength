package com.m91snik.coastlength;

import com.m91snik.coastlength.impl.CoastLengthCalculatorImpl;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by m91snik on 25.07.15.
 */

/**
 * Simple test to check algorithm correctness on random data, i.e. that no OOM will be, no infinite loops and so on.
 * By default it's ignored, because there is hardcoded time limit on each execution of CoastLength calculator,
 * but it makes sense to run it in case of any significant changes and set required parameters here
 */
public class CoastLengthCalculatorLoadTest {


    @Test
    public void coastLengthCalculatorTest() throws ExecutionException, InterruptedException, TimeoutException {

        // use executor to set time limits for service
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int k = 0; k < 1000; k++) {

            final int n = RandomUtils.nextInt(100);
            final int m = RandomUtils.nextInt(100);
            final int[][] MAP = new int[n][m];
//            for (int i = 0; i < DIM; i++) {
//                MAP[i][0] = 0;
//                MAP[0][i] = 0;
//                MAP[i][DIM - 1] = 0;
//                MAP[DIM - 1][i] = 0;
//            }

            Random random = new Random();
            for (int i = 2; i < n - 2; i++) {
                for (int j = 2; j < m - 2; j++) {
                    MAP[i][j] = random.nextBoolean() ? 1 : 0;
                }
            }

            Future<Integer> submit = executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    CoastLengthCalculator coastLengthCalculator = new CoastLengthCalculatorImpl();
                    return coastLengthCalculator.findCoastAndCalculateLength(n, m, MAP);
                }
            });

            try {
                System.out.println(submit.get(50, TimeUnit.MILLISECONDS));
            } catch (TimeoutException e) {
                printMap(MAP);
                throw e;
            } catch (Exception e) {
                printMap(MAP);
                throw e;
            }
        }

    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
