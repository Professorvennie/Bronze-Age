package com.professorvennie.bronzeage.api.wrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 11/25/2014 at 7:27 PM.
 */
public interface IWrenchable {

    public void dismantle(World world, EntityPlayer player, ItemStack wrench, int x, int y, int z);
}
