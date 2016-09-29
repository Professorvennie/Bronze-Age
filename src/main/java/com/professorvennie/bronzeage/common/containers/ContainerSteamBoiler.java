package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 10/23/2014 at 9:46 PM.
 */
public class ContainerSteamBoiler extends ContainerBasicMachine {

    public int lastBurnTime, lastItemBurnTime, lastTemp, lastTank2;
    private TileEntitySteamBoiler entity;

    public ContainerSteamBoiler(InventoryPlayer inventory, TileEntitySteamBoiler tile) {
        super(tile);
        this.entity = tile;
        addSlotToContainer(new Slot(tile, 0, 127, 9));
        addSlotToContainer(new Slot(tile, 1, 127, 58));
        addSlotToContainer(new Slot(tile, 2, 33, 9));
        addSlotToContainer(new Slot(tile, 3, 33, 58));
        addSlotToContainer(new Slot(tile, 4, 80, 41));
        addPlayersInv(inventory);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener icrafting = this.listeners.get(i);

            if (this.lastBurnTime != this.entity.burnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.entity.getField(3));
            }

            if (this.lastItemBurnTime != this.entity.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.entity.getField(4));
            }

            if (lastTemp != entity.temp) {
                icrafting.sendProgressBarUpdate(this, 3, entity.getField(5));
            }

            if (lastTank2 != entity.getWaterAmount()) {
                icrafting.sendProgressBarUpdate(this, 4, entity.getField(7));
            }
        }
        this.lastBurnTime = this.entity.getField(0);
        this.lastItemBurnTime = this.entity.getField(1);
        this.lastTemp = this.entity.getField(5);
        this.lastTank2 = this.entity.getField(7);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int par2) {
        super.updateProgressBar(slot, par2);
        entity.setField(slot, par2);
        /*if (slot == 4) {
            if (entity.getWaterTank() != null && entity.getWaterTank().getFluid() != null)
                entity.getWaterTank().getFluid().amount = par2;
        }*/
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return entity.isUseableByPlayer(player);
    }

   /* @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 1, this.entity.burnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.entity.currentItemBurnTime);
        iCrafting.sendProgressBarUpdate(this, 3, this.entity.temp);
        iCrafting.sendProgressBarUpdate(this, 4, lastTank2);
    }*/

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, tile);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot0) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slot0);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (slot0 < entity.getSizeInventory()) {
                if (!mergeItemStack(itemStack1, entity.getSizeInventory(), inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                if (TileEntityFurnace.isItemFuel(itemStack1)) {
                    if (!mergeItemStack(itemStack1, TileEntitySteamBoiler.FUELSLOT, TileEntitySteamBoiler.FUELSLOT + 1, false)) {
                        return null;
                    }
                } else if (itemStack1.getItem() == Items.WATER_BUCKET) {
                    if (!mergeItemStack(itemStack1, TileEntitySteamBoiler.WATERSLOT, TileEntitySteamBoiler.WATERSLOT + 1, false)) {
                        return null;
                    }
                } else if (itemStack1.getItem() == Items.BUCKET) {
                    if (!mergeItemStack(itemStack1, TileEntitySteamBoiler.STEAMEMPTYSLOT, TileEntitySteamBoiler.STEAMEMPTYSLOT + 1, false)) {
                        return null;
                    }
                } else
                    return null;
            }
            if (itemStack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
