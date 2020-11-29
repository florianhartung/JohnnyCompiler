package jcompiler.compiler.standard.statements.expressions;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.standard.johnnyinstructions.Jump;
import jcompiler.compiler.standard.johnnyinstructions.Save;
import jcompiler.compiler.standard.johnnyinstructions.Sub;
import jcompiler.compiler.standard.johnnyinstructions.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionalExpression implements Statement {

    //go 2 => true
    //y >= x    x - y -> one jump
    //x >= y    y - x -> one jump
    //x < y     x - y -> two jumps
    //y < x     y - x -> two jumps
    //y == x    x - y AND y - x both one jump

    private final String expression;

    public ConditionalExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public JohnnyInstruction[] compile() {
        List<JohnnyInstruction> instructionList = new ArrayList<>();


        String[] splitCondition = expression.split(" ?(<=|>=|<|>|==) ?");
        String operation = expression.replaceAll("[\\w\\d ]", "");

        NumericExpression expression1 = new NumericExpression(splitCondition[0]);
        NumericExpression expression2 = new NumericExpression(splitCondition[1]);

        List<JohnnyInstruction> testInstructions = new ArrayList<>(Arrays.asList(new Save(Memory.tmpVariable()), new Test(Memory.tmpVariable())));

        switch (operation) {
            case ">":
                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.addAll(Arrays.asList(new Jump(-1), new Jump(-1)));

                break;
            case "<":
                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.addAll(Arrays.asList(new Jump(-1), new Jump(-1)));

                break;
            //return new JohnnyInstruction[]{new Take(Memory.addVariable(Integer.parseInt(splitCondition[1]))), new Sub(Memory.resolveId(splitCondition[0])), new Save(Memory.tmpVariable()), new Test(Memory.tmpVariable()), new Jump(-1), new Jump(-1)};
            case "<=":
                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.add(new Jump(-1));

                //operation = IfState.Operation.LTEQ;
                break;
            case ">=":
                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.add(new Jump(-1));
                //operation = IfState.Operation.GTEQ;
                break;
            case "==":
                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.add(new Jump(-1));


                //Order expressions: Save second at tmp memory address
                instructionList.addAll(Arrays.asList(expression1.compile()));
                instructionList.add(new Save(Memory.tmpVariable()));

                //Subtract
                instructionList.addAll(Arrays.asList(expression2.compile()));
                instructionList.add(new Sub(Memory.tmpVariable()));

                //Test
                instructionList.addAll(testInstructions);
                instructionList.add(new Jump(-1));

                //operation = IfState.Operation.EQ;
                break;
            default:
                throw new IllegalArgumentException("Invalid condition: " + expression);
        }


        return instructionList.toArray(new JohnnyInstruction[0]);
    }
}
