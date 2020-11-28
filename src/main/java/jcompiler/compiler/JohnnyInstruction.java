package jcompiler.compiler;

public abstract class JohnnyInstruction {
    private String name;

    protected JohnnyInstruction() {}

    public JohnnyInstruction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    public abstract int getH();
    public abstract int getL();
}
