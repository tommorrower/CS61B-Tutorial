package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }
    private boolean isFull() {
        return size == array.length;
    }

    private void circularCopy(T[]old, int start, T[]fresh, int length) {
        for (int cnt = 0; cnt < size; cnt++) {
            if (start == size) {
                start = 0;
            }
            fresh[cnt] = old[start];
            start++;
        }
    }
    private void resize(int capacity) {
        int start = (nextFirst + 1) % array.length;
        T[] newArray = (T[]) new Object [capacity];
        circularCopy(array, start, newArray, size);
        array = newArray;
        nextFirst = array.length - 1;
        nextLast = size;

    }

    @Override
    public void addFirst(T data) {
        if (isFull()) {
            resize(size * 2);
        }
        if (isEmpty()) {
            nextLast = 1;
            array[0] = data;
            nextFirst = array.length - 1;
        } else {
            array[nextFirst] = data;
            if (nextFirst == 0) {
                nextFirst = array.length - 1;
            } else {
                nextFirst--;
            }
        }
        size += 1;
    }

    @Override
    public void addLast(T data) {
        if (isEmpty()) {
            addFirst(data);
            return;
        }
        if (isFull()) {
            resize(size * 2);
        }
        size += 1;
        array[nextLast] = data;
        if (nextLast == array.length - 1) {
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
        for (int cnt = 0, index = (nextFirst + 1) % array.length; cnt < size; cnt++, index++) {
            if (index >= array.length) {
                index %= array.length;
            }
            System.out.print(array[index] + " ");
            System.out.println();
        }
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ans = array[(nextFirst + 1) % array.length];
        array[(nextFirst + 1) % array.length] = null;
        nextFirst = (nextFirst + 1) % array.length;
        if (size == 1) {
            nextLast--;
        }
        size -= 1;
        if ((size < array.length / 4) && (size > 8)) {
            resize(array.length / 2);
        }
        return ans;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ans;
        if (nextLast == 0) {
            ans = array[array.length - 1];
            array[array.length - 1] = null;
            nextLast = array.length - 1;
        } else {
            ans = array[nextLast - 1];
            array[nextLast - 1] = null;
            nextLast -= 1;
        }
        if (size == 1) {
            nextFirst = 0;
        }
        size -= 1;
        if ((size < array.length / 4) && (size > 8)) {
            resize(array.length / 2);
        }
        return ans;

    }
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index > array.length - 1 || index < 0) {
            return null;
        }
        int start = (nextFirst + 1) % array.length;
        int realIndex = (start + index) % array.length;
        return array[realIndex];
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        ArrayDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }
        @Override
        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (!other.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }
}
