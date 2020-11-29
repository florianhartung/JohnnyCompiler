package jcompiler.compiler.standard.statements;

import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.ConstructStates;
import jcompiler.compiler.standard.statements.expressions.ConditionalExpression;

public class WhileStart implements Statement {

    private final String condition;

    public WhileStart(String condition) {
        this.condition = condition;
    }

    @Override
    public JohnnyInstruction[] compile() {
        String operation = condition.replaceAll("[\\w\\d ]", "");
        ConstructStates.startIfStructure(operation);


        return new ConditionalExpression(condition).compile();
    }
}
