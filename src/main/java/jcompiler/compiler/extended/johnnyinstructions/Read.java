package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Read extends JohnnyInstruction implements DataAccessor {

    private int valueSaveAdr;

    public Read(int valueSaveAdr) {
        this.valueSaveAdr = valueSaveAdr;
    }

    public int getH() {
        return 11;
    }

    public int getL() {
        return valueSaveAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        valueSaveAdr += shiftAmount;
    }
}
