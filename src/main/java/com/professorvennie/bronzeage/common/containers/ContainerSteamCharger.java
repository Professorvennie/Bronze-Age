package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamCharger;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 2/15/2015 at 3:29 PM.
 */
public class ContainerSteamCharger extends ContainerBasicMachine {

    public ContainerSteamCharger(InventoryPlayer inventory, TileEntitySteamCharger tileEntity) {
        super(tileEntity);
        addPlayersInv(inventory);
        addDefaultSteamTankSlots();
        addSlotToContainer(new Slot(tile, 2, 60, 35));
        addSlotToContainer(new Slot(tile, 3, 120, 35));
    }
}
