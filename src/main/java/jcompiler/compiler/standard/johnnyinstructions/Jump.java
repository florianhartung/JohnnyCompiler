package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Jump extends JohnnyInstruction {

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

    public int getJumpToAdr() {
        return jumpToAdr;
    }

    public void setJumpToAdr(int jumpToAdr) {
        this.jumpToAdr = jumpToAdr;
    }
}
