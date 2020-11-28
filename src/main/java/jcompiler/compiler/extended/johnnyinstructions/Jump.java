package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Jump extends JohnnyInstruction implements DataAccessor {

    private int jumpToAdr;

    public Jump(int jumpToAdr) {
        this.jumpToAdr = jumpToAdr;
    }

    public int getH() {
        return 5;
    }

    public int getL() {
        return jumpToAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        jumpToAdr += shiftAmount;
    }
}
