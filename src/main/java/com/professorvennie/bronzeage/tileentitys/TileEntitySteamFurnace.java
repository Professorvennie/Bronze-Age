package com.professorvennie.bronzeage.tileentitys;


/**
 * Created by ProfessorVennie on 1/7/2015 at 7:02 PM.
 */
public class TileEntitySteamFurnace extends TileEntityBasicMachine {

    public TileEntitySteamFurnace() {
        super("steamFurnace", 10000);
    }

    @Override
    public int getSizeInventory() {
        return 5;
    }

    @Override
    public int[] setInputSlots() {
        return new int[]{};
    }

    @Override
    public int[] setExportSlots() {
        return new int[]{};
    }
}
