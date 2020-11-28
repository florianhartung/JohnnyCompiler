package jcompiler.compiler.standard.statements;

import jcompiler.compiler.Statement;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.standard.johnnyinstructions.Halt;

public class Exit implements Statement {
    public JohnnyInstruction[] compile() {
        return new JohnnyInstruction[]{new Halt()};
    }
}
