package deque;

public class LinkedListDeque<Item> implements Deque<Item> {
    public node<Item> sentinel;
    private int size = 0;
    public LinkedListDeque() {
        //notice that the prev pointer of sentinel will not be used
        sentinel = new node<> (null,null,null);
        size = 0;
    }
    @Override
    public void addFirst(Item value) {

        if(isEmpty()) {
            node<Item> newNode = new node<>(value,null,null);
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            node<Item>First = sentinel.next;
            node<Item> newNode = new node<>(value,First.prev,First);
            First.prev = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }
@Override
    public void addLast(Item value) {
        if(isEmpty()) {
            node<Item> newNode = new node<>(value,null,null);
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            node<Item> First = sentinel.next;
            node<Item> newNode = new node<>(value,First.prev,First);
            First.prev.next = newNode;
            First.prev = newNode;
        }
        size += 1;
    }
@Override
    public int size() {
        return size;
    }
@Override
    public void printDeque() {
        if(isEmpty()) {
            System.out.println();
            return;
        }
        node<Item> pointer = sentinel.next;
        for(int cnt = 0; cnt < size();cnt++) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
@Override
    public Item removeFirst() {
        if(isEmpty())
            return null;
        node<Item> target = sentinel.next;
        //only one node
        if(size()==1) {
            sentinel.next=null;
        } else {
            sentinel.next = target.next;
            target.next.prev = target.prev;
            target.prev.next = target.next;
        }
        size -= 1;
        return target.data;
    }
@Override
    public Item removeLast() {
        if(isEmpty()) {
            return null;
        }
        node<Item> target = sentinel.next.prev;
        if(size() == 1) {
            sentinel.next=null;
        } else {
            sentinel.next.prev = target.prev;
            target.prev.next = sentinel.next;
        }
        size-=1;
        return target.data;
    }
@Override
    public Item get(int index) {
        if(index < 0 || index >= size()) {
            return null;
        }
        node<Item> pointer=sentinel.next;
        for(int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }
/*
    public Iterator<Item> iterator(){

    }

    public boolean equals(Object o){

    }

 */
}
