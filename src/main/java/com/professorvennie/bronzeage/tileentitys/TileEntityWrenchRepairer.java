package com.professorvennie.bronzeage.tileentitys;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:30 PM.
 */
public class TileEntityWrenchRepairer extends TileEntityBasicSteamMachine {

    public TileEntityWrenchRepairer() {
        super("wrenchRepairer", 10000);
    }

    @Override
    public int getSizeInventory() {
        return 5;
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
