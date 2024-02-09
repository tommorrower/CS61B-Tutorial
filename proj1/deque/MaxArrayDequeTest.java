package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
public class MaxArrayDequeTest
{
    private static class intComparator implements Comparator<Integer>
    {
        public int compare(Integer x,Integer y)
        {
            return x-y;
        }
    }

    private static class stringComparator implements Comparator<String>
    {
        public int compare(String x, String y)
        {
            return x.compareTo(y);
        }
    }

    private static class newStringComparator implements Comparator<String>
    {
        public int compare(String x, String y)
        {
            return x.length()-y.length();
        }
    }

    @Test
    public void TestMax()
    {
        MaxArrayDeque<Integer>test = new MaxArrayDeque<>(new intComparator());
        for(int i=0;i<10;i++)
        {
            test.addFirst(i);
        }
        assertEquals("Should be 9",9,test.max().intValue());
    }
    @Test
    public void TestMaxWithComparator()
    {
        MaxArrayDeque<String> test = new MaxArrayDeque<>(new stringComparator());
        test.addFirst("I");
        test.addFirst("Love");
        test.addFirst("You");
        assertEquals("Should be Love","Love",test.max(new newStringComparator()));
    }
}
