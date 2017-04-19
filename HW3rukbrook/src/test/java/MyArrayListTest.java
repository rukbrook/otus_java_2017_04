import org.junit.Assert;
import org.junit.Test;
import ru.otus.hw3.MyArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rukbr on 17.04.2017.
 */
public class MyArrayListTest {
    @Test
    public void test() {
        MyArrayList<Integer> array = new MyArrayList<>();
        LinkedList<Integer> linkedArray = new LinkedList<>();

        array.add(1);
        linkedArray.add(10);
        linkedArray.add(30);

        array.addAll(linkedArray);
        linkedArray.add(15);
        array.addAll(1,linkedArray);
        Integer[] test = {1,10,30,15,10,30};
        Assert.assertEquals(true, Arrays.equals(array.toArray(), test));
        Collections.addAll(array, 5, 7, 9);
        Integer[] test2 = {1,10,30,15,10,30,5,7,9};
        Assert.assertEquals(true, Arrays.equals(array.toArray(),test2));
        Integer[] test3 = {1,5,7,9,10,10,15,30,30};
        Collections.sort(array);
        Assert.assertEquals(true, Arrays.equals(array.toArray(),test3));
        LinkedList<Integer> test4 = new LinkedList<>();
        for(int i=0; i<9; i++)
            test4.add(0);
        Collections.copy(test4, array);
        Assert.assertEquals(true, Arrays.equals(array.toArray(),test4.toArray()));
    }
}
