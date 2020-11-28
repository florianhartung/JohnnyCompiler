package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.Statement;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.johnnyinstructions.Add;
import jcompiler.compiler.standard.johnnyinstructions.Save;
import jcompiler.compiler.standard.johnnyinstructions.Sub;
import jcompiler.compiler.standard.johnnyinstructions.Take;

import java.util.ArrayList;
import java.util.List;

public class VariableAssignment implements Statement {

    public static final String VAR_REGEX = "[a-zA-Z]+";
    public static final String NUM_REGEX = "[0-9]+";
    public static final String VALUE_REGEX = "(" + NUM_REGEX + "|" + VAR_REGEX + ")";
    public static final String OPERATOR_REGEX = "([+|\\-])";


    private final String id;
    private final String value;


    public VariableAssignment(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public JohnnyInstruction[] compile() {
        String[] splitValues = value.split("([+\\- ])+");
        System.out.println("splitValues = " + splitValues.length);
        if (splitValues.length == 1) {
            if (splitValues[0].matches(NUM_REGEX)) {
                Memory.addVariable(id, Integer.parseInt(splitValues[0]));
                return new JohnnyInstruction[0];
            } else if (splitValues[0].matches(VAR_REGEX)) {
                if (!Memory.variableExists(splitValues[0])) {
                    throw new IllegalStateException("Variable " + splitValues[0] + " does not exist!");
                }
                return new JohnnyInstruction[]{new Take(Memory.resolveId(splitValues[0])), new Save(Memory.resolveId(id))};
            } else {
                throw new IllegalArgumentException("Invalid syntax: " + id + " = " + value);
            }
        } else if (splitValues.length >= 2) {
            if (!Memory.variableExists(id)) {
                Memory.addVariable(id, 0);
            }

            boolean first = true;

            List<JohnnyInstruction> instructionList = new ArrayList<>();


            String operators = value.replaceAll("[\\w\\d ]", "");

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
                    throw new IllegalArgumentException("Invalid syntax: " + id + " = " + value + " at " + s);
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
                    throw new IllegalArgumentException("Invalid syntax: " + id + " = " + value + " at " + s);
                }
            }
            instructionList.add(new Save(Memory.resolveId(id)));

            return instructionList.toArray(new JohnnyInstruction[0]);
        } else {
            throw new IllegalArgumentException("Invalid Syntax: " + id + " = " + value);
        }
    }
}