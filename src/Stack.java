/**
 * This class defines the stack that is used to store operators and parenthesizes.
 * The codes of class Stack is used from the lecture slides with modifications.
 * @author John Wang
 *
 */
public class Stack {

    public void push(String arg) {  // add a node on top of the stack
        listNode node = new listNode();
        node.data = arg;
        node.next = top;
        top = node;
    }

    public String pop() {  // pop the top node out of the stack
        if (top == null) {
            return null;
        }
        String data = top.data;
        top = top.next;
        return data;
    }
    /**
     * This method allow the program to see what is on top of the stack without popping the data off of the stack.
     * @return the operator or the left parenthesis on top of the stack
     */
    public String peek() {
        if (top == null) {
            return null;
        }
        return top.data;
    }

    listNode top = null;
}
