import acm.gui.TableLayout;
import acm.program.Program;
import acm.gui.*;
import acm.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by faith on 2020/4/2.
 */
public class JCalcGUI extends Program {


    public static void main(String[] args) {
        new JCalcGUI().start(args);
    }

    private TextField input = null;
    private TextField output = null;

    private String[] operators = {"+",
            "-",
            "*",
            "/",
            ".",
            "(",
            ")",
            "C",
            "=",
            "Q"};

    public void init() {

        setLayout(new TableLayout(10, 4));
        input = new TextField();
        output = new TextField();
        String constrain = "gridwidth=4 height=50";
        add(constrain, input);
        add(constrain, output);


        String btnConstrain = "width=" + 80 + " height=" + 50;



//        add(new JButton("="), btnConstrain);
//        add(new JButton("+"), btnConstrain);


        for (int i = 0; i < 10; i++) {
            JButton button = new JButton("" + (9 - i));
            add(button, btnConstrain);
        }

        for (int i = 0; i < operators.length; i++) {
            add(new JButton(operators[i]), btnConstrain);
        }


        addActionListeners();
    }

    /**
     * Listen for a button action
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("C")) {
            input.setText("");
            output.setText("");
            return;
        }

        if (cmd.equals("=")) {
            Queue Qin = new Queue();
            postFix pf = new postFix();
            pf.parse(input.getText(), Qin);
            double result = pf.doExpression(Qin);
            output.setText(result + "");
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (cmd.equals(i + "")) {
//                int f = fahrenheitField.getValue();
//                int c = (int) Math.round((5.0 / 9.0) * (f - 32));
                input.setText(input.getText() + i);
            }
        }

        for (int i = 0; i < operators.length; i++) {
            if (cmd.equals(operators[i])) {
                input.setText(input.getText() + operators[i]);
            }
        }
    }

    private void addButtons() {

//        new JButton("Button 1");

    }
}
