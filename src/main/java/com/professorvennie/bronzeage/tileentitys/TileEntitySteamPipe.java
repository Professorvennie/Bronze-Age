package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import com.professorvennie.bronzeage.blocks.fluids.ModFluids;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

/**
 * Created by ProfessorVennie on 10/21/2014 at 8:03 PM.
 */
public class TileEntitySteamPipe extends TileEntityMod implements ISteamHandler, ITickable {

    public EnumFacing[] connections = new EnumFacing[6];
    private FluidTank steamTank;

    public TileEntitySteamPipe() {
        super("steamCable");
        steamTank = new FluidTank(ModFluids.steam, 0, 10000);
    }

    @Override
    public void update() {
        for (EnumFacing direction : connections) {
            if (direction != null) {
                switch (direction) {
                    case UP:
                        BlockPos pos = new BlockPos(getPos().getX(), getPos().getY() + 1, getPos().getZ());
                        if (worldObj.getTileEntity(pos) instanceof ISteamBoiler) {
                            ISteamBoiler boiler = (ISteamBoiler) worldObj.getTileEntity(pos);
                            boiler.drain(direction, 100);
                            this.fill(direction, 100);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public FluidTank getSteamTank() {
        return steamTank;
    }

    @Override
    public boolean canFill(EnumFacing direction, int amount) {
        return false;
    }

    @Override
    public boolean canDrain(EnumFacing direction, int amount) {
        return false;
    }

    @Override
    public void fill(EnumFacing direction, int amount) {

    }

    @Override
    public void drain(EnumFacing direction, int amount) {

    }

    @Override
    public int getSteamAmount() {
        return 0;
    }

    @Override
    public void setSteamAmount(int amount) {

    }

    @Override
    public int getSteamCapacity() {
        return steamTank.getCapacity();
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }
}
