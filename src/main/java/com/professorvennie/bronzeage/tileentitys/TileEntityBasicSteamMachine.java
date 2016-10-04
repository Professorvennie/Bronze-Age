package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.api.tiles.ISideConfigurable;
import com.professorvennie.bronzeage.api.steam.ISteamHandler;
import com.professorvennie.bronzeage.blocks.BlockBasicMachine;
import com.professorvennie.bronzeage.blocks.fluids.ModFluids;
import com.professorvennie.bronzeage.core.network.MessageUpdate;
import com.professorvennie.bronzeage.core.network.PacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

/**
 * Created by ProfessorVennie on 11/23/2014 at 3:29 PM.
 */
public abstract class TileEntityBasicSteamMachine extends TileEntityBasicMachine implements ISteamHandler, ISideConfigurable {

    protected SideMode[] sideModesSlots, sideModeTanks;
    public FluidTank steamTank;
    private int inputSlots[], exportSlots[], machineSpeed, progress;

    public TileEntityBasicSteamMachine(String name, int capacity) {
        super(name);
        steamTank = new FluidTank(ModFluids.steam, 0, 10000);
        inputSlots = getInputSlots();
        exportSlots = getExportSlots();

        sideModesSlots = new SideMode[6];
        for (int i = 0; i < sideModesSlots.length; i++)
            sideModesSlots[i] = SideMode.IMPORT;
        sideModesSlots[EnumFacing.DOWN.ordinal()] = SideMode.EXPORT;

        sideModeTanks = new SideMode[6];
        for (int i = 0; i < sideModeTanks.length; i++)
            sideModeTanks[i] = SideMode.IMPORT;
        sideModeTanks[EnumFacing.DOWN.ordinal()] = SideMode.EXPORT;
    }

    @Override
    public boolean isWorking(){
        return isActive;
    }

