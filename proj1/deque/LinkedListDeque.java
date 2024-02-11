package deque;
import java.util.Iterator;
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node<T> sentinel;
    private int size;
    public LinkedListDeque() {
        //notice that the prev pointer of sentinel will not be used
        sentinel = new Node<>(null, null, null);
        size = 0;
    }
    private T getRecursiveHelper(int index, Node<T> currentNode) {
        if (index == 0) {
            return currentNode.data;
        }
        return getRecursiveHelper(index - 1, currentNode.next);
    }
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(index - 1, sentinel.next);
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos;
        LinkedListDequeIterator() {
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
    public void addFirst(T value) {

        if (isEmpty()) {
            Node<T> newNode = new Node<>(value, null, null);
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node<T> first = sentinel.next;
            Node<T> newNode = new Node<>(value, first.prev, first);
            first.prev = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }
    @Override
    public void addLast(T value) {
        if (isEmpty()) {
            Node<T> newNode = new Node<>(value, null, null);
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node<T> first = sentinel.next;
            Node<T> newNode = new Node<>(value, first.prev, first);
            first.prev.next = newNode;
            first.prev = newNode;
        }
        size += 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
            return;
        }
        Node<T> pointer = sentinel.next;
        for (int cnt = 0; cnt < size(); cnt++) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> target = sentinel.next;
        //only one Node
        if (size() == 1) {
            sentinel.next = null;
        } else {
            sentinel.next = target.next;
            target.next.prev = target.prev;
            target.prev.next = target.next;
        }
        size -= 1;
        return target.data;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> target = sentinel.next.prev;
        if (size() == 1) {
            sentinel.next = null;
        } else {
            sentinel.next.prev = target.prev;
            target.prev.next = sentinel.next;
        }
        size -= 1;
        return target.data;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Node<T> pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
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
        Deque<T> list = (Deque<T>) o;
        if (list.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (list.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

}
