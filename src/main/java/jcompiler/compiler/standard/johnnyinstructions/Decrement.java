package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Decrement extends JohnnyInstruction implements DataAccessor {

    private int decrementAdr;

    public Decrement(int decrementAdr) {
        this.decrementAdr = decrementAdr;
    }

    public int getH() {
        return 8;
    }

    public int getL() {
        return decrementAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        decrementAdr += shiftAmount;
    }
}
