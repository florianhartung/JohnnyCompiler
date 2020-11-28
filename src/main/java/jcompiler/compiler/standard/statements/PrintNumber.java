package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.Statement;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.johnnyinstructions.Take;

public class PrintNumber implements Statement {

    private final int numberToPrint;

    public PrintNumber(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    public JohnnyInstruction[] compile() {
        return new JohnnyInstruction[]{new Take(Memory.addVariable(numberToPrint))};
    }
}
