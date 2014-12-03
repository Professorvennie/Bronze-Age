package com.professorvennie.bronzeage.api.tiles;


import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by ProfessorVennie on 11/20/2014 at 7:07 PM.
 */
public class SteamTank implements ISteamTank {

    private final int capacity;
    private int steamAmount;

    public SteamTank(int steamAmount, int capacity) {
        this.steamAmount = steamAmount;
        this.capacity = capacity;
    }

    @Override
    public int getAmount() {
        return steamAmount;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void fill(int amount) {
        if (steamAmount + amount <= capacity)
            steamAmount += amount;
    }

    @Override
    public void drain(int amount) {
        if (steamAmount - amount >= 0)
            steamAmount -= amount;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        this.steamAmount = nbtTagCompound.getInteger("SteamAmount");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("SteamAmount", this.steamAmount);
    }
}
