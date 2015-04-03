package com.professorvennie.bronzeage.tileentitys;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:12 PM.
 */
public class TileEntitySteamGrinder extends TileEntityBasicSteamMachine {

    public TileEntitySteamGrinder() {
        super("steamGrinder", 10000);
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
