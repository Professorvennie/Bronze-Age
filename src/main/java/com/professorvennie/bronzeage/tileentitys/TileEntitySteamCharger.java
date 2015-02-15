package com.professorvennie.bronzeage.tileentitys;


import com.professorvennie.bronzeage.api.ISteamUsingItem;

/**
 * Created by ProfessorVennie on 2/15/2015 at 3:25 PM.
 */
public class TileEntitySteamCharger extends TileEntityBasicSteamMachine {

    public TileEntitySteamCharger() {
        super("steamCharger", 10000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(!worldObj.isRemote){
            if(getStackInSlot(2) != null && getStackInSlot(2).getItem() instanceof ISteamUsingItem){

            }
        }
    }

    @Override
    public int getSizeInventory() {
        return 4;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 1};
    }

    @Override
    public int[] getExportSlots() {
        return new int[]{2, 3};
    }
}
