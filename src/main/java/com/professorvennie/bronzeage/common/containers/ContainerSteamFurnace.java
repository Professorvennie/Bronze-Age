package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 1/18/2015 at 1:27 PM.
 */
public class ContainerSteamFurnace extends ContainerBasicMachine {

    public ContainerSteamFurnace(InventoryPlayer inventory, TileEntitySteamFurnace tile) {
        super(tile);
        addPlayersInv(inventory);
        addDefaultSteamTankSlots();

        addSlotToContainer(new Slot(tile, 2, 60, 35));
        addSlotToContainer(new Slot(tile, 3, 120, 35));
        addSlotToContainer(new Slot(tile, 4, 136, 35));

    }
}
