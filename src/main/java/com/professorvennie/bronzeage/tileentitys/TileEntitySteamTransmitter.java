package com.professorvennie.bronzeage.tileentitys;


import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:07 PM.
 */
public class TileEntitySteamTransmitter extends TileEntityBasicSteamMachine {

    public TileEntitySteamTransmitter() {
        super("steamTransmitter", 10000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
            if(worldObj.getTileEntity(xCoord = direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof TileEntitySteamBoiler){
                TileEntitySteamBoiler steamBoiler = (TileEntitySteamBoiler)worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                if(steamBoiler.getSteamAmount() >= 100 && getSteamAmount() + 100 <= getSteamCapacity()){
                    steamBoiler.drain(direction, 100);
                    fill(direction, 100);
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
