package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.tiles.IButtonHandler;
import com.professorvennie.bronzeage.api.tiles.IRedstoneControlable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:41 PM.
 */
public class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements IButtonHandler, IRedstoneControlable {

    public boolean canWork;
    private RedstoneMode redStoneMode;
    public boolean isActive;
    private boolean isWorking;

    public TileEntityBasicMachine(String name) {
        super(name);
        redStoneMode = RedstoneMode.low;
    }

    @Override
    public void update() {
        updateRedStone();
    }

    public void updateRedStone(){

        switch (redStoneMode) {
            case low:
                if (worldObj.isBlockIndirectlyGettingPowered(pos) <= 0)
                    canWork = true;
                else
                    canWork = false;
                break;
            case high:
                if (worldObj.isBlockIndirectlyGettingPowered(pos) > 0)
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Mode", redStoneMode.ordinal());
        return nbtTagCompound;
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

    public void markDirtyClient() {
        markDirty();
        if (worldObj != null) {
            IBlockState state = worldObj.getBlockState(getPos());
            worldObj.notifyBlockUpdate(getPos(), state, state, 3);
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

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
