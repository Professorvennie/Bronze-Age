package com.professorvennie.bronzeage.core.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by ProfessorVennie on 2/14/2015 at 10:39 PM.
 */
//// TODO: 9/12/2016 fix this
public class BookPlayerHandler implements ICapabilityProvider {

    public boolean received;

    /*@Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("Received_Book", received);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        received = compound.getBoolean("Received_Book");
    }

    @Override
    public void init(Entity entity, World world) {

    }*/

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return null;
    }
}
