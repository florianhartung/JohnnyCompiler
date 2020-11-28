package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.Statement;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.johnnyinstructions.Save;
import jcompiler.compiler.standard.johnnyinstructions.Take;
import jcompiler.compiler.standard.statements.expressions.NumericExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariableAssignment implements Statement {

    private final String id;
    private final String value;


    public VariableAssignment(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public JohnnyInstruction[] compile() {
        String[] splitValues = value.split("([+\\- ])+");
        if (splitValues.length == 1 && !Memory.variableExists(id)) {
            if (splitValues[0].matches("[0-9]+")) {
                Memory.addVariable(id, Integer.parseInt(splitValues[0]));
                return new JohnnyInstruction[0];
            } else if (splitValues[0].matches("[a-zA-Z]+")) {
                if (!Memory.variableExists(splitValues[0])) {
                    throw new IllegalStateException("Variable " + splitValues[0] + " does not exist!");
                }
                return new JohnnyInstruction[]{new Take(Memory.resolveId(splitValues[0])), new Save(Memory.resolveId(id))};
            } else {
                throw new IllegalArgumentException("Invalid syntax: " + id + " = " + value);
            }
        } else if (splitValues.length > 0) {
            if (!Memory.variableExists(id)) {
                Memory.addVariable(id, 0);
            }

            //move value of expression to db
            JohnnyInstruction[] expression = new NumericExpression(value).compile();
            List<JohnnyInstruction> instructionList = new ArrayList<>(Arrays.asList(expression));

            //save to (new) identifier
            instructionList.add(new Save(Memory.resolveId(id)));

            return instructionList.toArray(new JohnnyInstruction[0]);
        } else {
            throw new IllegalArgumentException("Invalid Syntax: " + id + " = " + value);
        }
    }
}