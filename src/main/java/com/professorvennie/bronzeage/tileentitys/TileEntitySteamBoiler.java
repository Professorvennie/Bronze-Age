package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import com.professorvennie.bronzeage.api.tiles.ISteamTank;
import com.professorvennie.bronzeage.api.tiles.SteamTank;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:39 PM.
 */
public class TileEntitySteamBoiler extends TileEntityBasicSidedInventory implements ISteamBoiler {

    public int burnTime, currentItemBurnTime;
    private SteamTank steamTank;
    private FluidTank waterTank;

    public TileEntitySteamBoiler() {
        super("container.SteamBoiler");
        steamTank = new SteamTank(0, 10000);
        waterTank = new FluidTank(FluidRegistry.WATER, 0, 10000);
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

        if (!worldObj.isRemote) {
            for (TileEntity tile : tiles) {
                if (tile != null) {
                    if (tile instanceof ISteamHandler) {
                        int furnaceAmount = ((ISteamHandler) tile).getSteamAmount();
                        int capactiy = ((ISteamHandler) tile).getSteamCapacity();
                        if (furnaceAmount < capactiy) {
                            if (this.canDrain(null, 100)) {
                                this.drain(null, 100);
                                ((ISteamHandler) tile).fill(null, 100);
                            }
                        }
                    }
                }
            }

            if (waterTank.getFluidAmount() > waterTank.getCapacity())
                waterTank.getFluid().amount = waterTank.getCapacity();

            if (waterTank.getFluid().getFluid() == FluidRegistry.WATER && waterTank.getFluid().amount > 100) {

            }
        }
    }

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

    @Override
    public FluidTank getWaterTank() {
        return waterTank;
    }

    @Override
    public int getWaterAmount() {
        return waterTank.getFluid().amount;
    }

    @Override
    public int getWaterCapacity() {
        return waterTank.getCapacity();
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        waterTank.fill(resource, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        waterTank.drain(maxDrain, doDrain);
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{waterTank.getInfo()};
    }
}
