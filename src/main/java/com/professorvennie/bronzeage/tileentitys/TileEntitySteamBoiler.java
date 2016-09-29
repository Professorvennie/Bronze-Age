package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.core.network.MessageUpdate;
import com.professorvennie.bronzeage.core.network.PacketHandler;
import com.professorvennie.bronzeage.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:39 PM.
 */
public class TileEntitySteamBoiler extends TileEntityBasicSteamMachine implements ISteamBoiler {

    public static final int FUELSLOT = 4, WATERSLOT = 2, STEAMEMPTYSLOT = 0;
    public int burnTime, currentItemBurnTime, temp, maxTemp = 500;
    private FluidTank waterTank;

    public TileEntitySteamBoiler() {
        super("container.SteamBoiler", 10000);
        waterTank = new FluidTank(FluidRegistry.WATER, 0, 10000);
    }

    @Override
    public int getSizeInventory() {
        return 5;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 4, 2};
    }

    @Override
    public int[] getExportSlots() {
        return new int[]{1, 3};
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot == 4 && TileEntityFurnace.isItemFuel(itemStack))
            return true;
        return false;
    }

    @Override
    public void update() {
        super.update();

        if (burnTime > 0) {
            burnTime--;
            isActive = true;
            //markDirty();
            markDirtyClient();
        }else {
            isActive = false;
            //markDirty();
            markDirtyClient();
        }

            //System.out.println("SteamAmount: " + getSteamAmount() + "  WaterAmount: " + getWaterAmount() + "  BurnAmount: " + burnTime + "  Temp: " + temp);
        //if (!worldObj.isRemote) {
            PacketHandler.INSTANCE.sendToServer(new MessageUpdate(pos.getX(), pos.getY(), pos.getZ(), getWaterAmount(), getSteamAmount(), burnTime, temp));
            worldObj.updateBlockTick(pos, worldObj.getBlockState(pos).getBlock(), 1, 10);
            if (burnTime > 0) {
                isActive = true;
            }else {
                isActive = false;
            }

            if (inventory[4] != null && inventory[4].getItem() == Items.DIAMOND && waterTank.getFluid() != null){
                temp = 213;
                waterTank.getFluid().amount = 10000;
            }else if (waterTank.getFluid() == null){
                waterTank = new FluidTank(FluidRegistry.WATER, 1000, 1000);
            }

            if (waterTank.getFluidAmount() > waterTank.getCapacity())
                waterTank.getFluid().amount = waterTank.getCapacity();

            if (canWork) {
                if (getWaterAmount() == 0 && temp >= 212) {
                    worldObj.createExplosion(null, (double) pos.getZ(), (double) pos.getY(), (double) pos.getZ(), 4.0f, true);
                }


                int time = (int) worldObj.getWorldTime() % 85;
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
                    if(getSteamTank().getAmount() + 100 <= getSteamTank().getCapacity()) {
                        getSteamTank().fill(100);
                        drain(new FluidStack(FluidRegistry.WATER, 100), true);
                    }
                }

                if (temp < maxTemp) {
                    if (burnTime == 0) {
                        currentItemBurnTime = burnTime = TileEntityFurnace.getItemBurnTime(inventory[4]) / 2;
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
                        currentItemBurnTime = burnTime = TileEntityFurnace.getItemBurnTime(inventory[4]) * 10;
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
                if (inventory[2].getItem() == Items.WATER_BUCKET) {
                    int temp = getWaterCapacity() - getWaterAmount();
                    if (inventory[3] == null) {
                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000) {
                                setInventorySlotContents(3, new ItemStack(Items.BUCKET));
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

                    } else if (inventory[3].getItem() == Items.BUCKET) {
                        if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000) {
                                inventory[2].stackSize--;
                                if (inventory[2].stackSize == 0)
                                    inventory[2] = null;
                                inventory[3].stackSize++;
                            }
                        }

                       /* if (getWaterAmount() < getWaterCapacity()) {
                            if (temp >= 1000)
                                waterTank.getFluid().amount += 1000;
                        }*/
                    }

                } else if (inventory[2].getItem() == Items.BUCKET) {
                    if (inventory[3] == null) {
                        if (getWaterAmount() >= 1000) {
                            waterTank.getFluid().amount -= 1000;
                            inventory[2].stackSize--;
                            if (inventory[2].stackSize == 0)
                                inventory[2] = null;
                            setInventorySlotContents(3, new ItemStack(Items.WATER_BUCKET));
                        }
                    }
                }
            }

            if (inventory[0] != null) {
                if (inventory[0].getItem() == Items.BUCKET) {
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
        //}
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 3:
                return burnTime;
            case 4:
                return currentItemBurnTime;
            case 5:
                return temp;
            case 6:
                return maxTemp;
            case 7:
                return getWaterAmount();
            default:
                return super.getField(id);
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 3:
                burnTime = value;
                break;
            case 4:
                currentItemBurnTime = value;
                break;
            case 5:
                temp = value;
                break;
            case 7:
                waterTank.getFluid().amount += value;
                break;
            default:
                super.setField(id, value);
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return super.getFieldCount() + 5;
    }

    @Override
    public void clear() {
        super.clear();
        drain(null, getSteamAmount());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        this.temp = nbtTagCompound.getInteger("temp");
        this.waterTank.readFromNBT(nbtTagCompound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("temp", temp);
        this.waterTank.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public FluidTank getWaterTank() {
        return waterTank;
    }

    @Override
    public int getWaterAmount() {
        if (waterTank.getFluid() != null)
            return waterTank.getFluid().amount;
        else
            return 0;
    }

    @Override
    public int getTemperature() {
        return temp;
    }

    @Override
    public void setTemperature(int temperature) {
        this.temp = temperature;
    }

    @Override
    public int getWaterCapacity() {
        return waterTank.getCapacity();
    }

//    @Override
//    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
//        waterTank.fill(resource, doFill);
//        return 0;
//    }
//
//    @Override
//    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
//        return drain(from, resource.amount, doDrain);
//    }
//
//    @Override
//    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
//        waterTank.drain(maxDrain, doDrain);
//        return null;
//    }
//
//    @Override
//    public FluidTankInfo[] getTankInfo(EnumFacing from) {
//        return new FluidTankInfo[]{waterTank.getInfo()};
//    }

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

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return waterTank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        waterTank.fill(resource, doFill);
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        waterTank.drain(resource, doDrain);
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) waterTank;
        return super.getCapability(capability, facing);
    }
}