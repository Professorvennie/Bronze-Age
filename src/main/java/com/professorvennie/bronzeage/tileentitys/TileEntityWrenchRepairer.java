package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.wrench.IWrench;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:30 PM.
 */
public class TileEntityWrenchRepairer extends TileEntityBasicSteamMachine {

    public TileEntityWrenchRepairer() {
        super("wrenchRepairer", 10000);
        setMachineSpeed(100);
    }

    @Override
    public void update() {
        super.update();
        if(!worldObj.isRemote){
            if(getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof IWrench){
                if(getStackInSlot(1) != null && getStackInSlot(1).getItem() == ((IWrench)getStackInSlot(0).getItem()).getWrenchMaterial(getStackInSlot(0)).getRepairStack().getItem()){
                    if(getStackInSlot(0).getTagCompound().getBoolean("isBroken") && getStackInSlot(2) == null){
                        if (getProgress() == getMachineSpeed()){
                            setProgress(0);
                            ItemStack wrench = getStackInSlot(0);
                            wrench.getTagCompound().setBoolean("isBroken", false);
                            wrench.getTagCompound().setFloat("NumOfUsesRemaining", ((IWrench) getStackInSlot(0).getItem()).getWrenchMaterial(getStackInSlot(0)).getNumOfUses());
                            setInventorySlotContents(2, wrench);
                            setInventorySlotContents(0, null);
                            if (getStackInSlot(1).stackSize > 1)
                                getStackInSlot(1).stackSize--;
                            else
                                setInventorySlotContents(1, null);
                        }else
                            setProgress(getProgress() + 1);
                    }
                }
            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if(slot == 0 && itemStack.getItem() instanceof IWrench)
            return true;
        else if(slot == 1)
            return true;
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 1};
    }

    @Override
    public int[] getExportSlots() {
        return new int[]{2};
    }
}
