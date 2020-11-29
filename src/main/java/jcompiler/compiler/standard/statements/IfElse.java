package jcompiler.compiler.standard.statements;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.ConstructStates;
import jcompiler.compiler.standard.johnnyinstructions.Jump;

public class IfElse implements Statement {
    @Override
    public JohnnyInstruction[] compile() {
        ConstructStates.addElse();
        return new JohnnyInstruction[] {new Jump(-1)};
    }
}
