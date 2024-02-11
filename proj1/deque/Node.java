package deque;

public class Node<Item> {
    Item data;
    Node<Item> prev;
    //point at the previous
    Node<Item> next;
    //pint at the next

    //constructor
    public Node(Item value, Node<Item> front, Node<Item> rear) {
        data = value;
        prev = front;
        next = rear;
    }

}
