package jcompiler;

import jcompiler.compiler.extended.ExtendedCompiler;
import jcompiler.compiler.standard.StandardCompiler;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: jc <*.jcode input file> <.ram output file>");
            System.exit(0);
        }
        String inputFile = args[0];
        String outputFile = args[1];

        boolean useExtendedCompiler = false;
        if (args.length > 2 && args[2].equalsIgnoreCase("-extended")) {
            useExtendedCompiler = true;
        }

        if (!inputFile.endsWith(".jcode")) {
            inputFile += ".jcode";
        }
        if (!outputFile.endsWith(".ram")) {
            outputFile += ".ram";
        }

        File input = new File(inputFile);
        File output = new File(outputFile);


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output));

            if (useExtendedCompiler) {
                new ExtendedCompiler().compile(bufferedReader, bufferedWriter);
            } else {
                new StandardCompiler().compile(bufferedReader, bufferedWriter);
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MemoryOverflowException e) {
            e.printStackTrace();
        }
    }
}
