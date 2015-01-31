package com.professorvennie.bronzeage.tileentitys;

/**
 * Created by ProfessorVennie on 1/7/2015 at 7:02 PM.
 */
public class TileEntitySteamFurnace extends TileEntityBasicSteamMachine {

    public int burnTime, currentItemBurnTime, cookTime;

    public TileEntitySteamFurnace() {
        super("steamFurnace", 10000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (canWork) {

        }
    }

    @Override
    public int getSizeInventory() {
        return 6;
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
