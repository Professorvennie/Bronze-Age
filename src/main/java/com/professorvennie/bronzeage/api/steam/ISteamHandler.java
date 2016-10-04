package com.professorvennie.bronzeage.api.steam;


import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * Created by ProfessorVennie on 11/21/2014 at 8:05 PM.
 */
public interface ISteamHandler extends IFluidHandler {

     FluidTank getSteamTank();

     boolean canFill(EnumFacing direction, int amount);

     boolean canDrain(EnumFacing direction, int amount);

     void fill(EnumFacing direction, int amount);

     void drain(EnumFacing direction, int amount);

     int getSteamAmount();

     void setSteamAmount(int amount);

     int getSteamCapacity();
}
