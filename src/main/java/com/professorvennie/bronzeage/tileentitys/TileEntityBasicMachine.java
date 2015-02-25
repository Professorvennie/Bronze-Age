package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.tiles.IButtonHandler;
import com.professorvennie.bronzeage.api.tiles.IRedstoneControlable;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:41 PM.
 */
public class TileEntityBasicMachine extends TileEntityBasicSidedInventory implements IButtonHandler, IRedstoneControlable {

    public boolean canWork;
    private RedstoneMode redStoneMode;
    public boolean isActive;

    public TileEntityBasicMachine(String name) {
        super(name);
        redStoneMode = RedstoneMode.low;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        updateRedStone();
    }

    public void updateRedStone(){

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
