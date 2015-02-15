package com.professorvennie.bronzeage.api;

import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 2/14/2015 at 5:09 PM.
 */
public interface ISteamUsingItem {

    public int getSteamCapacity();

    public void receiveSteam(ItemStack itemStack, int amount);

    public void extractSteam(ItemStack itemStack, int amount);

    public boolean canExtract();

    public boolean canReceive();
}
