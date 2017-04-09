package ru.otus.hw2;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by rukbr on 09.04.2017.
 */
public class MeasuringStand<T> {
    private Runtime runtime;
    private Object[] array;
    private int size = 10*1024*1024;
    private long averageSize;

    public MeasuringStand(Runtime runtime, T originalCopy) throws InterruptedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.runtime = runtime;
        calcAverageSize(originalCopy);
    }

    public long getAverageSize() {
        return averageSize;
    }

    private void calcAverageSize(T originalCopy) throws InterruptedException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        int result = 0;
        for(int i=0; i<5; i++)
        {
            result+= calcSize(originalCopy);
            System.out.println("i= " + i);
        }
        averageSize = result/5;
    }

    private long calcSize(T originalCopy) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        array = new Object[size];
        for(int i = 0; i < size; i++)
        {
            T tmp = (T) BeanUtils.cloneBean(originalCopy);
            array[i] = tmp;
        }
        Thread.sleep(1000);
        long start = runtime.freeMemory();
        array = null;
        System.gc();
        Thread.sleep(10000);
        long end = runtime.freeMemory();
        return (end-start)/size;
    }
}
