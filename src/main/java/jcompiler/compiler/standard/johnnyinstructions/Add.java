package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Add extends JohnnyInstruction implements DataAccessor {

    private int addFromAdr;

    public Add(int addFromAdr) {
        this.addFromAdr = addFromAdr;
    }

    public int getH() {
        return 2;
    }

    public int getL() {
        return addFromAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        addFromAdr += shiftAmount;
    }
}
