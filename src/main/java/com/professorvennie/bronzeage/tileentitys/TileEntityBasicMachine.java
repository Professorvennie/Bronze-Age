package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.api.tiles.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public abstract class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements ISteamHandler, IButtonHandler, IRedstoneControlable, ISideConfigurable {

    public boolean canWork;
    private SteamTank steamTank;
    private RedstoneMode redStoneMode;
    private Map<Integer, SideMode> sideModeMap = new HashMap<Integer, SideMode>();
    private int[] inputSlots, exportSlots;

    public TileEntityBasicMachine(String name, int capacity) {
        super(name);
        steamTank = new SteamTank(0, capacity);
        redStoneMode = RedstoneMode.low;
        this.inputSlots = setInputSlots();
        this.exportSlots = setExportSlots();
        sideModeMap.put(0, SideMode.IMPORT);
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

    @Override
    public SideMode getModeOnSide(int side) {
        return sideModeMap.get(side);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        switch (getModeOnSide(side)) {
            case IMPORT:
                return inputSlots;
            case EXPORT:
                return exportSlots;
            case DISABLED:
                return null;
            default:
                return null;
        }
    }

    @Override
    public abstract int getSizeInventory();

    public abstract int[] setInputSlots();

    public abstract int[] setExportSlots();
}
