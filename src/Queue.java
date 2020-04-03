/**
 * This class defines a Queue which stores the output postfix.
 * The codes of class Queue is used from the lecture slides with modifications.
 * @author John Wang
 *
 */
public class Queue {

    listNode front = null;
    listNode rear = null;

    public void enqueue(String arg) {  // Create a new node at rear
        listNode node = new listNode();
        node.data = arg;
        node.next = null;

        if (rear != null) {
            rear.next = node;  // Attach
        } else {
            front = node;  // Attach if rear is null
        }
        rear = node;
    }

    public String dequeue() {
        if (front != null) {
            if (front == rear)
                rear = null;
            String j = front.data;
            front = front.next;  // Next node moves up a spot as the front node detaches.
            return j;
        } else {
            return null;  // Return null if empty
        }
    }

}
