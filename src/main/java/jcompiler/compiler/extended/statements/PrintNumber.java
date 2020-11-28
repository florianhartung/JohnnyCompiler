package jcompiler.compiler.extended.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.johnnyinstructions.Take;

public class PrintNumber implements Statement {

    private final int numberToPrint;

    public PrintNumber(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    public JohnnyInstruction[] compile() {

        int adrOfNumberToPrint = Memory.addVariable(numberToPrint);

        return new JohnnyInstruction[]{new Take(adrOfNumberToPrint)};
    }
    public int getNumberOfRequiredInstructions() {
        return 1;
    }
}
