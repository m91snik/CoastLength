package com.m91snik.coastlength;

import com.m91snik.coastlength.impl.CoastLengthCalculatorImpl;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by m91snik on 18.07.15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] map = new int[n + 4][m + 4];

        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            for (int j = 0; j < m; j++) {
                int mapCellValue = str.charAt(j) - '0';
                if (mapCellValue != 0 && mapCellValue != 1) {
                    throw new IllegalArgumentException("Please enter map of 0 or 1 elements");
                }
                map[i + 2][j + 2] = mapCellValue;
            }
        }

        com.m91snik.coastlength.CoastLengthCalculator coastLengthCalculator = new CoastLengthCalculatorImpl();

        int coastLength = coastLengthCalculator.findCoastAndCalculateLength(n + 4, m + 4, map);

        System.out.println(coastLength);


    }


}
