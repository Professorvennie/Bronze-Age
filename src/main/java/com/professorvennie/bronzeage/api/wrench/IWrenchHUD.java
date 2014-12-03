package com.professorvennie.bronzeage.api.wrench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 11/25/2014 at 7:27 PM.
 */
public interface IWrenchHUD {

    @SideOnly(Side.CLIENT)
    public void renderHUD(World world, Minecraft minecraft, ItemStack wrench, int x, int y, int z);
}
