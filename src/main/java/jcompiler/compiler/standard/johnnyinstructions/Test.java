package jcompiler.compiler.standard.johnnyinstructions;

import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;

public class Test extends JohnnyInstruction implements DataAccessor {

    private int testAdr;

    public Test(int testAdr) {
        this.testAdr = testAdr;
    }

    public int getH() {
        return 6;
    }

    public int getL() {
        return testAdr;
    }

    @Override
    public void shiftAddress(int shiftAmount) {
        testAdr += shiftAmount;
    }
}
