package ru.otus.hw4;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rukbr on 26.04.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        measureGC.collect();
        memoryLeak();
    }

    private static void memoryLeak() throws InterruptedException {
        List<Object> list;
        int j = 1;
        while (true) {
            Thread.sleep(300);
            list = new ArrayList<>();

            for (long i = 0L; i < 100000000*j ; i++)
                list.add(new Object());
            j++;
        }
    }
}
