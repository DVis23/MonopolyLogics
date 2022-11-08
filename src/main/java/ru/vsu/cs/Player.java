package ru.vsu.cs;

public class Player {
    private final String name;
    private int liberalValues;
    private int step;
    private int skipping;

    public Player(String name, int liberalValues) {
        this.name = name;
        this.liberalValues = liberalValues;
        step = 0;
        skipping = 0;
    }

    public String getName() {
        return name;
    }
    public int getLiberalValues() {
        return liberalValues;
    }
    public int getStep() {
        return step;
    }
    public void setLiberalValues(int liberalValues) {
        this.liberalValues = liberalValues;
    }
    public void setStep(int step) {
        this.step = step;
    }
    public void setSkipping(int skipping) {this.skipping = skipping;}
    public int getSkipping() {return skipping;}

}
