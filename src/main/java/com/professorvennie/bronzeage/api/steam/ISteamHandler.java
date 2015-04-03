package com.professorvennie.bronzeage.api.steam;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 11/21/2014 at 8:05 PM.
 */
public interface ISteamHandler {

    public ISteamTank getSteamTank();

    public boolean canFill(ForgeDirection direction, int amount);

    public boolean canDrain(ForgeDirection direction, int amount);

    public void fill(ForgeDirection direction, int amount);

    public void drain(ForgeDirection direction, int amount);

    public int getSteamAmount();

    public int getSteamCapacity();
}
