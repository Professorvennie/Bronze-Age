package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 1/2/2015 at 12:06 PM.
 */
public class ContainerFake extends Container {

    public ContainerFake(InventoryPlayer inventory, TileEntityBasicSteamMachine tileEntityBasicMachine) {
        //addSlotToContainer(new Slot(tileEntityBasicMachine, tileEntityBasicMachine.inventory.length, 176, 31));

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
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return super.transferStackInSlot(player, slot);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
