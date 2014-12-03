package com.professorvennie.bronzeage.api.tiles;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by ProfessorVennie on 11/20/2014 at 6:59 PM.
 */
public interface ISteamTank {

    public int getAmount();

    public int getCapacity();

    public void fill(int amount);

    public void drain(int amount);

    public void readFromNBT(NBTTagCompound nbtTagCompound);

    public void writeToNBT(NBTTagCompound nbtTagCompound);
}
