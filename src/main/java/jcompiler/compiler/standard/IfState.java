package jcompiler.compiler.standard;

import java.util.Stack;

public class IfState {
    private static Stack<IfConstruct> ifConstructs = new Stack<>();


    public static void startIfStructure(String condition) {
        Operation operation;
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
        ifConstructs.push(new IfConstruct(operation, false));
    }

    public static void addElse() {
        ifConstructs.peek().addElse();
    }

    public static IfConstruct endIfStructure() {
        return ifConstructs.pop();
    }

    static class IfConstruct {
        private final Operation operation;
        private boolean hasElse;

        public IfConstruct(Operation operation, boolean hasElse) {
            this.operation = operation;
            this.hasElse = hasElse;
        }

        public void addElse() {
            hasElse = true;
        }

        public Operation getOperation() {
            return operation;
        }

        public boolean hasElse() {
            return hasElse;
        }
    }

    enum Operation {
        GT,LT,EQ,GTEQ,LTEQ
    }
}
