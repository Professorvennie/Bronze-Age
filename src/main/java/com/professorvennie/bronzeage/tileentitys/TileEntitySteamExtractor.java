package com.professorvennie.bronzeage.tileentitys;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:11 PM.
 */
public class TileEntitySteamExtractor extends TileEntityBasicSteamMachine implements IFluidHandler {

    public FluidTank tank;

    public TileEntitySteamExtractor() {
        super("steamExtractor", 10000);
        tank = new FluidTank(10000);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getExportSlots() {
        return new int[0];
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        tank.fill(resource, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        tank.drain(maxDrain, doDrain);
        return null;
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }
}