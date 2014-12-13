package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import com.professorvennie.bronzeage.api.tiles.ISteamTank;
import com.professorvennie.bronzeage.api.tiles.SteamTank;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements ISteamBoiler {

    private SteamTank steamTank;
    private FluidTank waterTank;

    public TileEntityBasicMachine(String name, int capacity) {
        super(name);
        steamTank = new SteamTank(0, capacity);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        TileEntity[] tiles = new TileEntity[6];
        tiles[0] = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
        tiles[1] = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
        tiles[2] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        tiles[3] = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        tiles[4] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
        tiles[5] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);

        for (TileEntity tile : tiles) {
            if (!worldObj.isRemote) {
                if (tile != null && tile instanceof ISteamHandler) {
                    if (tile instanceof ISteamBoiler)
                        return;

                    int tankAmount = ((ISteamHandler) tile).getSteamAmount();
                    int tankCap = ((ISteamHandler) tile).getSteamCapacity();
                    if (tankAmount < getSteamAmount()) {
                        if (tankAmount < tankCap) {
                            ((ISteamHandler) tile).fill(null, 5);
                            drain(null, -5);
                        }
                    }
                }
            }
        }

        if (waterTank.getFluid().amount > waterTank.getCapacity())
            waterTank.getFluid().amount = waterTank.getCapacity();
    }

    //ISteamHandler
    @Override
    public ISteamTank getSteamTank() {
        return steamTank;
    }

    @Override
    public boolean canFill(ForgeDirection direction, int amount) {
        return steamTank.getAmount() + amount <= steamTank.getCapacity();
    }

    @Override
    public boolean canDrain(ForgeDirection direction, int amount) {
        return steamTank.getAmount() - amount >= 0;
    }

    @Override
    public void fill(ForgeDirection direction, int amount) {
        if (canFill(direction, amount))
            steamTank.fill(amount);
    }

    @Override
    public void drain(ForgeDirection direction, int amount) {
        if (canDrain(direction, amount))
            steamTank.drain(amount);
    }

    @Override
    public int getSteamAmount() {
        return steamTank.getAmount();
    }

    @Override
    public int getSteamCapacity() {
        return steamTank.getCapacity();
    }

    //ISteamBoiler
    @Override
    public FluidTank getWaterTank() {
        return waterTank;
    }

    @Override
    public int getWaterAmount() {
        return waterTank.getFluidAmount();
    }

    @Override
    public int getWaterCapacity() {
        return waterTank.getCapacity();
    }

    //IFluidHandler
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{waterTank.getInfo()};
    }
}
