package ru.otus.hw3;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by rukbr on 15.04.2017.
 */
public class MyArrayList<T> implements List<T>{
    private Object[] array;
    private  int nElems;

    public MyArrayList(int max) {
        array = new Object[max];
        nElems = 0;
    }

    public MyArrayList() {
        array = new Object[10];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public boolean isEmpty() {
        return nElems==0 ? true:false;
    }

    public boolean contains(Object o) {
        for(int i = 0; i<nElems; i++)
        {
            if(array[i].equals(o))
                return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < nElems && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return (T)array[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array,nElems);
    }

    public <T1> T1[] toArray(T1[] a) {
        if (nElems <= a.length){
            System.arraycopy(array, 0, a, 0, nElems);
            return a;
        } else {
            return (T1[])Arrays.copyOf(array, nElems, a.getClass());
        }
    }

    public boolean add(T t) {
        if(array.length <= nElems) {
            Object[] newArray = new Object[2*nElems];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[nElems] = t;
        nElems++;
        return true;
    }

    public boolean remove(Object o) {
        int delete = indexOf(o);
        if(delete!=-1)
        {
            for(; delete <nElems-1; delete++)
            {
                array[delete] = array[delete+1];
            }
            nElems--;
            return true;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for(Iterator iterator = c.iterator(); iterator.hasNext();)
        {
            if(!contains(iterator.next()))
                return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        try {
            for (Iterator iterator = c.iterator(); iterator.hasNext(); ) {
                add((T) iterator.next());
            }
        } catch (ClassCastException e) {
            return false;
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if(index<nElems)
        {
            Object[] result = new Object[nElems+c.size()];
            System.arraycopy(array, 0, result, 0, index);
            System.arraycopy(c.toArray(), 0, result, index, c.size());
            System.arraycopy(array, index, result, index+nElems, nElems-index);
            array = result;
            nElems = nElems+c.size();
            return true;
        }
        else
        {
            return addAll(c);
        }
    }

    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (Iterator iterator = c.iterator(); iterator.hasNext(); ) {
            boolean tmpRemove = remove(iterator.next());
            if(tmpRemove)
            {
                isRemoved=tmpRemove;
            }
        }
        return isRemoved;
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        nElems = 0;
        array = new Object[10];
    }

    public T get(int index) {
        return (T)array[index];
    }

    public T set(int index, T element) {
        T result = (T)array[index];
        array[index] = element;
        return result;
    }

    public void add(int index, T element) {
        if(index<nElems)
        {
            Object[] result = new Object[nElems+1];
            System.arraycopy(array, 0, result, 0, index);
            result[index] = element;
            System.arraycopy(array, index, result, index+1, nElems-index);
            array = result;
            nElems = nElems+1;
        }
        else
        {
            add(element);
        }
    }

    public T remove(int index) {
        if(index < nElems)
        {
            T result = (T)array[index];
            for(;index<nElems-1; index++)
            {
                array[index] = array[index+1];
            }
            nElems--;
            return result;
        }
        return null;
    }

    public int indexOf(Object o) {
        for(int i = 0; i<nElems; i++)
        {
            if(array[i].equals(o))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int result = -1;
        for(int i = 0; i<nElems; i++)
        {
            if(array[i].equals(o))
                result=i;
        }
        return result;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) this.array, 0, nElems, c);
    }
}
