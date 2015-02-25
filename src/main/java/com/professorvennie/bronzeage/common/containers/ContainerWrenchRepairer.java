package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntityWrenchRepairer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:35 PM.
 */
public class ContainerWrenchRepairer extends ContainerBasicMachine {

    public ContainerWrenchRepairer(InventoryPlayer inventory, TileEntityWrenchRepairer tileEntity) {
        super(tileEntity);

        addSlotToContainer(new Slot(tileEntity, 0, 27, 47));
        addSlotToContainer(new Slot(tileEntity, 1, 76, 47));
        addSlotToContainer(new Slot(tileEntity, 2, 134, 47));
        addPlayersInv(inventory);
    }
}
