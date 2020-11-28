package jcompiler.compiler.standard.statements.expressions;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.johnnyinstructions.Add;
import jcompiler.compiler.standard.johnnyinstructions.Sub;
import jcompiler.compiler.standard.johnnyinstructions.Take;

import java.util.ArrayList;
import java.util.List;

public class NumericExpression implements Statement {

    public static final String VAR_REGEX = "[a-zA-Z]+";
    public static final String NUM_REGEX = "[0-9]+";

    private final String expression;

    public NumericExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public JohnnyInstruction[] compile() {
        boolean first = true;

        List<JohnnyInstruction> instructionList = new ArrayList<>();

        String[] splitValues = expression.split("([+\\- ])+");
        String operators = expression.replaceAll("[\\w\\d ]", "");

        List<String> toAdd = new ArrayList<>();
        List<String> toSub = new ArrayList<>();
        for (int i = 0; i < splitValues.length; i++) {
            String s = splitValues[i];
            boolean add;
            if (first) {
                add = true;
                first = false;
            } else {
                add = operators.charAt(i - 1) == '+';
            }
            if (add) {
                toAdd.add(s);
            } else {
                toSub.add(s);
            }
        }
        first = true;
        for (String s : toAdd) {
            if (s.matches(VAR_REGEX)) {
                if (!Memory.variableExists(s)) {
                    throw new IllegalStateException("Variable " + s + " does not exist!");
                }
                instructionList.add(first ? new Take(Memory.resolveId(s)) : new Add(Memory.resolveId(s)));
            } else if (s.matches(NUM_REGEX)) {
                instructionList.add(first ? new Take(Memory.addVariable(Integer.parseInt(s))) : new Add(Memory.addVariable(Integer.parseInt(s))));
            } else {
                throw new IllegalArgumentException("Invalid numeric expression syntax: " + expression + " at " + s);
            }
            if (first) {
                first = false;
            }
        }
        for (String s : toSub) {
            if (s.matches(VAR_REGEX)) {
                if (!Memory.variableExists(s)) {
                    throw new IllegalStateException("Variable " + s + " does not exist!");
                }

                instructionList.add(new Sub(Memory.resolveId(s)));
            } else if (s.matches(NUM_REGEX)) {
                instructionList.add(new Sub(Memory.addVariable(Integer.parseInt(s))));
            } else {
                throw new IllegalArgumentException("Invalid numeric expression syntax: " + expression + " at " + s);
            }
        }
        return instructionList.toArray(new JohnnyInstruction[0]);
    }
}
