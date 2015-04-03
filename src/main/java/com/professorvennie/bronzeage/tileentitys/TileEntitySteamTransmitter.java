package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.api.steam.SteamNetwork;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:07 PM.
 */
public class TileEntitySteamTransmitter extends TileEntityBasicSteamMachine {

    public int range, transmitAmount;
    private List<TileEntitySteamReceiver> receivers = new ArrayList<TileEntitySteamReceiver>();
    public UUID owner;

    public TileEntitySteamTransmitter() {
        super("steamTransmitter", 10000);
        range = 32;
        transmitAmount = 100;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(!worldObj.isRemote) {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                // System.out.println(worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ));
                //System.out.println("X: " + direction.offsetX + " Y: " + direction.offsetY + " Z: " + direction.offsetZ);
                //System.out.println("TRUE");
                TileEntity tileEntity = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                if (tileEntity instanceof ISteamBoiler) {
                    //System.out.println(tileEntity);
                    ISteamBoiler steamBoiler = (ISteamBoiler) tileEntity;
                    if (steamBoiler.getSteamAmount() >= transmitAmount && getSteamAmount() + transmitAmount <= getSteamCapacity()) {
                        steamBoiler.drain(direction, transmitAmount);
                        fill(direction, transmitAmount);
                    }
                }
            }
        }

        if (!worldObj.isRemote) {
             for (int i = 0; i < range * 2 + 1; i++) {
                for (int j = 0; j < range * 2 + 1; j++) {
                    for (int k = 0; k < range * 2 + 1; k++) {
                        int x = xCoord + i - range;
                        int y = yCoord + j - range;
                        int z = zCoord + k - range;
                        //System.out.println("X: " + x + "Y: " + y + "Z: " + z);

                        if(worldObj.getTileEntity(x, y, z) instanceof TileEntitySteamReceiver) {
                            TileEntitySteamReceiver receiver = (TileEntitySteamReceiver)worldObj.getTileEntity(x, y, z);
                            if(receiver.getSteamAmount() + transmitAmount <= receiver.getSteamCapacity() && getSteamAmount() - transmitAmount >= getSteamCapacity()){
                                receiver.fill(ForgeDirection.UP, transmitAmount);
                                drain(ForgeDirection.DOWN, transmitAmount);
                            }
                        }
                    }
                }
            }
        }
    }

    public void deregisterWithField()
    {
        if (worldObj != null && !worldObj.isRemote)
        {
            SteamNetwork.removeLink(this);
        }
    }

    public void registerWithField()
    {
        if (worldObj != null && !worldObj.isRemote)
        {
            SteamNetwork.registerLink(this);
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

    public String getOwner(){
        if (this.owner == null)
            return null;
        return this.owner.toString();
    }
}
