package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.Mod;

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
                    for (int i = 0; i < ((IFluidHandler) tile).getTankInfo(EnumFacing.UP).length; i++) {
                        FluidTankInfo info = ((IFluidHandler) tile).getTankInfo(EnumFacing.UP)[i];
                        if (info != null && info.fluid != null) {
                            int amount = info.fluid.amount;
                            int cap = info.capacity;
                            if (info.fluid.getFluid() == FluidRegistry.WATER) {
                                if (amount + 1 <= cap) {
                                    if (tank.getFluidAmount() >= 1) {
                                        tank.getFluid().amount -= 1;
                                        ((IFluidHandler) tile).fill(EnumFacing.UP, new FluidStack(FluidRegistry.WATER, 1), true);
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

    /*@Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
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
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }
}
