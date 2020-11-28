package jcompiler.compiler.standard.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.IfState;
import jcompiler.compiler.standard.johnnyinstructions.*;

public class IfStart implements Statement {

    //y>=x  g   y-x
    //y<x   b   y-x
    //y<=x  g   x-y
    //y>x   b   x-y
    //y>x bad 2 jmps
    //y<x good 1 jump

    private final String condition;

    public IfStart(String condition) {//y>x means y sub x then non null, one step (bad)
        this.condition = condition;
    }

    @Override
    public JohnnyInstruction[] compile() {
        String[] splitCondition = condition.split(" ?(<|>|<=|>=|==) ?");
        String operation = condition.replaceAll("[\\w\\d ]", "");

        if (!Memory.variableExists(splitCondition[0])) {
            Memory.addVariable(splitCondition[0], 0);
        }
        IfState.startIfStructure(operation);
        switch (operation) {
            case ">":
                return new JohnnyInstruction[]{new Take(Memory.resolveId(splitCondition[0])), new Sub(Memory.addVariable(Integer.parseInt(splitCondition[1]))), new Save(Memory.tmpVariable()), new Test(Memory.tmpVariable()), new Jump(-1), new Jump(-1)};
            case "<":
                return new JohnnyInstruction[]{new Take(Memory.addVariable(Integer.parseInt(splitCondition[1]))), new Sub(Memory.resolveId(splitCondition[0])), new Save(Memory.tmpVariable()), new Test(Memory.tmpVariable()), new Jump(-1), new Jump(-1)};
            case "<=":
                //operation = IfState.Operation.LTEQ;
                break;
            case ">=":
                //operation = IfState.Operation.GTEQ;
                break;
            case "==":
                //operation = IfState.Operation.EQ;
                break;
            default:
                throw new IllegalArgumentException("Invalid condition: " + condition);
        }
        System.out.println(1233);


        return new JohnnyInstruction[0];
    }
}
