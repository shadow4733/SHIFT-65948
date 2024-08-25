package com.project.stats;

import com.project.input.InputDataType;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistics {
    private int count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;
    private boolean isFullStatistics;
    private InputDataType dataType;
    private boolean isNumber;
    private static boolean isFirstPrint = true;

    private BigInteger integerSum = BigInteger.ZERO;
    private long integerMin = Long.MAX_VALUE;
    private long integerMax = Long.MIN_VALUE;
    private BigDecimal floatSum = BigDecimal.ZERO;
    private BigDecimal floatMin = BigDecimal.valueOf(Double.MAX_VALUE);
    private BigDecimal floatMax = BigDecimal.valueOf(Double.MIN_VALUE);

    public Statistics(boolean isFullStatistics, InputDataType dataType, boolean isNumber) {
        this.isFullStatistics = isFullStatistics;
        this.dataType = dataType;
        this.isNumber = isNumber;
    }

    public void incrementCount() {
        count++;
    }

    public void updateNumberStatistics(String numberStr, InputDataType dataType) {
        if (dataType == InputDataType.INTEGER) {
            BigInteger number = new BigInteger(numberStr);
            integerSum = integerSum.add(number);
            integerMin = Math.min(integerMin, number.longValue());
            integerMax = Math.max(integerMax, number.longValue());
        } else if (dataType == InputDataType.FLOAT) {
            BigDecimal number = new BigDecimal(numberStr);
            floatSum = floatSum.add(number);
            floatMin = floatMin.min(number);
            floatMax = floatMax.max(number);
        }
    }

    public void updateStringStatistics(String str) {
        minLength = Math.min(minLength, str.length());
        maxLength = Math.max(maxLength, str.length());
    }

    public void printStatistics() {
        if (isFirstPrint) {
            System.out.println("Number of elements written to the file: " + count + "\n");
            isFirstPrint = false;
        }
        if (isFullStatistics && count > 0) {
            if (isNumber) {
                if (this.dataType == InputDataType.INTEGER && !integerSum.equals(BigInteger.ZERO)) {
                    System.out.println("Statistics for " + dataType + ":");
                    System.out.println("Minimum integer: " + integerMin);
                    System.out.println("Maximum integer: " + integerMax);
                    System.out.println("Sum of integers: " + integerSum);
                    BigDecimal averageInteger = new BigDecimal(integerSum).divide(BigDecimal.valueOf(count), BigDecimal.ROUND_HALF_UP);
                    System.out.println("Average: " + averageInteger + "\n");
                } else if (this.dataType == InputDataType.FLOAT && !floatSum.equals(BigDecimal.ZERO)) {
                    System.out.println("Statistics for " + dataType + ":");
                    System.out.println("Minimum float: " + floatMin);
                    System.out.println("Maximum float: " + floatMax);
                    System.out.println("Sum of floats: " + floatSum);
                    BigDecimal averageFloat = floatSum.divide(BigDecimal.valueOf(count), BigDecimal.ROUND_HALF_UP);
                    System.out.println("Average: " + averageFloat + "\n");
                }
            } else {
                if (minLength != Integer.MAX_VALUE) {
                    System.out.println("Statistics for " + dataType + ":");
                    System.out.println("Minimum number of characters in a word: " + minLength);
                    System.out.println("Maximum number of characters in a word: " + maxLength + "\n");
                }
            }
        }
    }
}