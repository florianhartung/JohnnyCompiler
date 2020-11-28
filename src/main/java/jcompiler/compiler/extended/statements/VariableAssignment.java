package jcompiler.compiler.extended.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.extended.johnnyinstructions.Set;
import jcompiler.compiler.standard.johnnyinstructions.Save;
import jcompiler.compiler.standard.johnnyinstructions.Take;

public class VariableAssignment implements Statement {

    private final String id;
    private final int value;

    public VariableAssignment(String id, int value) {
        this.id = id;
        this.value = value;
    }

    public JohnnyInstruction[] compile() {
        if (Memory.variableExists(id)) {
            return new JohnnyInstruction[]{new Set(value), new Save(Memory.resolveId(id))};
        } else {
            //int adrOfNumberToPrint = Memory.addVariable(numberToPrint);

            //return new JohnnyInstruction[]{new Take(adrOfNumberToPrint)};
            return null;
        }
    }

}
