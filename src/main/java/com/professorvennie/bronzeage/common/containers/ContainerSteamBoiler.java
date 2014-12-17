package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 10/23/2014 at 9:46 PM.
 */
public class ContainerSteamBoiler extends Container {

    private TileEntitySteamBoiler tile;

    public ContainerSteamBoiler(InventoryPlayer inventory, TileEntitySteamBoiler tile) {
        this.tile = tile;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }
}
