package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int maxsize;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
        maxsize = 8;
    }
    public boolean isFull() {
        return size == maxsize;
    }

    public boolean isUsed() {
        double usage = (double) size / (double) maxsize;
        return maxsize < 16 || usage > 0.25;
    }

    public void circularCopy(T[]old, int start, T[]fresh, int length) {
        for (int cnt = 0; cnt < size; cnt++) {
            if (start == size) {
                start = 0;
            }
            fresh[cnt] = old[start];
            start++;
        }
    }
    public void resizeSmaller() {
        int start = (nextFirst+1) % maxsize;
        double usage = 0.25;//which is bigger than 0.25
        int new_maxsize = maxsize;
        while (new_maxsize == maxsize) {
            new_maxsize = (int) (size/usage);
            usage+=0.1;
        }
        maxsize = new_maxsize;
        T[] newArray = (T[]) new Object [maxsize];
        circularCopy(array,start,newArray,size);
        array = newArray;
        nextFirst = maxsize-1;
        nextLast = size;
    }

    public void resizeBigger() {
        int start = (nextFirst+1) % maxsize;
        double factor = 1.2;
        int new_maxsize = maxsize;
        while(new_maxsize == maxsize) {
            new_maxsize = (int) (maxsize*factor);
            factor+=0.1;
        }
        maxsize = new_maxsize;
        T[] newArray = (T[]) new Object[maxsize];
        circularCopy(array, start, newArray, size);
        array = newArray;
        nextFirst = maxsize-1;
        nextLast = size;

    }
    @Override
    public void addFirst(T data) {
        if(isEmpty()) {
            nextLast++;
        }
        if(isFull()) {
            resizeBigger();
        }
        size++;
        array[nextFirst] = data;
        if(nextFirst == 0) {
            nextFirst = maxsize-1;
        } else {
            nextFirst--;
        }
    }

@Override
    public void addLast(T data) {
        if(isEmpty()) {
            addFirst(data);
            return;
        }
        if(isFull()) {
            resizeBigger();
        }
        size++;
        array[nextLast] = data;
        if(nextLast == maxsize-1) {
            nextLast = 0;
        } else {
            nextLast++;
        }

    }
@Override
    public int size() {
        return size;
    }
@Override
    public void printDeque() {
        for(int cnt = 0, index = (nextFirst+1) % maxsize; cnt < size;cnt++, index++) {
            if(index >= array.length) {
                index %= array.length;
            }
            System.out.print(array[index]+" ");
            System.out.println();
        }
    }
@Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }
        T ans = array[(nextFirst+1) % maxsize];
        array[(nextFirst+1) % maxsize] = null;
        nextFirst = (nextFirst+1) % maxsize;
        if(size == 1) {
            nextLast--;
        }
        size--;
        return ans;
    }
@Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        T ans;
        if(nextLast == 0) {
            ans = array[maxsize-1];
            array[maxsize-1] = null;
            nextLast = maxsize-1;
        } else {
            ans = array[nextLast-1];
            array[nextLast-1] = null;
            nextLast--;
        }
        if(size == 1) {
            nextFirst = 0;
        }
        size--;
        return ans;

    }
@Override
    public T get(int index) {
        if(isEmpty()) {
            return null;
        }
        int start = (nextFirst+1) % maxsize;
        int realIndex = (start + index) % maxsize;
        if(index >= 0 && index < size) {
            return array[realIndex];
        } else {
            return null;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        public ArrayDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }
        @Override
        public T next() {
            T returnItem = array[pos];
            pos += 1;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if(other.size() != this.size()) {
            return false;
        }
        int cnt = 0;
        for(T item : other) {
            if(this.get(cnt) != item) {
                return false;
            }
            cnt += 1;
        }
        return true;
    }
}
