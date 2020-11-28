package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Take extends JohnnyInstruction implements DataAccessor {

    private int fromAdr;

    public Take(int fromAdr) {
        this.fromAdr = fromAdr;
    }

    public int getH() {
        return 1;
    }

    public int getL() {
        return fromAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        fromAdr += shiftAmount;
    }
}
