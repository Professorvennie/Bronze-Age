package com.professorvennie.bronzeage.api.wrench;

import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 12/1/2014 at 5:04 PM.
 */
public class WrenchMaterial {

    private int durability;
    private String name;
    private float numOfUses, usesPerRotation, usesPerDismantle;
    private ItemStack repairStack;

    public WrenchMaterial(String name, ItemStack reapairStack, int durability, float usesPerRotation, float usesPerDismantle) {
        this.name = name;
        this.durability = durability;
        this.numOfUses = durability / 100;
        this.usesPerRotation = usesPerRotation;
        this.usesPerDismantle = usesPerDismantle;
        this.repairStack = reapairStack;
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

    public ItemStack getRepairStack() {
        return repairStack;
    }
}
