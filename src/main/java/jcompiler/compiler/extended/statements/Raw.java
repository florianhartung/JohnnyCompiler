package jcompiler.compiler.extended.statements;

import jcompiler.Memory;
import jcompiler.compiler.JohnnyInstruction;
import jcompiler.compiler.Statement;

import java.util.ArrayList;
import java.util.List;

public class Raw implements Statement {

    public static final String VAR_REGEX = "[a-zA-Z]+";
    public static final String NUM_REGEX = "[0-9]+";

    private final String code;

    public Raw(String code) {
        this.code = code;
    }

    String[] names = {"take", "add", "sub", "save", "jmp", "tst", "inc", "dec", "null", "hlt", "read", "out", "set"};

    @Override
    public JohnnyInstruction[] compile() {
        String[] splitCode = code.substring(1).split(" ");

        int hi = 0;
        int lo = 0;

        if (splitCode[0].matches(NUM_REGEX)) {
            hi = Integer.parseInt(splitCode[0]);
        } else {
            for (int i = 0; i < names.length; i++) {
                if (splitCode[0].toLowerCase().equals(names[i])) {
                    hi = i + 1;
                    break;
                }
            }
            if (hi == 0) {
                throw new IllegalArgumentException("Could not find instruction at statement " + code);
            }
        }


        boolean isAddress;
        List<JohnnyInstruction> instructionList = new ArrayList<>();
        if (splitCode[1].matches(NUM_REGEX) || (splitCode[1].startsWith("$") && splitCode[1].substring(1).matches(NUM_REGEX))) {
            isAddress = splitCode[1].startsWith("$");

            lo = isAddress ? Integer.parseInt(splitCode[1].substring(1)) : Integer.parseInt(splitCode[1]);

        } else {
            String varName = splitCode[1];
            if (!Memory.variableExists(varName)) {
                throw new IllegalStateException("Variable " + varName + " does not exist!");
            }
            lo = Memory.resolveId(varName);
            isAddress = true;
        }

        instructionList.add(new jcompiler.compiler.standard.johnnyinstructions.Raw(hi, lo, isAddress));

        return instructionList.toArray(new JohnnyInstruction[0]);
    }
}
