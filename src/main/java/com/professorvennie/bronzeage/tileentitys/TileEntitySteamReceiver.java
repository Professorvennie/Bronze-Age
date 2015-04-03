package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:09 PM.
 */
public class TileEntitySteamReceiver extends TileEntityBasicSteamMachine {

    public TileEntitySteamReceiver() {
        super("steamReceiver", 1000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        System.out.println(getSteamAmount());
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if(worldObj.getTileEntity(xCoord = direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof ISteamHandler){
                ISteamHandler steamHandler = (ISteamHandler)worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                if(steamHandler.getSteamAmount() + 100 <= steamHandler.getSteamCapacity() && getSteamAmount() >= 100){
                    steamHandler.fill(direction, 100);
                    drain(direction, 100);
                }
            }
        }
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
