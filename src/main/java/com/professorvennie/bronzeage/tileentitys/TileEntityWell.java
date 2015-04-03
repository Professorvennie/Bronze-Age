package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

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
    public void updateEntity() {
        super.updateEntity();

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (!worldObj.isRemote) {
                if (worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) != null && worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof IFluidHandler) {
                   TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                   for (int i = 0; i < ((IFluidHandler) tile).getTankInfo(ForgeDirection.UP).length; i++) {
                       FluidTankInfo info = ((IFluidHandler) tile).getTankInfo(ForgeDirection.UP)[i];
                       if (info != null) {
                           int amount = info.fluid.amount;
                           int cap = info.capacity;
                           if (info.fluid.getFluid() == FluidRegistry.WATER) {
                               if (amount + 1 <= cap) {
                                   if (tank.getFluidAmount() >= 1) {
                                       tank.getFluid().amount -= 1;
                                       ((IFluidHandler) tile).fill(ForgeDirection.UP, new FluidStack(FluidRegistry.WATER, 1), true);
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
                    for (int i = 1; i < yCoord; i++) {
                        if (inventory[2] != null) {
                            if (inventory[2].stackSize > 0) {
                                if (worldObj.getBlock(xCoord, yCoord - i, zCoord) != ModBlocks.wellPipe && worldObj.getBlock(xCoord, yCoord - i, zCoord) != Blocks.water) {
                                    worldObj.setBlock(xCoord, yCoord - i, zCoord, ModBlocks.wellPipe);
                                    amountOfPipes++;
                                    decrStackSize(2, 1);
                                }
                            } else {
                                worldObj.setBlock(xCoord, yCoord - i, zCoord, Blocks.water);
                            }

                            if (worldObj.getBlock(xCoord, yCoord - i - 1, zCoord) == Blocks.bedrock) {
                                worldObj.setBlock(xCoord, yCoord - i, zCoord, Blocks.water);
                            }
                        }else {
                            if (worldObj.getBlock(xCoord, yCoord - i, zCoord) != Blocks.water)
                            worldObj.setBlock(xCoord, yCoord - i, zCoord, Blocks.water);
                        }
                    }
                }
                if (tank.getFluidAmount() < tank.getCapacity()) {
                    if (amountOfPipes > 0 && amountOfPipes <= 16) {
                        tank.getFluid().amount += amountOfPipes;
                    } else if (amountOfPipes > 16 && amountOfPipes <= 32) {
                        tank.getFluid().amount += amountOfPipes + 10;
                    } else if (amountOfPipes > 32 && amountOfPipes <= 48) {
                        tank.getFluid().amount += amountOfPipes + 20;
                    } else if (amountOfPipes > 48 && amountOfPipes <= 64) {
                        tank.getFluid().amount += amountOfPipes + 30;
                    }
                }

                if (tank.getFluidAmount() > tank.getCapacity())
                    tank.getFluid().amount = tank.getCapacity();
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
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        tank.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("bottom", bottom);
        nbtTagCompound.setInteger("numOfPipes", amountOfPipes);
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

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
        return new FluidTankInfo[]{tank.getInfo()};
    }

    public int getAmountOfPipes() {
        return amountOfPipes;
    }

    public void setAmountOfPipes(int amountOfPipes) {
        this.amountOfPipes = amountOfPipes;
    }
}
