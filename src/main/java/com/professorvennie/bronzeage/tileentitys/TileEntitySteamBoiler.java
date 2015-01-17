package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import com.professorvennie.bronzeage.api.tiles.ISteamTank;
import com.professorvennie.bronzeage.api.tiles.SteamTank;
import com.professorvennie.bronzeage.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:39 PM.
 */
public class TileEntitySteamBoiler extends TileEntityBasicMachine implements ISteamBoiler {

    public static final int FUELSLOT = 4, WATERSLOT = 2, STEAMEMPTYSLOT = 0;
    public int burnTime, currentItemBurnTime, temp, maxTemp = 500;
    private float efficiency;
    private SteamTank steamTank;
    private FluidTank waterTank;

    public TileEntitySteamBoiler() {
        super("container.SteamBoiler", 10000);
        efficiency = 0.5f;
        steamTank = new SteamTank(0, 10000);
        waterTank = new FluidTank(FluidRegistry.WATER, 0, 10000);
    }

    @Override
    public int getSizeInventory() {
        return 5;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (burnTime > 0) {
            burnTime--;
            //BlockSteamBoiler.updateBlockState(burnTime > 0, worldObj, xCoord, yCoord, zCoord, new ItemStack(ModBlocks.steamBoilerIdle, 1, 1), new ItemStack(ModBlocks.steamBoilerIdle, 1, 0));
        }

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

            if (canWork) {
                if (getWaterAmount() == 0 && temp >= 212) {
                    worldObj.createExplosion(null, (double) xCoord, (double) yCoord, (double) zCoord, 4.0f, true);
                }


                int time = (int) worldObj.getWorldTime() % 100;
                if (burnTime > 0) {
                    if (temp < maxTemp) {
                        if (time == 0)
                            temp++;
                    }
                } else if (time == 0) {
                    if (temp > 0)
                        temp--;
                }

                if (temp >= 212 && getWaterAmount() > 0) {
                    fill(null, 100);
                    drain(null, 100, true);
                }

                if (temp < maxTemp) {
                    if (burnTime == 0) {
                        currentItemBurnTime = burnTime = TileEntityFurnace.getItemBurnTime(inventory[4]) / (int) (efficiency * 2);
                        if (inventory[4] != null) {
                            if (TileEntityFurnace.isItemFuel(inventory[4])) {
                                inventory[4].stackSize--;
                                if (inventory[4].stackSize == 0)
                                    inventory[4] = new ItemStack(inventory[4].getItem().getContainerItem());
                            }
                        }
                    }
                } else if (temp == maxTemp) {
                    if (burnTime == 0) {
                        currentItemBurnTime = burnTime = TileEntityFurnace.getItemBurnTime(inventory[4]) * (int) (efficiency * 10);
                        if (inventory[4] != null) {
                            if (TileEntityFurnace.isItemFuel(inventory[4])) {
                                inventory[4].stackSize--;
                                if (inventory[4].stackSize == 0)
                                    inventory[4] = new ItemStack(inventory[4].getItem().getContainerItem());
                            }
                        }
                    }
                }
            }

            if (inventory[2] != null) {
                if (inventory[2].getItem() == Items.water_bucket) {
                    int temp = getWaterCapacity() - getWaterAmount();
                    if (inventory[3] == null) {
                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000) {
                                setInventorySlotContents(3, new ItemStack(Items.bucket));
                            }
                        }
                        if (getWaterAmount() < getWaterCapacity()) {
                            if (getWaterAmount() < getWaterCapacity()) {
                                if (temp >= 1000)
                                    waterTank.getFluid().amount += 1000;
                            }
                        }

                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000) {
                                inventory[2].stackSize--;
                                if (inventory[2].stackSize == 0)
                                    inventory[2] = null;
                            }
                        }

                    } else if (inventory[3].getItem() == Items.bucket) {
                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000) {
                                inventory[2].stackSize--;
                                if (inventory[2].stackSize == 0)
                                    inventory[2] = null;
                                inventory[3].stackSize++;
                            }
                        }

                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000)
                                waterTank.getFluid().amount += 1000;
                        }
                    }

                } else if (inventory[2].getItem() == Items.bucket) {
                    if (inventory[3] == null) {
                        if (getWaterAmount() >= 1000) {
                            waterTank.getFluid().amount -= 1000;
                            inventory[2].stackSize--;
                            if (inventory[2].stackSize == 0)
                                inventory[2] = null;
                            setInventorySlotContents(3, new ItemStack(Items.water_bucket));
                        }
                    }
                }
            }

            if (inventory[0] != null) {
                if (inventory[0].getItem() == Items.bucket) {
                    if (inventory[1] == null) {
                        if (getSteamAmount() >= 1000) {
                            drain(null, 1000);
                            inventory[0].stackSize--;
                            if (inventory[0].stackSize == 0)
                                inventory[0] = null;
                            setInventorySlotContents(1, new ItemStack(ModItems.wrench, 1, 5));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        this.temp = nbtTagCompound.getInteger("temp");
        this.efficiency = nbtTagCompound.getFloat("efficiency");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("temp", temp);
        nbtTagCompound.setFloat("efficiency", efficiency);
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

    public boolean isBurning() {
        return burnTime > 0;
    }

    public int getBurnTimeReamingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 100;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getTempScaled(int scale) {
        return temp * scale / maxTemp;
    }
}