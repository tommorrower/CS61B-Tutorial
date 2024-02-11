package deque;

public class node<Item> {
    Item data;
    node<Item> prev;
    //point at the previous
    node<Item> next;
    //pint at the next

    //constructor
    public node(Item value, node<Item> front, node<Item> rear) {
        data = value;
        prev = front;
        next = rear;
    }

}
