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
            if(i!=0)
                result+= calcSize(originalCopy);
            else
                calcSize(originalCopy);
            System.out.println((i+1)+"/5 of the calculation" );
        }
        averageSize = result/4;
    }

    private long calcSize(T originalCopy) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        array = new Object[size];
        for(int i = 0; i < size; i++)
        {
            T tmp = (T) BeanUtils.cloneBean(originalCopy);
            array[i] = tmp;
        }
        long start = runtime.freeMemory();
        array = null;
        System.gc();
        Thread.sleep(1000);
        long end = runtime.freeMemory();
        return (end-start)/size;
    }
}
