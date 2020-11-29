package jcompiler.compiler.standard;

import jcompiler.Memory;

import java.util.Stack;

public class ConstructStates {
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
        ifConstructs.push(new IfConstruct(operation, false, -1));
    }

    public static void addElse() {
        ifConstructs.peek().addElse();
    }

    public static IfConstruct endIfStructure() {
        return ifConstructs.pop();
    }

    public static void startWhileLoop(int startOfWhileLoop) {
        ifConstructs.peek().bindWithWhileLoop(startOfWhileLoop);
    }

    public static class IfConstruct {
        private final Operation operation;
        private boolean hasElse;
        private int startOfWhileLoop;

        public IfConstruct(Operation operation, boolean hasElse, int startOfWhileLoop) {
            this.operation = operation;
            this.hasElse = hasElse;
            this.startOfWhileLoop = startOfWhileLoop;
        }

        public void addElse() {
            hasElse = true;
            startOfWhileLoop = -1;
        }

        public void bindWithWhileLoop(int startOfWhileLoop){
            this.startOfWhileLoop = startOfWhileLoop;
            hasElse = false;
        }

        public Operation getOperation() {
            return operation;
        }

        public boolean hasElse() {
            return hasElse;
        }

        public int getStartOfWhileLoop() {
            return startOfWhileLoop;
        }
    }

    public enum Operation {
        GT,LT,EQ,GTEQ,LTEQ
    }
}
