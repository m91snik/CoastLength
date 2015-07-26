package com.m91snik.coastlength;

import com.m91snik.coastlength.impl.CoastLengthCalculatorImpl;
import org.junit.Ignore;
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
@Ignore
public class CoastLengthCalculatorLoadTest {

    public static final int DIM = 1000;
    private static final int[][] MAP = new int[DIM][DIM];

    @Test
    public void coastLengthCalculatorTest() throws ExecutionException, InterruptedException, TimeoutException {

        // use executor to set time limits for service
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int k = 0; k < 100; k++) {

            for (int i = 0; i < DIM; i++) {
                MAP[i][0] = 0;
                MAP[0][i] = 0;
                MAP[i][DIM - 1] = 0;
                MAP[DIM - 1][i] = 0;
            }

            Random random = new Random();
            for (int i = 1; i < DIM - 1; i++) {
                for (int j = 1; j < DIM - 1; j++) {
                    MAP[i][j] = random.nextBoolean() ? 1 : 0;
                }
            }

            Future<Integer> submit = executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    CoastLengthCalculator coastLengthCalculator = new CoastLengthCalculatorImpl();
                    return coastLengthCalculator.findCoastAndCalculateLength(DIM, DIM, MAP);
                }
            });

            try {
                submit.get(500, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                //printMap(MAP);
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
