package com.professorvennie.bronzeage.api.steam;


import net.minecraft.util.EnumFacing;

/**
 * Created by ProfessorVennie on 11/21/2014 at 8:05 PM.
 */
public interface ISteamHandler {

    public ISteamTank getSteamTank();

    public boolean canFill(EnumFacing direction, int amount);

    public boolean canDrain(EnumFacing direction, int amount);

    public void fill(EnumFacing direction, int amount);

    public void drain(EnumFacing direction, int amount);

    public int getSteamAmount();

    public void setSteamAmount(int amount);

    public int getSteamCapacity();
}
