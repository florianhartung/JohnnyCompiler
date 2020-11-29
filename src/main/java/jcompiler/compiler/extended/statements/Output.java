package jcompiler.compiler.extended.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.extended.johnnyinstructions.Out;
import jcompiler.compiler.standard.johnnyinstructions.Save;
import jcompiler.compiler.standard.johnnyinstructions.Take;
import jcompiler.compiler.standard.statements.expressions.NumericExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Output implements Statement {

    private final String expressionToOutput;

    public Output(String expressionToOutput) {
        this.expressionToOutput = expressionToOutput;
    }

    public JohnnyInstruction[] compile() {

        JohnnyInstruction[] expressionInstructions = new NumericExpression(expressionToOutput).compile();

        List<JohnnyInstruction> instructionList = new ArrayList<>(Arrays.asList(expressionInstructions));
        instructionList.addAll(Arrays.asList(new Save(Memory.tmpVariable()), new Out(Memory.tmpVariable())));
        return instructionList.toArray(new JohnnyInstruction[0]);
    }
}
