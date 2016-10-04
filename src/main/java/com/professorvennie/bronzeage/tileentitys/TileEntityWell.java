package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:33 PM.
 */
public class TileEntityWell extends TileEntityBasicMachine implements IFluidHandler {

    public FluidTank tank;
    private int bottom, amountOfPipes = 0;

    public TileEntityWell() {
        super("Well");
        tank = new FluidTank(FluidRegistry.WATER, 0, 10000);
    }

    @Override
    public void update() {
        super.update();

        for (EnumFacing direction : EnumFacing.VALUES) {
            if (!worldObj.isRemote) {
                BlockPos pos = new BlockPos(getPos().getX() + direction.getFrontOffsetX(), getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ());
                if (worldObj.getTileEntity(pos) != null && worldObj.getTileEntity(pos) instanceof IFluidHandler) {
                    TileEntity tile = worldObj.getTileEntity(pos);
                    for (int i = 0; i < ((IFluidHandler) tile).getTankProperties().length; i++) {
                        IFluidTankProperties info = ((IFluidHandler) tile).getTankProperties()[i];
                        if (info != null && info.getContents() != null) {
                            int amount = info.getContents().amount;
                            int cap = info.getCapacity();
                            if (info.getContents().getFluid() == FluidRegistry.WATER) {
                                if (amount + 1 <= cap) {
                                    if (tank.getFluidAmount() >= 1) {
                                        tank.getFluid().amount -= 1;
                                        ((IFluidHandler) tile).fill(new FluidStack(FluidRegistry.WATER, 1), true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!worldObj.isRemote) {
            if (canWork) {
                if (inventory[2] != null && inventory[2].getItem() == Item.getItemFromBlock(ModBlocks.wellPipe)) {
                    bottom = inventory[2].stackSize;
                    for (int i = 1; i < getPos().getY(); i++) {
                        BlockPos pos = new BlockPos(getPos().getX(), getPos().getY() - 1, getPos().getZ());
                        if (inventory[2] != null) {
                            if (inventory[2].stackSize > 0) {
                                if (worldObj.getBlockState(pos).getBlock() != ModBlocks.wellPipe && worldObj.getBlockState(pos).getBlock() != Blocks.WATER) {
                                    worldObj.setBlockState(pos, ModBlocks.well.getDefaultState());
                                    amountOfPipes++;
                                    decrStackSize(2, 1);
                                }
                            } else {
                                worldObj.setBlockState(pos, Blocks.WATER.getDefaultState());
                            }

                            if (worldObj.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                                worldObj.setBlockState(pos, Blocks.WATER.getDefaultState());
                            }
                        } else {
                            if (worldObj.getBlockState(pos).getBlock() != Blocks.WATER)
                                worldObj.setBlockState(pos, Blocks.WATER.getDefaultState());
                        }
                    }
                    if (tank.getFluidAmount() < tank.getCapacity()) {
                        if (amountOfPipes > 0 && amountOfPipes <= 16) {
                            tank.getFluid().amount += amountOfPipes;
                        } else if (amountOfPipes > 16 && amountOfPipes <= 32) {
                            tank.getFluid().amount += amountOfPipes + 10;
                        } else if (amountOfPipes > 32 && amountOfPipes <= 48) {
                            tank.getFluid().amount += amountOfPipes + 20;
                        } else if (amountOfPipes > 48 && amountOfPipes < 64) {
                            tank.getFluid().amount += amountOfPipes + 30;
                        } else if (amountOfPipes == 64) {
                            tank.getFluid().amount += amountOfPipes + 64;
                        }
                    }

                    if (tank.getFluidAmount() > tank.getCapacity())
                        tank.getFluid().amount = tank.getCapacity();
                }
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) tank;
        return super.getCapability(capability, facing);
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return tank.getFluidAmount();
            case 1:
                return tank.getCapacity();
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                tank = new FluidTank(FluidRegistry.WATER, value, 10000);
                break;
            default:
                break;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        tank.readFromNBT(nbtTagCompound);
        bottom = nbtTagCompound.getInteger("bottom");
        amountOfPipes = nbtTagCompound.getInteger("numOfPipes");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        tank.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("bottom", bottom);
        nbtTagCompound.setInteger("numOfPipes", amountOfPipes);
        return nbtTagCompound;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    /*@Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
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
        return new FluidTankInfo[]{tank.getInfo()};
    }*/

    public int getAmountOfPipes() {
        return amountOfPipes;
    }

    public void setAmountOfPipes(int amountOfPipes) {
        this.amountOfPipes = amountOfPipes;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tank.getTankProperties();
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
