package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import com.professorvennie.bronzeage.api.tiles.ISteamTank;
import com.professorvennie.bronzeage.api.tiles.SteamTank;
import com.professorvennie.bronzeage.api.tilesenties.IButtonHandler;
import com.professorvennie.bronzeage.api.tilesenties.IRedstoneControlable;
import com.professorvennie.bronzeage.client.gui.buttons.RedstoneMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements ISteamHandler, IButtonHandler, IRedstoneControlable {

    public boolean canWork;
    private SteamTank steamTank;
    private RedstoneMode redStoneMode;


    public TileEntityBasicMachine(String name, int capacity) {
        super(name);
        steamTank = new SteamTank(0, capacity);
        redStoneMode = RedstoneMode.low;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        TileEntity[] tiles = new TileEntity[6];
        tiles[0] = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
        tiles[1] = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
        tiles[2] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        tiles[3] = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        tiles[4] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
        tiles[5] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);

        for (TileEntity tile : tiles) {
            if (!worldObj.isRemote) {
                if (tile != null && tile instanceof ISteamHandler) {
                    if (tile instanceof ISteamBoiler)
                        return;

                    int tankAmount = ((ISteamHandler) tile).getSteamAmount();
                    int tankCap = ((ISteamHandler) tile).getSteamCapacity();
                    if (tankAmount < getSteamAmount()) {
                        if (tankAmount < tankCap) {
                            ((ISteamHandler) tile).fill(null, 5);
                            drain(null, -5);
                        }
                    }
                }
            }
        }

        switch (redStoneMode) {
            case low:
                if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
                    canWork = true;
                else
                    canWork = false;
                break;
            case high:
                if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
                    canWork = true;
                else
                    canWork = false;
                break;
            case disabled:
                canWork = true;
                break;
            default:
                canWork = false;
                break;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        redStoneMode = RedstoneMode.values()[nbtTagCompound.getInteger("Mode")];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Mode", redStoneMode.ordinal());
    }

    //ISteamHandler
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

    @Override
    public void handleClick(int id) {
        switch (id) {
            case 0:
                setRedstoneMode(RedstoneMode.high);
                break;
            case 1:
                setRedstoneMode(RedstoneMode.disabled);
                break;
            case 2:
                setRedstoneMode(RedstoneMode.low);
                break;
        }
    }

    @Override
    public RedstoneMode getRedStoneMode() {
        return redStoneMode;
    }

    @Override
    public void setRedstoneMode(RedstoneMode mode) {
        this.redStoneMode = mode;
    }
}
