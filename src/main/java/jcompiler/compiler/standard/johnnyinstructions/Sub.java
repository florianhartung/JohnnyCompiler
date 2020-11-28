package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Sub extends JohnnyInstruction implements DataAccessor {

    private int subFromAdr;

    public Sub(int subFromAdr) {
        this.subFromAdr = subFromAdr;
    }

    public int getH() {
        return 3;
    }

    public int getL() {
        return subFromAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        subFromAdr += shiftAmount;
    }
}
