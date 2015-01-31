package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 1/18/2015 at 1:27 PM.
 */
public class ContainerSteamFurnace extends Container {

    private TileEntitySteamFurnace tile;

    public ContainerSteamFurnace(InventoryPlayer inventory, TileEntitySteamFurnace tile) {
        this.tile = tile;

        addSlotToContainer(new Slot(tile, 0, 33, 9));
        addSlotToContainer(new Slot(tile, 1, 33, 58));

        addSlotToContainer(new Slot(tile, 3, 60, 35));

        addSlotToContainer(new Slot(tile, 4, 120, 35));
        addSlotToContainer(new Slot(tile, 5, 136, 35));

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
