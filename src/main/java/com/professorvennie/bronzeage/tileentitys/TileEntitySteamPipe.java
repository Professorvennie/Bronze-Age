package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import com.professorvennie.bronzeage.api.steam.ISteamTank;
import com.professorvennie.bronzeage.api.steam.SteamTank;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 10/21/2014 at 8:03 PM.
 */
public class TileEntitySteamPipe extends TileEntityMod implements ISteamHandler {

    public ForgeDirection[] connections = new ForgeDirection[6];
    private SteamTank steamTank;

    public TileEntitySteamPipe() {
        steamTank = new SteamTank(0, 1000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        for (ForgeDirection direction : connections) {
            if (direction != null) {
                switch (direction) {
                    case UP:
                        if (worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof ISteamBoiler) {
                            ISteamBoiler boiler = (ISteamBoiler) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
                            boiler.drain(direction, 100);
                            this.fill(direction, 100);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public ISteamTank getSteamTank() {
        return steamTank;
    }

    @Override
    public boolean canFill(ForgeDirection direction, int amount) {
        return steamTank.getAmount() + amount <= steamTank.getCapacity();
    }

    @Override
    public boolean canDrain(ForgeDirection direction, int amount) {
        return steamTank.getAmount() - amount >= 0;
    }

    @Override
    public void fill(ForgeDirection direction, int amount) {
        if (canFill(direction, amount))
            steamTank.fill(amount);
    }

    @Override
    public void drain(ForgeDirection direction, int amount) {
        if (canDrain(direction, amount))
            steamTank.drain(amount);
    }

    @Override
    public int getSteamAmount() {
        return steamTank.getAmount();
    }

    @Override
    public int getSteamCapacity() {
        return steamTank.getCapacity();
    }
}
