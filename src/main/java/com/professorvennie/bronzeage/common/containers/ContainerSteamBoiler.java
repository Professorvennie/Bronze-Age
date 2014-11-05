package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by ProfessorVennie on 10/23/2014 at 9:46 PM.
 */
public class ContainerSteamBoiler extends Container {

    private TileEntitySteamBoiler tile;

    public ContainerSteamBoiler(InventoryPlayer inventory, TileEntitySteamBoiler tile) {
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }
}
