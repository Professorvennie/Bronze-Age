package com.professorvennie.bronzeage.api.steam;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * Created by ProfessorVennie on 11/20/2014 at 6:59 PM.
 */
public interface ISteamBoiler extends ISteamHandler {

    public FluidTank getWaterTank();

    public int getWaterAmount();

    public int getWaterCapacity();

    public int getTemperature();

    public void setTemperature(int temperature);
}
