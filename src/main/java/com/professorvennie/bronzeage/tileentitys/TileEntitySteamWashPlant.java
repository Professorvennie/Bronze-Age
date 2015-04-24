package com.professorvennie.bronzeage.tileentitys;

import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:09 PM.
 */
public class TileEntitySteamWashPlant extends TileEntityBasicSteamMachine {

    public TileEntitySteamWashPlant() {
        super("steamWashPlant", 10000);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return false;
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
