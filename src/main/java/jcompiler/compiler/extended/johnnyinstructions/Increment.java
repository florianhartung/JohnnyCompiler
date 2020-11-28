package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Increment extends JohnnyInstruction implements DataAccessor {

    private int incrementAdr;

    public Increment(int incrementAdr) {
        this.incrementAdr = incrementAdr;
    }

    public int getH() {
        return 7;
    }

    public int getL() {
        return incrementAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        incrementAdr += shiftAmount;
    }
}
