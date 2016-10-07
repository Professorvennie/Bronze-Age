package com.professorvennie.bronzeage.api.steam;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * Created by ProfessorVennie on 11/20/2014 at 6:59 PM.
 */
public interface ISteamBoiler extends ISteamHandler {

    FluidTank getWaterTank();

    int getWaterAmount();

    int getWaterCapacity();

    int getTemperature();

    void setTemperature(int temperature);
}
