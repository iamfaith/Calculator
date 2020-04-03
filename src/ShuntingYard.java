
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by faith on 2020/3/6.
 */
public class ShuntingYard {


    public static class OperatorClass {
        int predecence;
        String Ops;
    }

    public static enum Operator {

        ADD(2), SUBTRACT(2), MULTIPLY(3), DIVIDE(3), POWER(4);
        final int predecence;

        Operator(int p) {
            predecence = p;
        }

    }

    public static class Function {

        String func;

        Function(String func) {
            this.func = func;
        }
    }

    private static HashMap<String, Integer> m = new HashMap<String, Integer>() {{
        put("+", 2);
        put("-", 2);
        put("*", 3);
        put("/", 3);
        put("^", 4);
    }};

    public static boolean isOperator(String str) {
        return m.containsKey(str);
    }

    
    public static boolean isGreaterPrec(String token, String current) {
    	if (current != null && isOperator(current)) {
    		return m.get(current) - m.get(token) >= 0;
    	}
    	else {
    		return false;
    	}
    }
    
    
    private static String IGNORE = ",";
    //TODO: add left and righarenthesis


    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isFunction(String str) {
        return !isOperator(str) && !isNumeric(str) && !Objects.equals(str, IGNORE);
    }


}
