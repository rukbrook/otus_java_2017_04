package ru.otus.hw2;


import com.rits.cloning.Cloner;

/**
 * Created by rukbr on 09.04.2017.
 */
public class MeasuringStand<T> {
    private Runtime runtime;
    private Object[] array;
    private T originalCopy;
    Cloner cloner;
    private int size = 10*1024*1024;
    private long averageSize;

    public MeasuringStand(Runtime runtime, T originalCopy) throws InterruptedException {
        this.runtime = runtime;
        this.originalCopy = originalCopy;
        this.cloner = new Cloner();
        calcAverageSize(originalCopy);
    }

    public long getAverageSize() {
        return averageSize;
    }

    private void calcAverageSize(T originalCopy) throws InterruptedException {
        int result = 0;
        for(int i=0; i<5; i++)
        {
            result+= calcSize(originalCopy);
        }
        averageSize = result/5;
    }

    private long calcSize(T originalCopy) throws InterruptedException {
        array = new Object[size];
        for(int i = 0; i < size; i++)
        {
            array[i] = cloner.deepClone(originalCopy);
        }
        Thread.sleep(10*1000);
        long start = runtime.freeMemory();
        array = null;
        System.gc();
        long end = runtime.freeMemory();

        return (end-start)/size;
    }
}
