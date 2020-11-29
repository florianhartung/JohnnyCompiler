package jcompiler.compiler.extended.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.extended.johnnyinstructions.Read;

public class Input implements Statement {

    private final String saveAdr;

    public Input(String saveAdr) {
        this.saveAdr = saveAdr;
    }

    @Override
    public JohnnyInstruction[] compile() {

        if(!Memory.variableExists(saveAdr)) {
            Memory.addVariable(saveAdr, 0);
        }

        return new JohnnyInstruction[] {new Read(Memory.resolveId(saveAdr))};
    }
}
