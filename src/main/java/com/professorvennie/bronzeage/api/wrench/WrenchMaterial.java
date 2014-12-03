package com.professorvennie.bronzeage.api.wrench;

/**
 * Created by ProfessorVennie on 12/1/2014 at 5:04 PM.
 */
public class WrenchMaterial {

    private int durability;
    private String name;
    private float numOfUses, usesPerRotation, usesPerDismantle;

    public WrenchMaterial(String name, int durability, float usesPerRotation, float usesPerDismantle) {
        this.name = name;
        this.durability = durability;
        this.numOfUses = durability / 100;
        this.usesPerRotation = usesPerRotation;
        this.usesPerDismantle = usesPerDismantle;
    }

    public String getName() {
        return name;
    }

    public float getNumOfUses() {
        return numOfUses;
    }

    public float getUsesPerDismantle() {
        return usesPerDismantle;
    }

    public float getUsesPerRotation() {
        return usesPerRotation;
    }

    public int getDurability() {
        return durability;
    }

    public void addUse(float amountOfUses) {
        this.numOfUses += amountOfUses;
    }

    public void subtractUse(float amountOfUses) {
        this.numOfUses -= amountOfUses;
    }
}
