package jcompiler.compiler.standard.statements;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.johnnyinstructions.Jump;

public class WhileEnd implements Statement {

    private int whileStartAddress;

    public void setStartAddress(int whileStartAddress) {
        this.whileStartAddress = whileStartAddress;
    }

    @Override
    public JohnnyInstruction[] compile() {
        return new JohnnyInstruction[] {new Jump(whileStartAddress)};
    }
}
