package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.api.tiles.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public abstract class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements ISteamHandler, IButtonHandler, IRedstoneControlable, ISideConfigurable {

    public boolean canWork;
    protected SideMode[] sideModesSlots, sideModeTanks;
    private SteamTank steamTank;
    private RedstoneMode redStoneMode;
    private int[] inputSlots, exportSlots;

    public TileEntityBasicMachine(String name, int capacity) {
        super(name);
        steamTank = new SteamTank(0, capacity);
        redStoneMode = RedstoneMode.low;
        inputSlots = getInputSlots();
        exportSlots = getExportSlots();

        sideModesSlots = new SideMode[6];
        for (int i = 0; i < sideModesSlots.length; i++)
            sideModesSlots[i] = SideMode.IMPORT;
        sideModesSlots[ForgeDirection.DOWN.ordinal()] = SideMode.EXPORT;

        sideModeTanks = new SideMode[6];
        for (int i = 0; i < sideModeTanks.length; i++)
            sideModeTanks[i] = SideMode.IMPORT;
        sideModeTanks[ForgeDirection.DOWN.ordinal()] = SideMode.EXPORT;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        /*if (worldObj.isRemote)
            System.out.println("Client: " + getModeOnSide(ForgeDirection.DOWN));
        else
            System.out.println("Server: " + getModeOnSide(ForgeDirection.DOWN));*/

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
        steamTank.readFromNBT(nbtTagCompound);

       /*for (int i = 0; i < sideModesSlots.length; i++) {
            sideModesSlots[i] = SideMode.values()[nbtTagCompound.getInteger("SideMode" + i)];
        }*/
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Mode", redStoneMode.ordinal());
        steamTank.writeToNBT(nbtTagCompound);

        /*for (int i = 0; i < sideModesSlots.length; i++) {
            setModeOnSide(ForgeDirection.getOrientation(i), SideMode.values()[nbtTagCompound.getInteger("SideMode" + i)]);
        }*/
    }

    //ISteamHandler
    @Override
    public ISteamTank getSteamTank() {
        return steamTank;
    }

    @Override
    public boolean canFill(ForgeDirection direction, int amount) {
        if (getTankModeOnSide(direction.getOpposite()) == SideMode.IMPORT || getTankModeOnSide(direction.getOpposite()) == SideMode.BOTH)
            return steamTank.getAmount() + amount <= steamTank.getCapacity();
        else
            return false;
    }

    @Override
    public boolean canDrain(ForgeDirection direction, int amount) {
        if (getTankModeOnSide(direction.getOpposite()) == SideMode.DISABLED || getTankModeOnSide(direction.getOpposite()) == SideMode.BOTH)
            return steamTank.getAmount() - amount >= 0;
        else
            return false;
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
    public SideMode getModeOnSide(ForgeDirection side) {
        return sideModesSlots[side.ordinal()];
    }

    @Override
    public void changeMode(ForgeDirection side) {
        switch (getModeOnSide(side)) {
            case IMPORT:
                sideModesSlots[side.ordinal()] = SideMode.EXPORT;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case EXPORT:
                sideModesSlots[side.ordinal()] = SideMode.BOTH;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case BOTH:
                sideModesSlots[side.ordinal()] = SideMode.DISABLED;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case DISABLED:
                sideModesSlots[side.ordinal()] = SideMode.IMPORT;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            default:
                System.out.println("Unknown side mode. Please report this.");
                sideModesSlots[side.ordinal()] = SideMode.IMPORT;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
        }
    }

    @Override
    public void setModeOnSide(ForgeDirection direction, SideMode mode) {
        sideModesSlots[direction.ordinal()] = mode;
    }

    @Override
    public SideMode getTankModeOnSide(ForgeDirection side) {
        return sideModeTanks[side.ordinal()];
    }

    @Override
    public void changeTankMode(ForgeDirection side) {
        switch (getTankModeOnSide(side)) {
            case IMPORT:
                sideModeTanks[side.ordinal()] = SideMode.EXPORT;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case EXPORT:
                sideModeTanks[side.ordinal()] = SideMode.BOTH;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case BOTH:
                sideModeTanks[side.ordinal()] = SideMode.DISABLED;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case DISABLED:
                sideModeTanks[side.ordinal()] = SideMode.IMPORT;
                //System.out.println(sideModesSlots[side.ordinal()]);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            default:
                System.out.println("Unknown side mode. Please report this.");
                sideModeTanks[side.ordinal()] = SideMode.IMPORT;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
        }
    }

    @Override
    public void setTankModeOnSide(ForgeDirection direction, SideMode mode) {
        sideModeTanks[direction.ordinal()] = mode;
    }

    @Override
    public void overrideConfig(SideMode[] sideMode) {
        this.sideModesSlots = sideMode;
        this.sideModeTanks = sideMode;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.IMPORT || getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH)
            return inputSlots;
        else if (getModeOnSide(ForgeDirection.getOrientation(side)) != SideMode.DISABLED && getModeOnSide(ForgeDirection.getOrientation(side)) != SideMode.IMPORT)
            return exportSlots;
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.IMPORT || getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH)
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.EXPORT || getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH) {
            return true;
        }
        return false;
    }

    @Override
    public abstract int getSizeInventory();

    public abstract int[] getInputSlots();

    public abstract int[] getExportSlots();
}