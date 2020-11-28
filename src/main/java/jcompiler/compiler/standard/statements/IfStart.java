package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.IfState;
import jcompiler.compiler.standard.johnnyinstructions.*;
import jcompiler.compiler.standard.statements.expressions.ConditionalExpression;
import jcompiler.compiler.standard.statements.expressions.NumericExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IfStart implements Statement {

    //y>=x  g   y-x
    //y<x   b   y-x
    //y<=x  g   x-y
    //y>x   b   x-y
    //y>x bad 2 jmps
    //y<x good 1 jump

    //go 2 => true
    //y >= x    x - y -> one jump
    //x >= y    y - x -> one jump
    //x < y     x - y -> two jumps
    //y < x     y - x -> two jumps
    //y == x    x - y AND y - x both one jump

    private final String condition;

    public IfStart(String condition) {//y>x means y sub x then non null, one step (bad)
        this.condition = condition;
    }

    @Override
    public JohnnyInstruction[] compile() {
        String operation = condition.replaceAll("[\\w\\d ]", "");

        IfState.startIfStructure(operation);

        return new ConditionalExpression(condition).compile();
    }
}
