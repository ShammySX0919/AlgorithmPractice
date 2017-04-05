package misc;

/**
 * Created by andrew on 4/5/2017.
 */
public class ExpressionForTargetNumber {
    static void check(double sum, double previous, String remainingNumbers, double target, String expr) {
        if (remainingNumbers.length() == 0) {
            if (sum + previous == target) {
                System.out.println(expr + " = " + target);
            }
        } else {
            //from left to right, for every prefix, operates(+-*/) it with previous sum
            //for * and /, postpone the result with sum
            for (int i = 1; i <= remainingNumbers.length(); i++) {
                double current = Double.parseDouble(remainingNumbers.substring(0, i));
                String remaining = remainingNumbers.substring(i);
                check(sum + previous, current, remaining, target, expr + " + " + current);
                check(sum, previous * current, remaining, target, expr + " * " + current);
                check(sum, previous / current, remaining, target, expr + " / " + current);
                check(sum + previous, -current, remaining, target, expr + " - " + current);
            }
        }
    }

    static void expressionForTarget(String digits, double target) {
        for (int i = 1; i <= digits.length(); i++) {
            String exp = digits.substring(0, i);
            check(0, Double.parseDouble(exp), digits.substring(i), target, exp);
        }
    }
    public static void main(String... args){
        expressionForTarget("314159265358",27182);
    }
}
