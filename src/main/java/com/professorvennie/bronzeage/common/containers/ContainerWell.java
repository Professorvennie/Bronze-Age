package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntityWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:49 PM.
 */
public class ContainerWell extends Container {

    public int lastTankAmount, lastPipes;
    private TileEntityWell tile;

    public ContainerWell(InventoryPlayer inventory, TileEntityWell entity) {
        this.tile = entity;

        addSlotToContainer(new Slot(entity, 0, 33, 9));
        addSlotToContainer(new Slot(entity, 1, 33, 58));
        addSlotToContainer(new Slot(entity, 2, 69, 34));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    /*@Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, lastTankAmount);
        icrafting.sendProgressBarUpdate(this, 1, lastPipes);
    }*/

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

            if (lastTankAmount != tile.tank.getFluidAmount())
                icrafting.sendProgressBarUpdate(this, 0, tile.getField(0));

            if (lastPipes != tile.getAmountOfPipes())
                icrafting.sendProgressBarUpdate(this, 1, tile.getAmountOfPipes());
        }
        lastTankAmount = tile.getField(0);
        lastPipes = tile.getAmountOfPipes();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int par2) {
        super.updateProgressBar(slot, par2);
        tile.setField(slot, par2);
        if (slot == 1)tile.setAmountOfPipes(par2);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }
}
