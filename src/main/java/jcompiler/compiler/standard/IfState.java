package jcompiler.compiler.standard;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.statements.IfStart;

import java.util.List;
import java.util.Stack;

public class IfState {
    private static int inIfStructure = 0;
    private static Stack<Operation> operationStack = new Stack<>();


    public static void startIfStructure(String condition) {
        Operation operation;
        System.out.println(13);
        switch (condition) {
            case ">":
                operation = Operation.GT;
                break;
            case "<":
                operation = Operation.LT;
                break;
            case "<=":
                operation = Operation.LTEQ;
                break;
            case ">=":
                operation = Operation.GTEQ;
                break;
            case "==":
                operation = Operation.EQ;
                break;
            default:
                throw new IllegalArgumentException("Invalid condition!");
        }
        System.out.println(123);
        operationStack.push(operation);
        inIfStructure++;
    }

    public static Operation endIfStructure() {
        inIfStructure--;
        return operationStack.pop();
    }

    enum Operation {
        GT,LT,EQ,GTEQ,LTEQ
    }
}
