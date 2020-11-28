package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Null extends JohnnyInstruction implements DataAccessor {

    private int nullAdr;

    public Null(int nullAdr) {
        this.nullAdr = nullAdr;
    }

    public int getH() {
        return 9;
    }

    public int getL() {
        return nullAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        nullAdr += shiftAmount;
    }
}
