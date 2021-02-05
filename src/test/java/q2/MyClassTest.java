package q2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyClassTest {

    private Date dt;
    String name;
    List<Long> nums;
    List<String> strs;

    @Before
    public void setup() {
        dt = new Date();
        name = "test";
        nums = new ArrayList<>();
        strs = new ArrayList<>();
    }

    @Test
    public void testRemoveString() {
        strs.add("a");
        strs.add("b");
        strs.add("b");
        MyClass c = new MyClass(dt, name, nums, strs);
        System.out.println(c.getStrings()); // [a, b, b]
        c.removeString("b");
        System.out.println(c.getStrings()); // => [a, b]
        // [a, b, b]
        //     i     i=1 removes idx 1 but then b in idx 1 and loop ends [a, b]
        // => use iterator
    }

    @Test
    public void testIsHistoric() {
        MyClass c = new MyClass(dt, name, nums, strs);
        assertTrue(c.isHistoric());
    }
}
