import acm.program.ConsoleProgram;

/**
 * Created by faith on 2020/4/2.
 */
public class JCalc extends ConsoleProgram {

    // Create program objects
    Queue Qin = new Queue(); // Queue for input expressions
    postFix pf = new postFix(); // Postfix converter and interpreter

    // Entry point
    public void run() {
        println("Infix expression evaluator, enter expression of blank line to exit.");
        while (true) {
            String input = readLine("expr: ");
            if (input.equals("")) break;
            pf.parse(input, Qin);
            double result = pf.doExpression(Qin);
            println(input + " = " + result);
        }
        println("Program terminated.");
    }
}
