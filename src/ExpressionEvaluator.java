
import java.util.Scanner;
import java.util.Stack;

/* Joshua Farren

 */

public class ExpressionEvaluator {
    public static void main(String [] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter an expression (with no spaces): ");

        String expression = input.next();
        System.out.println(expressionEvaluator(expression));
        input.close();

    }

    public static int expressionEvaluator(String expression) {
        Stack<Integer> operandStack = new Stack<>();

        Stack<Character>operatorStack = new Stack<>();

        expression = insertBlank(expression);

        String[] tokens = expression.split(" ");

        for(String token: tokens) {
            if(token.length() == 0) {
                continue;
            }
            else if(token.charAt(0) == '+' || token.charAt(0) == '-') {
                while(!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/' )) {
                    processOperator(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }
            else if(token.charAt(0) == '*' || token.charAt(0) == '/') {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processOperator(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }
            else if(token.trim().charAt(0) == '(') {
                operatorStack.push('(');
            }
            else if(token.trim().charAt(0) == ')') {
                while(operatorStack.peek() != '(') {
                    processOperator(operandStack, operatorStack);
                }
                operatorStack.pop();
            }
            else {
                operandStack.push(new Integer(token)); //Integer?

            }
        }
        while(!operatorStack.isEmpty()) {
            processOperator(operandStack, operatorStack);
        }
        return operandStack.pop();
    }

    public static void processOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        if(op == '+') {
            operandStack.push(op2 + op1);
        }
        else if(op == '-') {
            operandStack.push(op2 - op1);
        }
        else if(op == '*') {
            operandStack.push(op2 * op1);
        }
        else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }

    public static String insertBlank(String s) {
        String result = "";

        for(int i= 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '*' || s.charAt(i) == '/') {
                result += " " + s.charAt(i) + " ";
            }
            else result += s.charAt(i);
        }
        return result;
    }
}
