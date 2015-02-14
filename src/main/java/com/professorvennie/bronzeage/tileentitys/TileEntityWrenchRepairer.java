package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.wrench.IWrench;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:30 PM.
 */
public class TileEntityWrenchRepairer extends TileEntityBasicSteamMachine {

    public TileEntityWrenchRepairer() {
        super("wrenchRepairer", 10000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(!worldObj.isRemote){
            if(getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof IWrench){
                //System.out.println(((IWrench)getStackInSlot(0).getItem()).getWrenchMaterial(getStackInSlot(0)).getRepairStack().getUnlocalizedName());
                if(getStackInSlot(1) != null && getStackInSlot(1).getItem() == ((IWrench)getStackInSlot(0).getItem()).getWrenchMaterial(getStackInSlot(0)).getRepairStack().getItem()){
                    if(getStackInSlot(0).getTagCompound().getBoolean("isBroken") && getStackInSlot(2) == null){
                        ItemStack wrench = getStackInSlot(0);
                        wrench.getTagCompound().setBoolean("isBroken", false);
                        wrench.getTagCompound().setFloat("NumOfUsesRemaining", ((IWrench)getStackInSlot(0).getItem()).getWrenchMaterial(getStackInSlot(0)).getNumOfUses());
                        setInventorySlotContents(2, wrench);
                        setInventorySlotContents(0, null);
                        if(getStackInSlot(1).stackSize > 1)
                            getStackInSlot(1).stackSize--;
                        else
                            setInventorySlotContents(1, null);

                    }
                }
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getExportSlots() {
        return new int[0];
    }
}
