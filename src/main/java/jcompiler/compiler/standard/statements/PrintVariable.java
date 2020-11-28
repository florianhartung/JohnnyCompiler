package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.Statement;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.johnnyinstructions.Take;

public class PrintVariable implements Statement {

    private final String variableToPrint;

    public PrintVariable(String variableToPrint) {
        this.variableToPrint = variableToPrint;
    }

    public JohnnyInstruction[] compile() {
        if(!Memory.variableExists(variableToPrint)) {
            throw new IllegalStateException("Variable " +  variableToPrint + " does not exist!");
        }

        return new JohnnyInstruction[]{new Take(Memory.resolveId(variableToPrint))};
    }
}