    @Override
    public void update() {
        super.update();

        if(!worldObj.isRemote){
            for(EnumFacing direction : EnumFacing.HORIZONTALS){
                BlockPos pos = new BlockPos(getPos().getX() + direction.getFrontOffsetX(), getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ());
                TileEntity tile = worldObj.getTileEntity(pos);
                if(tile != null && tile instanceof ISteamHandler){
                    ISteamHandler steamHandler = (ISteamHandler)tile;
                    System.out.println("SteamAmountOther: " + steamHandler.getSteamAmount() + "  ThisSteamAmount: " + getSteamAmount());
                    if(steamHandler.canFill(direction, 100) && canDrain(direction, 100)){
                        steamHandler.fill(direction, 100);
                        drain(direction, 100);
                        //PacketHandler.INSTANCE.sendToServer(new MessageUpdate(pos.getX(), pos.getY(), pos.getZ(), 0, steamHandler.getSteamAmount(), 0, 0));
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        steamTank.writeToNBT(nbtTagCompound);

        for (int i = 0; i < sideModesSlots.length; i++)
            nbtTagCompound.setInteger("SideMode" + i, sideModesSlots[i].ordinal());

        for (int i = 0; i < sideModeTanks.length; i++)
            nbtTagCompound.setInteger("TankMode" + i, sideModeTanks[i].ordinal());

        return nbtTagCompound;
    }

    //ISteamHandler
    @Override
    public FluidTank getSteamTank() {
        return steamTank;
    }

    @Override
    public void setSteamAmount(int amount) {
        steamTank.drain(steamTank.getFluidAmount(), true);
        steamTank.fill(new FluidStack(ModFluids.steam, amount), true);
    }

    @Override
    public boolean canFill(EnumFacing direction, int amount) {
        if (getTankModeOnSide(direction.getOpposite()) == SideMode.IMPORT || getTankModeOnSide(direction.getOpposite()) == SideMode.BOTH)
            return steamTank.getFluidAmount() + amount <= steamTank.getCapacity();
        else
            return false;
    }

    @Override
    public boolean canDrain(EnumFacing direction, int amount) {
        if (getTankModeOnSide(direction.getOpposite()) == SideMode.EXPORT || getTankModeOnSide(direction.getOpposite()) == SideMode.BOTH)
            return steamTank.getFluidAmount() - amount >= 0;
        else
            return false;
    }

    @Override
    public void fill(EnumFacing direction, int amount) {
        if (canFill(direction, amount))
            steamTank.fill(new FluidStack(ModFluids.steam, amount), true);
    }

    @Override
    public void drain(EnumFacing direction, int amount) {
        if (canDrain(direction, amount))
            steamTank.fill(new FluidStack(ModFluids.steam, amount), true);
    }

    @Override
    public int getSteamAmount() {
        return steamTank.getFluidAmount();
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
    public SideMode getModeOnSide(EnumFacing side) {
        return sideModesSlots[side.ordinal()];
    }

    @Override
    public void changeMode(EnumFacing side) {
        switch (getModeOnSide(side)) {
            case IMPORT:
                sideModesSlots[side.ordinal()] = SideMode.EXPORT;
                markDirty();
                break;
            case EXPORT:
                sideModesSlots[side.ordinal()] = SideMode.BOTH;
                markDirty();
                break;
            case BOTH:
                sideModesSlots[side.ordinal()] = SideMode.DISABLED;
                markDirty();
                break;
            case DISABLED:
                sideModesSlots[side.ordinal()] = SideMode.IMPORT;
                markDirty();
                break;
            default:
                System.out.println("Unknown side mode. Please report this.");
                sideModesSlots[side.ordinal()] = SideMode.IMPORT;
                markDirty();
                break;
        }
    }

    @Override
    public void setModeOnSide(EnumFacing direction, SideMode mode) {
        sideModesSlots[direction.ordinal()] = mode;
    }

    @Override
    public SideMode getTankModeOnSide(EnumFacing side) {
        return sideModeTanks[side.ordinal()];
    }

    @Override
    public void changeTankMode(EnumFacing side) {
        switch (getTankModeOnSide(side)) {
            case IMPORT:
                sideModeTanks[side.ordinal()] = SideMode.EXPORT;
                markDirty();
                break;
            case EXPORT:
                sideModeTanks[side.ordinal()] = SideMode.BOTH;
                markDirty();
                break;
            case BOTH:
                sideModeTanks[side.ordinal()] = SideMode.DISABLED;
                markDirty();
                break;
            case DISABLED:
                sideModeTanks[side.ordinal()] = SideMode.IMPORT;
                markDirty();
                break;
            default:
                System.out.println("Unknown side mode. Please report this.");
                sideModeTanks[side.ordinal()] = SideMode.IMPORT;
                markDirty();
                break;
        }
    }

    @Override
    public void setTankModeOnSide(EnumFacing direction, SideMode mode) {
        sideModeTanks[direction.ordinal()] = mode;
    }

    @Override
    public void overrideConfig(SideMode[] sideMode) {
        this.sideModesSlots = sideMode;
        this.sideModeTanks = sideMode;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] both = new int[inputSlots.length + exportSlots.length];
        System.arraycopy(inputSlots, 0, both, 0, inputSlots.length);
        System.arraycopy(exportSlots, 0, both, inputSlots.length, exportSlots.length);
        if (getModeOnSide(side) == SideMode.IMPORT)
            return inputSlots;
        else if (getModeOnSide(side) == SideMode.EXPORT)
            return exportSlots;
        else if (getModeOnSide(side) == SideMode.BOTH)
            return both;
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side) {
        if ((getModeOnSide(side) == SideMode.IMPORT || getModeOnSide(side) == SideMode.BOTH) && isItemValidForSlot(slot, itemStack))
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side) {
        if (getModeOnSide(side) == SideMode.EXPORT || getModeOnSide(side) == SideMode.BOTH)
            return true;
        return false;
    }

    @Override
    public int getFieldCount() {
        return 3;
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return machineSpeed;
            case 1:
                return progress;
            case 2:
                //System.out.println("GetSteamAmount: " + steamTank.getAmount());
                 return steamTank.getFluidAmount();
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                this.machineSpeed = value;
                break;
            case 1:
                this.progress = value;
                break;
            case 2:
                setSteamAmount(value);
                //System.out.println("Value: " + value);
                break;
            default:
                break;
        }
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return steamTank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if(steamTank.canFill()){
            steamTank.fill(resource, doFill);
            return steamTank.getFluidAmount();
        }
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return drain(new FluidStack(ModFluids.steam, maxDrain), doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (steamTank.canDrain()){
            steamTank.drain(resource, doDrain);
            return steamTank.getFluid();
        }
        return null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) steamTank;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState != newSate;
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
}