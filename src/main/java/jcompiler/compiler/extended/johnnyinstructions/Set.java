package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Set extends JohnnyInstruction {

    private final int value;

    public Set(int value) {
        this.value = value;
    }

    public int getH() {
        return 13;
    }

    public int getL() {
        return value;
    }
}
