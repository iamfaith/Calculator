import javafx.geometry.Pos;

import java.util.StringTokenizer;

/**
 * Created by faith on 2020/4/2.
 */
public class postFix {

    public void parse(String input, Queue output) {

        StringTokenizer st = new StringTokenizer(input.trim(), "+-*/(),", true);

        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            output.enqueue(token);
        }

    }


    public double doExpression(Queue qin) {

        Queue PostFix = new Queue();  // crate a instance of the output Queue.
        Stack operator = new Stack();  // crate a instance of the stack.


        while (true) {
            String token = qin.dequeue();  // dequeue one by one,
            if (token == null) {                 // until the queue is empty
                break;
            }

            if (In2pJ.isNumeric(token)) {  // if a number is detected, put it into the queue.
                PostFix.enqueue(token);

				/*Code below compare the precedence of the operator,
                 * if the token contains an operator and it has a higher precedence,put it into the queue.
				 */
            } else if (In2pJ.isOperator(token)) {  // if a operator is detected
                while (In2pJ.isGreaterPrec(token, operator.peek())) {
                    PostFix.enqueue(operator.pop());
                }
                operator.push(token);  // Otherwise push the token on the stack.

            } else if (token.equals("(")) {
                operator.push(token);  // push ( to the stack as indicated on the Shunting-yard algorithm instruction sheet.

            } else if (token.equals(")")) {

                while (!"(".equals(operator.peek())) {  // peek to see if a operator is on top of the stack or not.

                    PostFix.enqueue(operator.pop());   // Pop all the operators into the queue until meets "(".
                }

                operator.pop();  // Discard the "("

            }

        }//while

        while (operator.peek() != null) {
            PostFix.enqueue(operator.pop());  // Pop everything from the stack into the queue
        }

        return PostEval(PostFix);
    }

    private double PostEval(Queue PostFix) {
        Stack stack = new Stack();
        while (true) {
            String token = PostFix.dequeue();  // step1
            if (token == null) {
                break;
            }

            if (In2pJ.isNumeric(token)) {
                stack.push(token);
            } else if (In2pJ.isOperator(token)) {
                Double result = 0.0;
                String OP_A = stack.pop();
                String OP_B = stack.pop();
                Double A = Double.parseDouble(OP_A);
                Double B = Double.parseDouble(OP_B);
                switch (token) {
                    case "+":
                        result = B + A;
                        break;
                    case "-":
                        result = B - A;
                        break;
                    case "*":
                        result = B * A;
                        break;
                    case "/":
                        result = B / A;
                        break;
                }
                stack.push(String.valueOf(result));
            }


        }


        return Double.parseDouble(stack.pop());
    }


    public static void main(String[] args) {
        Queue Qin = new Queue();
        postFix postFix = new postFix();
        postFix.parse("5*(3+4)/2", Qin);


        Double ret = postFix.doExpression(Qin);
        System.out.println(ret);
    }
}
