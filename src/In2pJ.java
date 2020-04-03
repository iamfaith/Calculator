/**
 * This program translates fore-function infix into postfix that can be processed by a simple interpreter.
 * The codes for StringTokenizer is used from the instruction PDF with modifications.
 *
 * @author John Wang - 260887758
 */

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

public class In2pJ extends ConsoleProgram {

    /**
     * This method assigns each operator with their precedence and return them.
     * @return the corresponding precedence.
     */
    public static int getPrec(String str) {
        if (str.equals("+")) {
            return 2;
        }
        if (str.equals("-")) {
            return 2;
        }
        if (str.equals("*")) {
            return 3;
        }
        if (str.equals("/")) {
            return 3;
        }
        return 0;  // this line is just to qualify the conditions of this method to be valid.
        // 0 would never be returned under the context of this assignment.

    }

    /**
     * This method checks whether a string is one of the fore-operator or not.
     * @return a boolean, true if string equals to "+" "-" "*" "/", false otherwise.
     */
    public static boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return true;
        }
        return false;
    }

    /**
     * This method checks whether the token(operator) has a greater precedence or not.
     * @return a boolean, true if the string's precedence > the precedence of the operator on top of the stack, false otherwise.
     */
    public static boolean isGreaterPrec(String token, String current) {
        if (current != null && isOperator(current)) {
            return getPrec(current) - getPrec(token) >= 0;  // the boolean expressions >= evaluate the precedence and returns.
        } else {
            return false;
        }
    }

    /**
     * This method checks whether a string is a number or not.
     * @return a boolean, true if a number is detected, false otherwise.
     */
    public static boolean isNumeric(String str) {  // learned at https://www.baeldung.com/java-check-string-number
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void run() {
        while (true) {  // this program will run into a loop, keep translating the infix until user close the window.

            String str = readLine("Enter string: ");
            StringTokenizer st = new StringTokenizer(str.trim(), "+-*/(),", true);

            Queue output = new Queue();  // crate a instance of the output Queue.
            Stack operator = new Stack();  // crate a instance of the stack.

            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if (isNumeric(token)) {  // if a number is detected, put it into the queue.
                    output.enqueue(token);

				/*Code below compare the precedence of the operator,
				 * if the token contains an operator and it has a higher precedence,put it into the queue.
				 */
                } else if (isOperator(token)) {  // if a operator is detected
                    while (isGreaterPrec(token, operator.peek())) {
                        output.enqueue(operator.pop());
                    }
                    operator.push(token);  // Otherwise push the token on the stack.

                } else if (token.equals("(")) {
                    operator.push(token);  // push ( to the stack as indicated on the Shunting-yard algorithm instruction sheet.

                } else if (token.equals(")")) {

                    while (!"(".equals(operator.peek())) {  // peek to see if a operator is on top of the stack or not.

                        output.enqueue(operator.pop());   // Pop all the operators into the queue until meets "(".
                    }

                    operator.pop();  // Discard the "("

                }

            }

            while (operator.peek() != null) {
                output.enqueue(operator.pop());  // Pop everything from the stack into the queue
            }
            print("Postfix: ");
            while (true) {
                String oneByOne = output.dequeue();  // dequeue one by one,
                if (oneByOne == null) {                 // until the queue is empty
                    break;
                }
                print(" " + oneByOne);  // prints output
            }
            println();  // prints a line to prepare for next translation.
        }
    }
}
