package deque;

public class node<Item>
{
    Item data;
    node<Item> prev;
    //point at the previous
    node<Item> next;
    //pint at the next

    //constructor
    public node(Item value, node<Item> PREV, node<Item> NEXT) {
        data = value;
        prev = PREV;
        next = NEXT;
    }

}
