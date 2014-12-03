package com.professorvennie.bronzeage.api.tiles;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Created by ProfessorVennie on 11/20/2014 at 6:59 PM.
 */
public interface ISteamBoiler extends IFluidHandler, ISteamHandler {

    public FluidTank getWaterTank();

    public int getWaterAmount();

    public int getWaterCapacity();
}
