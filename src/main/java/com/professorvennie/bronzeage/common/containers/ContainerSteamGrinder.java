package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamGrinder;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:18 PM.
 */
public class ContainerSteamGrinder extends ContainerBasicMachine {

    public ContainerSteamGrinder(InventoryPlayer inventory, TileEntitySteamGrinder tileEntity) {
        super(tileEntity);
        addPlayersInv(inventory);
        addDefaultSteamTankSlots();

        addSlotToContainer(new Slot(tile, 3, 60, 35));
        addSlotToContainer(new Slot(tile, 4, 120, 35));
        addSlotToContainer(new Slot(tile, 5, 136, 35));
    }
}