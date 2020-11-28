package jcompiler.compiler.extended.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Save extends JohnnyInstruction implements DataAccessor {

    private int saveToAdr;

    public Save(int saveToAdr) {
        this.saveToAdr = saveToAdr;
    }

    public int getH() {
        return 4;
    }

    public int getL() {
        return saveToAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        saveToAdr += shiftAmount;
    }
}
