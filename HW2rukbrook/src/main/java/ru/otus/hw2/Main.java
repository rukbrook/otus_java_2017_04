package ru.otus.hw2;

import java.lang.management.ManagementFactory;

/**
 * Created by rukbr on 08.04.2017.
 */
//VM options -Xmx512m -Xms512m
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting pod: " + ManagementFactory.getRuntimeMXBean().getName());
        Runtime runtime = Runtime.getRuntime();
        MeasuringStand<String> test = new MeasuringStand<>(runtime, new String(""));
        System.out.println(test.getAverageSize());
    }
}
