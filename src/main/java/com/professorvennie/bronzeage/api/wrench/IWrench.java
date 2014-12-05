package com.professorvennie.bronzeage.api.wrench;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 11/25/2014 at 7:26 PM.
 */
public interface IWrench {

    public int getDurability();

    public float getNumOfUsesRemaining(ItemStack itemStack);

    public void damage(ItemStack itemStack, boolean isDismantle);

    public void onWrenchUsed(World world, ItemStack wrench, int x, int y, int z);

    public WrenchMaterial getWrenchMaterial();

    public void addUse(ItemStack itemStack, float amountOfUses);

    public void subtractUse(ItemStack itemStack, float amountOfUses);
}
