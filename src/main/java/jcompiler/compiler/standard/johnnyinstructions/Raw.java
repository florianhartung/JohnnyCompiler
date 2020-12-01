package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Raw extends JohnnyInstruction implements DataAccessor {

    private final int hi;
    private int lo;
    private final boolean isAddress;

    public Raw(int hi, int lo, boolean isAddress) {
        this.hi = hi;
        this.lo = lo;
        this.isAddress = isAddress;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        lo = isAddress ? lo + shiftAmount : lo;
    }

    @Override
    public int getH() {
        return hi;
    }

    @Override
    public int getL() {
        return lo;
    }
}
