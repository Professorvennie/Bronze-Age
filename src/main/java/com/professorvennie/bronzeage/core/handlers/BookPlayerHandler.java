package com.professorvennie.bronzeage.core.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by ProfessorVennie on 2/14/2015 at 10:39 PM.
 */
public class BookPlayerHandler implements IExtendedEntityProperties {

    public boolean received;

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("Received_Book", received);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        received = compound.getBoolean("Received_Book");
    }

    @Override
    public void init(Entity entity, World world) {

    }
}
