package com.professorvennie.bronzeage.api.steam;

import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 2/14/2015 at 5:09 PM.
 */
public interface ISteamUsingItem {

    int getSteamCapacity();

    void receiveSteam(ItemStack itemStack, int amount);

    void extractSteam(ItemStack itemStack, int amount);

    boolean canExtract();

    boolean canReceive();
}
