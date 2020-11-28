package jcompiler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Memory {

    public static final int maxMemoryLength = 1000;

    private static Map<String, Integer> dataPointers;

    private static List<Integer> dataSection;

    private static List<Integer> codeSection;

    private static int tmpVariable = -1;

    private static int codeSectionSize;

    static {
        codeSectionSize = 0;
        dataPointers = new HashMap<>();
        dataSection = new ArrayList<>();
        codeSection = new ArrayList<>();
    }

    public static void addToCodeSectionSize(int i) {
        codeSectionSize += i;
    }

    public static void addCode(int value) {
        codeSection.add(value);
    }

    public static int addVariable(int value) {
        dataSection.add(value);
        return dataSection.size() - 1;
    }

    public static int addVariable(String id, int value) {
        dataPointers.put(id, dataSection.size());

        dataSection.add(value);
        return dataSection.size() - 1;
    }

    public static int tmpVariable() {
        if (tmpVariable == -1) {
            tmpVariable = addVariable(0);
        }

        return tmpVariable;
    }

    public static boolean variableExists(String id) {
        return dataPointers.containsKey(id);
    }

    public static void setVariable(String id, int value) throws AddressViolationException {
        if (!dataPointers.containsKey(id)) {
            throw new AddressViolationException(id);
        }

        dataSection.set(resolveId(id), value);
    }

    public static List<Integer> generateMemory() throws MemoryOverflowException {
        if (dataSection.size() + codeSectionSize > maxMemoryLength) {
            throw new MemoryOverflowException("Memory overflow!\nNeeded memory: " + (dataSection.size() + codeSection.size()) + "\nAvailable memory: " + maxMemoryLength);
        }

        return Stream.concat(codeSection.stream(), dataSection.stream()).collect(Collectors.toList());
    }

    public static int resolveId(String id) {
        return dataPointers.get(id);
    }
}
