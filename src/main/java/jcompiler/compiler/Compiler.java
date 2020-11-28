package jcompiler.compiler;

import jcompiler.MemoryOverflowException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Compiler {

    protected List<Statement> statements = new ArrayList<>();

    public abstract void compile(BufferedReader input, BufferedWriter output) throws MemoryOverflowException, IOException;
}
