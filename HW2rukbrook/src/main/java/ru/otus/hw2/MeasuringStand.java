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

    @Override
    public String toString() {
        return "averageSize of 1 object = " + averageSize;
    }

    public MeasuringStand(Runtime runtime, T originalCopy) throws InterruptedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.runtime = runtime;
        calcAverageSize(originalCopy);
    }

    private void calcAverageSize(T originalCopy) throws InterruptedException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("start of the calculation");
        int result = 0;
        for(int i=0; i<5; i++)
        {
            result+= calcSize(originalCopy);
            System.out.println((i+1)+"/5 of the calculation" );
        }
        averageSize = result/5;
    }

    private long calcSize(T originalCopy) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        array = new Object[size];
        for(int i = 0; i < size; i++)
        {
            array[i] = (T) BeanUtils.cloneBean(originalCopy);
        }
        System.gc();
        long start = runtime.freeMemory();
        for(int i = 0; i < size; i++)
        {
            array[i] = null;
        }
        System.gc();
        long end = runtime.freeMemory();
        return (end-start)/size;
    }
}
