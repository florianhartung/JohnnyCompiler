package jcompiler.compiler.standard.statements;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;

public class IfEnd implements Statement {
    @Override
    public JohnnyInstruction[] compile() {
        return new JohnnyInstruction[0];
    }
}
