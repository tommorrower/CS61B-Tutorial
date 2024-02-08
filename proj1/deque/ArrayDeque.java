package deque;
import java.lang.reflect.Array;
public class ArrayDeque<T>
{
    public T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int maxsize;
    public ArrayDeque()
    {
        array = (T[]) new Object[8];
        nextFirst=0;
        nextLast=0;
        size=0;
        maxsize=8;
    }
    public boolean isFull()
    {
        return size == maxsize;
    }

    public boolean isUsed()
    {
        double usage = (double)size/(double)maxsize;
        return maxsize < 16 || usage > 0.25;
    }
    public void resizeSmaller()
    {
        int start = (nextFirst+1) % maxsize;
        double usage=0.25;//which is bigger than 0.25
        int new_maxsize = maxsize;
        while(new_maxsize==maxsize)
        {
            new_maxsize = (int) (size/usage);
            usage+=0.1;
        }
        maxsize = new_maxsize;
        T []newArray = (T[])new Object [maxsize];
        System.arraycopy(array,start,newArray,0,size);
        array = newArray;
        nextFirst = maxsize-1;
        nextLast = size;
    }

    public void resizeBigger()
    {
        int start = (nextFirst+1) % maxsize;
        double factor = 1.2;
        maxsize = (int)(maxsize*factor);
        T[] newArray = (T[])new Object[maxsize];
        System.arraycopy(array,start,newArray,0,size);
        array=newArray;
        nextFirst = maxsize-1;
        nextLast = size;

    }
    public void addFirst(T data)
    {
        if(isEmpty())
            nextLast++;
        if(isFull())
        {
            resizeBigger();
        }
        size++;
        array[nextFirst]=data;
        if(nextFirst==0)
        {
            nextFirst=maxsize-1;
        }
        else
        {
            nextFirst--;
        }
    }

    public void addLast(T data)
    {
        if(isEmpty())
        {
            addFirst(data);
            return;
        }
        if(isFull())
        {
            resizeBigger();
        }
        size++;
        array[nextLast]=data;
        if(nextLast==maxsize-1)
        {
            nextLast=0;
        }
        else
        {
            nextLast++;
        }

    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        for(int cnt = 0,index = (nextFirst+1)%maxsize;cnt<size;cnt++,index++)
        {
            if(index>=array.length)
            {
                index%=array.length;
            }
            System.out.print(array[index]+" ");
            System.out.println();
        }
    }

    public T removeFirst()
    {
        if(isEmpty())
            return null;
        T ans = array[(nextFirst+1)%maxsize];
        array[(nextFirst+1)%maxsize] = null;
        nextFirst=(nextFirst+1)%maxsize;
        if(size==1)
            nextLast--;
        size--;
        return ans;
    }

    public T removeLast()
    {
        if(isEmpty())
            return null;
        T ans;
        if(nextLast==0)
        {
            ans = array[maxsize-1];
            array[maxsize-1] = null;
            nextLast = maxsize-1;
        }
        else
        {
            ans = array[nextLast-1];
            array[nextLast-1] = null;
            nextLast--;
        }
        if(size==1)
            nextFirst = 0;
        size--;
        return ans;

    }

    public T get(int index)
    {
        if(index>=nextFirst+1&&index<=nextLast-1)
        {
            return array[index];
        }
        else return null;
    }

}