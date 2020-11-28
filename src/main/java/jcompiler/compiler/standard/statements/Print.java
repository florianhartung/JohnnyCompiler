package jcompiler.compiler.standard.statements;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.statements.expressions.NumericExpression;

public class Print implements Statement {

    private final String expressionToPrint;

    public Print(String expressionToPrint) {
        this.expressionToPrint = expressionToPrint;
    }

    public JohnnyInstruction[] compile() {
        return new NumericExpression(expressionToPrint).compile();
    }
}
