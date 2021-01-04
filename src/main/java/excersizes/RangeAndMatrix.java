package excersizes;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class RangeAndMatrix {

    public static void rangeMatrix() {

        /***
         * If you even have a fixed row/column grid - then this can be very useful
         **/
        int[][] matrix = new int[8][3];
        matrix[0][2] = 122112;
        System.out.println(matrix[0][2]);

        /**
         * Lottery numbers
         * want to know how many numbers between 0.1 and 0.2
         */
        System.out.println("Adding tons of numbers");
        NavigableSet<Double> lotteryNumbers = new TreeSet<>();
        for (int i = 0; i < 5000000; i++) {
            lotteryNumbers.add(
                    Math.random()
            );
        }
        System.out.println("Done");
        System.out.println("lotteryNumbers size: " + lotteryNumbers.size());

        System.out.println("Getting the range");
        Set<Double> matchingNumbers = lotteryNumbers.subSet(0.1D, 0.13D);
        System.out.println("matching lottery numbers: " + matchingNumbers.size());

    }

}
