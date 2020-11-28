package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Out extends JohnnyInstruction implements DataAccessor {

    private int valueOutputAdr;

    public Out(int valueOutputAdr) {
        this.valueOutputAdr = valueOutputAdr;
    }

    public int getH() {
        return 12;
    }

    public int getL() {
        return valueOutputAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        valueOutputAdr += valueOutputAdr;
    }
}
