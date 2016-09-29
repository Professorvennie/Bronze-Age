package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import com.professorvennie.bronzeage.api.steam.ISteamTank;
import com.professorvennie.bronzeage.api.steam.SteamTank;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * Created by ProfessorVennie on 10/21/2014 at 8:03 PM.
 */
public class TileEntitySteamPipe extends TileEntityMod implements ISteamHandler, ITickable {

    public EnumFacing[] connections = new EnumFacing[6];
    private SteamTank steamTank;

    public TileEntitySteamPipe() {
        steamTank = new SteamTank(0, 1000);
    }

    @Override
    public void update() {
        for (EnumFacing direction : connections) {
            if (direction != null) {
                switch (direction) {
                    case UP:
                        BlockPos pos = new BlockPos(getPos().getX(), getPos().getY() + 1, getPos().getZ());
                        if (worldObj.getTileEntity(pos) instanceof ISteamBoiler) {
                            ISteamBoiler boiler = (ISteamBoiler) worldObj.getTileEntity(pos);
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
    public boolean canFill(EnumFacing direction, int amount) {
        return steamTank.getAmount() + amount <= steamTank.getCapacity();
    }

    @Override
    public boolean canDrain(EnumFacing direction, int amount) {
        return steamTank.getAmount() - amount >= 0;
    }

    @Override
    public void fill(EnumFacing direction, int amount) {
        if (canFill(direction, amount))
            steamTank.fill(amount);
    }

    @Override
    public void drain(EnumFacing direction, int amount) {
        if (canDrain(direction, amount))
            steamTank.drain(amount);
    }

    @Override
    public int getSteamAmount() {
        return steamTank.getAmount();
    }

    @Override
    public void setSteamAmount(int amount) {

    }

    @Override
    public int getSteamCapacity() {
        return steamTank.getCapacity();
    }
}
