package jcompiler.compiler.extended;

import jcompiler.Memory;
import jcompiler.MemoryOverflowException;
import jcompiler.compiler.Compiler;
import jcompiler.compiler.DataAccessor;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;
import jcompiler.compiler.extended.johnnyinstructions.Read;
import jcompiler.compiler.extended.statements.Input;
import jcompiler.compiler.extended.statements.Output;
import jcompiler.compiler.standard.ConstructStates;
import jcompiler.compiler.standard.johnnyinstructions.Jump;
import jcompiler.compiler.standard.statements.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedCompiler extends Compiler {

    @Override
    public void compile(BufferedReader input, BufferedWriter output) throws MemoryOverflowException, IOException {

        StringBuilder currentStatement = new StringBuilder();
        int buffer;
        while ((buffer = input.read()) != -1) {
            switch ((char) buffer) {
                case '\n':
                case '\r':
                    if (currentStatement.length() > 0) {
                        compileStatement(currentStatement.toString());
                        currentStatement = new StringBuilder();
                    }
                    break;
                default:
                    currentStatement.append((char) buffer);
            }
        }
        if (currentStatement.length() > 0) {
            compileStatement(currentStatement.toString());
        }

        List<JohnnyInstruction> johnnyInstructions = new ArrayList<>();
        for (Statement statement : statements) {
            if (statement instanceof IfEnd || statement instanceof WhileEnd) {
                int found = 0;
                int elseAddress = -1;
                ConstructStates.IfConstruct ifConstruct = ConstructStates.endIfStructure();
                ConstructStates.Operation operation = ifConstruct.getOperation();

                if (statement instanceof WhileEnd) {
                    ((WhileEnd) statement).setStartAddress(ifConstruct.getStartOfWhileLoop());
                }

                for (int j = johnnyInstructions.size() - 1; ; j--) {

                    if (johnnyInstructions.get(j) instanceof Jump && ((Jump) johnnyInstructions.get(j)).getJumpToAdr() == -1) {
                        if (ifConstruct.hasElse() && elseAddress == -1) {
                            ((Jump) johnnyInstructions.get(j)).setJumpToAdr(johnnyInstructions.size() + 1);
                            elseAddress = j + 1;
                            continue;
                        }
                        if (found == 1) {
                            if (operation == ConstructStates.Operation.GT || operation == ConstructStates.Operation.LT) {
                                ((Jump) johnnyInstructions.get(j)).setJumpToAdr(j + 2);
                            } else {
                                ((Jump) johnnyInstructions.get(j)).setJumpToAdr(ifConstruct.hasElse() ? elseAddress : johnnyInstructions.size() + 1);
                            }
                            break;
                        }
                        ((Jump) johnnyInstructions.get(j)).setJumpToAdr(ifConstruct.hasElse() ? elseAddress : johnnyInstructions.size() + 1);

                        if (operation == ConstructStates.Operation.GT || operation == ConstructStates.Operation.LT) {
                            found = 1;
                        } else if (operation == ConstructStates.Operation.GTEQ || operation == ConstructStates.Operation.LTEQ) {
                            break;
                        } else if (operation == ConstructStates.Operation.EQ) {
                            found = 1;
                        }
                    }
                }
            } else if (statement instanceof WhileStart) {
                int whileLoopStartAddress = johnnyInstructions.size();
                johnnyInstructions.addAll(Arrays.asList(statement.compile()));
                ConstructStates.startWhileLoop(whileLoopStartAddress);
                continue;
            }
            johnnyInstructions.addAll(Arrays.asList(statement.compile()));
        }

        int codeSectionSize = johnnyInstructions.size();

        for (JohnnyInstruction johnnyInstruction : johnnyInstructions) {
            if (johnnyInstruction instanceof DataAccessor) {
                ((DataAccessor) johnnyInstruction).shiftAddress(codeSectionSize);
            }
            Memory.addCode(johnnyInstruction.getH() * 1000 + johnnyInstruction.getL());
        }


        writeMemory(output);
    }

    private void compileStatement(String statement) {
        if (statement.toLowerCase().startsWith("print")) {
            String valueToPrint = statement.substring(6);

            statements.add(new Print(valueToPrint));
        } else if (statement.equalsIgnoreCase("exit")) {
            statements.add(new Exit());
        } else if (statement.matches("\\w+ ?=.*")) {
            String[] splitStatement = statement.split(" ?= ?");
            String id = splitStatement[0];
            String value = splitStatement[1];
            statements.add(new VariableAssignment(id, value));
        } else if (statement.toLowerCase().startsWith("if ")) {
            statements.add(new IfStart(statement.substring(3)));
        } else if (statement.equalsIgnoreCase("endif")) {
            statements.add(new IfEnd());
        } else if (statement.equalsIgnoreCase("else")) {
            statements.add(new IfElse());
        } else if (statement.toLowerCase().startsWith("while")) {
            statements.add(new WhileStart(statement.substring(6)));
        } else if (statement.equalsIgnoreCase("endwhile")) {
            statements.add(new WhileEnd());
        } else if (statement.toLowerCase().startsWith("input")) {
            statements.add(new Input(statement.substring(6)));
        } else if (statement.toLowerCase().startsWith("output")) {
            statements.add(new Output(statement.substring(7)));
        } else {
            throw new IllegalArgumentException("Syntax Error at " + statement);
        }
    }


    private void writeMemory(BufferedWriter output) throws MemoryOverflowException, IOException {
        List<Integer> memory = Memory.generateMemory();
        for (int i = 0; i < 1000; i++) {
            int value = i < memory.size() ? memory.get(i) : 0;
            output.write(String.valueOf(value));
            output.write("\r\n");
        }
    }
}
