package com.professorvennie.bronzeage.tileentitys;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:09 PM.
 */
public class TileEntitySteamWashPlant extends TileEntityBasicSteamMachine {

    public TileEntitySteamWashPlant() {
        super("steamWashPlant", 10000);
    }

    @Override
    public int getSizeInventory() {
        return 0;
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
