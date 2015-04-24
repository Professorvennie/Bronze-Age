package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.api.tiles.ISideConfigurable;
import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import com.professorvennie.bronzeage.api.steam.ISteamTank;
import com.professorvennie.bronzeage.api.steam.SteamTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public abstract class TileEntityBasicSteamMachine extends TileEntityBasicMachine implements ISteamHandler, ISideConfigurable {

    protected SideMode[] sideModesSlots, sideModeTanks;
    private SteamTank steamTank;
    private int inputSlots[], exportSlots[], machineSpeed, progress;

    public TileEntityBasicSteamMachine(String name, int capacity) {
        super(name);
        steamTank = new SteamTank(0, capacity);
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

        if(!worldObj.isRemote){
            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
                TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                if(tile != null && tile instanceof ISteamHandler){
                    ISteamHandler steamHandler = (ISteamHandler)tile;
                    if(steamHandler.canFill(direction, 100) && canDrain(direction, 100)){
                        steamHandler.fill(direction, 100);
                        drain(direction, 100);
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        steamTank.readFromNBT(nbtTagCompound);

       for (int i = 0; i < sideModesSlots.length; i++)
            sideModesSlots[i] = SideMode.values()[nbtTagCompound.getInteger("SideMode" + i)];

        for (int i = 0; i < sideModeTanks.length; i++)
            sideModeTanks[i] = SideMode.values()[nbtTagCompound.getInteger("TankMode" + i)];

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        steamTank.writeToNBT(nbtTagCompound);

        for (int i = 0; i < sideModesSlots.length; i++)
            nbtTagCompound.setInteger("SideMode" + i, sideModesSlots[i].ordinal());

        for (int i = 0; i < sideModeTanks.length; i++)
            nbtTagCompound.setInteger("TankMode" + i, sideModeTanks[i].ordinal());

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
        if (getTankModeOnSide(direction.getOpposite()) == SideMode.EXPORT || getTankModeOnSide(direction.getOpposite()) == SideMode.BOTH)
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
    public SideMode getModeOnSide(ForgeDirection side) {
        return sideModesSlots[side.ordinal()];
    }

    @Override
    public void changeMode(ForgeDirection side) {
        switch (getModeOnSide(side)) {
            case IMPORT:
                sideModesSlots[side.ordinal()] = SideMode.EXPORT;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case EXPORT:
                sideModesSlots[side.ordinal()] = SideMode.BOTH;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case BOTH:
                sideModesSlots[side.ordinal()] = SideMode.DISABLED;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case DISABLED:
                sideModesSlots[side.ordinal()] = SideMode.IMPORT;
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
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case EXPORT:
                sideModeTanks[side.ordinal()] = SideMode.BOTH;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case BOTH:
                sideModeTanks[side.ordinal()] = SideMode.DISABLED;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                break;
            case DISABLED:
                sideModeTanks[side.ordinal()] = SideMode.IMPORT;
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
        int[] both = new int[inputSlots.length + exportSlots.length];
        System.arraycopy(inputSlots, 0, both, 0, inputSlots.length);
        System.arraycopy(exportSlots, 0, both, inputSlots.length, exportSlots.length);
        if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.IMPORT)
            return inputSlots;
        else if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.EXPORT)
            return exportSlots;
        else if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH)
            return both;
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        if ((getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.IMPORT || getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH) && isItemValidForSlot(slot, itemStack))
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        if (getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.EXPORT || getModeOnSide(ForgeDirection.getOrientation(side)) == SideMode.BOTH)
            return true;
        return false;
    }

    public abstract boolean isItemValidForSlot(int slot, ItemStack itemStack);
    public abstract int getSizeInventory();
    public abstract int[] getInputSlots();
    public abstract int[] getExportSlots();

    public int getMachineSpeed() {
        return machineSpeed;
    }

    public void setMachineSpeed(int machineSpeed) {
        this.machineSpeed = machineSpeed;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void increaseProgressByOne(){
        this.progress++;
    }

    public void resetProgress(){
        this.progress = 0;
    }

    public int getProgressScaled(int scale){
        return progress * scale / machineSpeed;
    }
}