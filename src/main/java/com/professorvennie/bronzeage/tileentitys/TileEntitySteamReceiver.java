package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:09 PM.
 */
public class TileEntitySteamReceiver extends TileEntityBasicSteamMachine {

    public TileEntitySteamReceiver() {
        super("steamReceiver", 1000);
    }

    @Override
    public void update() {
        super.update();
        //// TODO: 9/12/2016 finish this
        System.out.println(getSteamAmount());
       /* for(EnumFacing direction : EnumFacing.VALID_DIRECTIONS) {
            if(worldObj.getTileEntity(xCoord = direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof ISteamHandler){
                ISteamHandler steamHandler = (ISteamHandler)worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                if(steamHandler.getSteamAmount() + 100 <= steamHandler.getSteamCapacity() && getSteamAmount() >= 100){
                    steamHandler.fill(direction, 100);
                    drain(direction, 100);
                }
            }
        }*/
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
